package com.example.logintest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.telecom.RemoteConnection
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern


@SuppressLint("Registered")
class RegisterAct : AppCompatActivity() {

    private var RButton: Button? = null
    private var BButton: Button? = null
    private var Usertext: EditText? = null
    private var Emailtext: EditText? = null
    private var pwtext: EditText? = null
    private var pwtext2: EditText? = null
    private var UName: String? = null
    private var pw: String? = null
    private var pw2: String? = null
    private var email: String? = null
    fun saveRegisterInfo(UName: String?, pw: String?,email: String?) {
        val savepw = encode(pw.toString())
        val savemail = email.toString()
        val gsp = getSharedPreferences("regiInfo", MODE_PRIVATE)
        val editor = gsp.edit()
        editor.putString(UName,savepw )
        editor.putString(savemail,UName)
        editor.apply()
    }
    fun isExistUName(UName: String?): Boolean {
        var NameExist = false
        val gsp = getSharedPreferences("regiInfo", MODE_PRIVATE)
        val savpw = gsp.getString(UName, "")
        if (!TextUtils.isEmpty(savpw)) {
            NameExist = true
        }
        return NameExist
    }
    fun isExistEmail(email: String?): Boolean {
        var EmailExist = false
        val gsp = getSharedPreferences("regiInfo", MODE_PRIVATE)
        val savemail = gsp.getString(email, "")
        if (!TextUtils.isEmpty(savemail)) {
            EmailExist = true
        }
        return EmailExist
    }
    fun encode(pw: String): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(pw.toByteArray())
            val sb: StringBuffer = StringBuffer()
            for (b in digest) {
                //获取低八位有效值
                var i: Int = b.toInt() and 0xff
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
        setContentView(com.example.myapplication.R.layout.testregister)
        init()
    }

    private fun init() {
        Usertext = findViewById(com.example.myapplication.R.id.editText2)
        pwtext = findViewById(com.example.myapplication.R.id.editText3)
        pwtext2 = findViewById(com.example.myapplication.R.id.editText4)
        Emailtext = findViewById(com.example.myapplication.R.id.editText6)
        RButton = findViewById(com.example.myapplication.R.id.button4)
        BButton = findViewById(com.example.myapplication.R.id.button5)
        var pwstyle = Regex("[A-Z,a-z,0-9,?,!,(,),+,-,*,/,=,%,@]{6,16}")
        var emailstyle = Regex("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")
        BButton!!.setOnClickListener {
            val intent = Intent(this@RegisterAct,LoginAct::class.java)
            startActivity(intent)
        }
        RButton!!.setOnClickListener(View.OnClickListener {
            UName = Usertext!!.text.toString().trim { it <= ' ' }
            pw = pwtext!!.text.toString().trim { it <= ' ' }
            pw2 = pwtext2!!.text.toString().trim { it <= ' ' }
            email = Emailtext!!.text.toString().trim { it <= ' ' }
            when {
                TextUtils.isEmpty(UName) -> {
                    Toast.makeText(this@RegisterAct, "请输入用户名", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                TextUtils.isEmpty(pw) -> {
                    Toast.makeText(this@RegisterAct, "请输入密码", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                !pwstyle.matches(pw.toString()) -> {
                    Toast.makeText(this@RegisterAct, "密码格式错误", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                TextUtils.isEmpty(pw2) -> {
                    Toast.makeText(this@RegisterAct, "请再次输入密码", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                pw != pw2 -> {
                    Toast.makeText(this@RegisterAct, "输入两次的密码不一样", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                TextUtils.isEmpty(email.toString()) -> {
                    Toast.makeText(this@RegisterAct, "请输入邮箱", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                !emailstyle.matches(email.toString()) -> {
                    Toast.makeText(this@RegisterAct, "邮箱格式不正确", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }

                isExistUName(UName) -> {
                    Toast.makeText(this@RegisterAct, "此账户名已经存在", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                isExistEmail(email) -> {
                    Toast.makeText(this@RegisterAct, "此邮箱已注册", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                else -> {
                    Toast.makeText(this@RegisterAct, "注册成功", Toast.LENGTH_SHORT).show()
                    saveRegisterInfo(UName, pw, email)
                    val data = Intent()
                    data.putExtra("Name", UName)
                    setResult(Activity.RESULT_OK, data)
                    this@RegisterAct.finish()
                    startActivity(Intent(this@RegisterAct, FinalAct::class.java))
                }
            }

        })








    }


}