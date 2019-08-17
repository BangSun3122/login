package com.example.mvplogin.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.example.logintest.FinalAct
import com.example.mvplogin.contract.ChangeContract
import com.example.mvplogin.presenter.ipresenter.basepresenter
import com.example.mvplogin.view.act.ChangeActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ChangePresenter (private var mView: ChangeActivity) : basepresenter<ChangeContract.View, ChangeContract.Model>(),
ChangeContract.Presenter{
    fun onEmptyUser() {
        mView.setEmptyUser()
    }

    fun onEmptyPw() {
        mView.setEmptyPw()
    }

    fun onEmptyPw2(){
        mView.setEmptyPw2()
    }

    fun onWrongstylePw() {
        mView.setWrongstylePw()
    }

    fun onPwError() {
        mView.setPwError()
    }

    fun onUnknownUser()
    {
        mView.setUnknownUser()
    }

    fun onSuccess()
    {
        mView.setSuccess()
    }

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

    fun readPsw(userName: String?,context: Context): String? {
        val sp = context.getSharedPreferences("regiInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }

    fun saveRegisterInfo(UName: String?, pw: String?,context: Context) {
        val savepw = encode(pw.toString())
        val gsp = context.getSharedPreferences("regiInfo", MODE_PRIVATE)
        val editor = gsp.edit()
        editor.putString(UName,savepw )
        editor.apply()
    }

    fun Change(UName:String,pw:String,pw2:String,context: Context){
        var pw1=readPsw(UName,context)
        var pwstyle = Regex("[A-Z,a-z,0-9,?,!,(,),+,-,*,/,=,%,@]{6,16}")
        if (TextUtils.isEmpty(UName)) {
            onEmptyUser()
        } else if (TextUtils.isEmpty(pw)) {
            onEmptyPw()
        } else if (TextUtils.isEmpty(pw2)) {
            onEmptyPw2()
        } else if (encode(pw) == pw1) {
            when{
                !pwstyle.matches(pw2) -> {
                    onWrongstylePw()
                }
                else -> {
                    saveRegisterInfo(UName, pw2,context)
                    onSuccess()
            }
            }

        }else if (pw1 != null && !TextUtils.isEmpty(pw1) &&encode(pw) != pw1) {
            onPwError()
        }
        else {
            onUnknownUser()
        }

    }



}