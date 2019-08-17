package com.example.mvplogin.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.logintest.FinalAct
import com.example.mvplogin.contract.registerContract
import com.example.mvplogin.presenter.ipresenter.basepresenter
import com.example.mvplogin.view.act.RegisterActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class RegisterPresenter (private var mView:RegisterActivity):basepresenter<registerContract.View, registerContract.Model>(){
    fun onEmptyUser()
    {
        mView.setEmptyUser()
    }

    fun onEmptyPw() {
        mView.setEmptyPw()
    }

     fun onEmptyPw2(){
        mView.setEmptyPw2()
    }

    fun onEmptyEmail(){
        mView.setEmptyEmail()
    }
    fun onWrongPw() {
        mView.setWrongPw()
    }

    fun onWrongEmail(){
        mView.setWrongEmail()
    }

    fun onPwDifference() {
        mView.setPwDifference()
    }

    fun onExistUser() {
        mView.setExistUser()
    }

    fun onExistEmail() {
        mView.setExistEmail()
    }

    fun onSuccess() {
        mView.setSuccess()
    }
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
    fun register(UName: String, Email: EditText, pw:String, pw2:String, context: Context){
        var pwstyle = Regex("[A-Z,a-z,0-9,?,!,(,),+,-,*,/,=,%,@]{6,16}")
        var emailstyle = Regex("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")
        var tempemail=Email.text.toString()
        when {
            TextUtils.isEmpty(UName) -> {
                onEmptyUser()
            }
            TextUtils.isEmpty(pw) -> {
                onEmptyPw()
            }
            !pwstyle.matches(pw) -> {
                onWrongPw()
            }
            TextUtils.isEmpty(pw2) -> {
                onEmptyPw2()
            }
            pw != pw2 -> {
                onPwDifference()
            }
            TextUtils.isEmpty(tempemail) -> {
                onEmptyEmail()
            }
            !emailstyle.matches(tempemail) -> {
                onWrongEmail()
            }

            isExistUName(UName) -> {
                onExistUser()
            }
            isExistEmail(tempemail) -> {
                onExistEmail()
            }
            else -> {
                onSuccess()
            }
        }

    }

}