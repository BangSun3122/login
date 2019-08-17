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

class ChangePasswordAct : AppCompatActivity() {
    var SButton: Button? = null
    var CButton: Button? = null
    var Usertext: EditText? = null
    var Keytext: EditText? = null
    var Keytext2: EditText? = null
    var UName:String? = null
    var pw:String? = null
    var pw2:String? = null
    var readpw:String? = null
    fun encode(pw: String): String {
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

    fun readPsw(userName: String?): String? {
        val sp = getSharedPreferences("regiInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }

    fun saveRegisterInfo(UName: String?, pw: String?) {
        val savepw = encode(pw.toString())
        val gsp = getSharedPreferences("regiInfo", MODE_PRIVATE)
        val editor = gsp.edit()
        editor.putString(UName,savepw )
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testchange)
        init()
    }
    private fun init() {
        SButton = findViewById(R.id.button6)
        CButton = findViewById(R.id.button7)
        Usertext = findViewById(R.id.editText7)
        Keytext = findViewById(R.id.editText8)
        Keytext2 = findViewById(R.id.editText9)
        var pwstyle = Regex("[A-Z,a-z,0-9,?,!,(,),+,-,*,/,=,%,@]{6,16}")


        SButton!!.setOnClickListener{
            val intent = Intent(this@ChangePasswordAct, LoginAct::class.java)
            startActivity(intent)
        }

        CButton!!.setOnClickListener(View.OnClickListener {
            UName = Usertext!!.text.toString().trim { it <= ' ' }
            pw = Keytext!!.text.toString().trim { it <= ' ' }
            pw2 = Keytext2!!.text.toString().trim { it <= ' ' }

            readpw = readPsw(UName)
            val temp_pw:String=pw.toString()
            if (TextUtils.isEmpty(UName)) {
                Toast.makeText(this@ChangePasswordAct, "请输入用户名", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (TextUtils.isEmpty(pw)) {
                Toast.makeText(this@ChangePasswordAct, "请输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (TextUtils.isEmpty(pw2)) {
                Toast.makeText(this@ChangePasswordAct, "请输入修改后密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (encode(temp_pw) == readpw) {
                when{
                    !pwstyle.matches(pw2.toString()) -> {
                        Toast.makeText(this@ChangePasswordAct, "修改后密码格式错误", Toast.LENGTH_SHORT).show()
                        return@OnClickListener
                    } else -> {
                    Toast.makeText(this@ChangePasswordAct, "修改成功", Toast.LENGTH_SHORT).show()
                    saveRegisterInfo(UName, pw2)
                    val data = Intent()
                    data.putExtra("Name", UName)
                    setResult(Activity.RESULT_OK, data)
                    this@ChangePasswordAct.finish()
                    startActivity(Intent(this@ChangePasswordAct, FinalAct::class.java))
                }
                }

            }else if (readpw != null && !TextUtils.isEmpty(readpw) &&encode(temp_pw) != readpw) {
                Toast.makeText(this@ChangePasswordAct, "输入的密码错误", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            else {
                Toast.makeText(this@ChangePasswordAct, "此用户名不存在", Toast.LENGTH_SHORT).show()
            }
        })

    }
}