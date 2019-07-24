package com.example.logintest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginAct : AppCompatActivity() {

    var RButton: Button? = null//注册
    var LButton: Button? = null//登录
    var Usertext: EditText? = null
    var Keytext: EditText? = null
    var UName:String? = null
    var pw:String? = null
    var readpw:String? = null
    fun encode(pw: String): String {
        val digest: MessageDigest?
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest:ByteArray = instance.digest(pw.toByteArray())
            val sb : StringBuffer = StringBuffer()
            for (b in digest) {
                //获取低八位有效值
                var i :Int = b.toInt() and 0xff
                //将整数转化为16进制
                var hexString = Integer.toHexString(i)
                if (hexString.length < 2) {
                    //如果是一位的话，补0
                    hexString = "0" + hexString
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlogin)
        init()
    }
    private fun init() {
        LButton = findViewById(R.id.button2)
        RButton = findViewById(R.id.button3)
        Usertext = findViewById(R.id.editText5)
        Keytext = findViewById(R.id.editText)


        RButton!!.setOnClickListener{
            val intent = Intent(this@LoginAct, RegisterAct::class.java)
            startActivity(intent)
        }

        LButton!!.setOnClickListener(View.OnClickListener {
            //开始登录，获取用户名和密码
            UName = Usertext!!.text.toString().trim { it <= ' ' }
            pw = Keytext!!.text.toString().trim { it <= ' ' }

            readpw = readPsw(UName)
            val temp_pw:String=pw.toString()
            if (TextUtils.isEmpty(UName)) {
                Toast.makeText(this@LoginAct, "请输入用户名", Toast.LENGTH_SHORT).show()
                return@OnClickListener
                //密码为空
            } else if (TextUtils.isEmpty(pw)) {
                Toast.makeText(this@LoginAct, "请输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
                // 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
            } else if (encode(temp_pw) == readpw) {
                //登录成功
                Toast.makeText(this@LoginAct, "登录成功", Toast.LENGTH_SHORT).show()
                saveLoginStatus(true, UName)
                val data = Intent()
                data.putExtra("isLogin", true)
                setResult(Activity.RESULT_OK, data)
                this@LoginAct.finish()
                //跳转，登录成功的状态
                startActivity(Intent(this@LoginAct, FinalAct::class.java))
                return@OnClickListener
                //用户名密码不匹配
            }else if (readpw != null && !TextUtils.isEmpty(readpw) &&encode(temp_pw) != readpw) {
                Toast.makeText(this@LoginAct, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }//用户名不存在
            else {
                Toast.makeText(this@LoginAct, "此用户名不存在", Toast.LENGTH_SHORT).show()
            }
        })
    }
    //读取输入的用户名，找到与之匹配的密码并返回
    private fun readPsw(userName: String?): String? {
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }
    //保存登陆状态
    private fun saveLoginStatus(status: Boolean, UName: String?) {
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean("isLogin", status)
        //存入登录状态时的用户名
        editor.putString("loginUserName", UName)
        editor.apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            val UName = data.getStringExtra("userName")
            if (!TextUtils.isEmpty(UName)) {
                //设置用户名到 input_user_text 控件
                Usertext!!.setText(UName)
                Usertext!!.setSelection(UName!!.length)
            }
        }
    }

}