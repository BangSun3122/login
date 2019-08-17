package com.example.mvplogin.presenter


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.mvplogin.contract.loginContract
import com.example.mvplogin.model.imodel.ILoginModel
import com.example.mvplogin.model.imodel.OnLoginListener
import com.example.mvplogin.presenter.ipresenter.ILoginPresenter
import com.example.mvplogin.presenter.ipresenter.basepresenter
import com.example.mvplogin.view.act.LoginActivity
import com.example.mvplogin.view.act.iview.ILoginView
import kotlinx.android.synthetic.main.testlogin.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@SuppressLint("Registered")
class LoginPresenter (private var loginView:LoginActivity) : basepresenter<loginContract.IView, loginContract.IModel>(){
    var readpw:String? = null
    fun login(userName: String, password: String, context: Context) {
        readpw = readPsw(userName,context)
        val temp_pw:String=password
        if (TextUtils.isEmpty(userName)) {
            onEmptyUser()
        }else if (TextUtils.isEmpty(password)) {
            onEmptyPw()
        } else if (encode(temp_pw) == readpw) {
            saveLoginStatus(true,userName,context)
            val data = Intent()
            data.putExtra("isLogin", true)
            setResult(Activity.RESULT_OK, data)
            onSuccess()
        }else if (readpw != null && !TextUtils.isEmpty(readpw) &&encode(temp_pw) != readpw) {
            onPwError()
        }
        else {
           onUnExistUser()
        }

    }
    private fun readPsw(userName: String?,context: Context): String? {
        val sp = context.getSharedPreferences("regiInfo", AppCompatActivity.MODE_PRIVATE)
        return sp.getString(userName, "")
    }
    private fun saveLoginStatus(status: Boolean, UName: String?,context: Context) {
        val gsp =  context.getSharedPreferences("regiInfo", AppCompatActivity.MODE_PRIVATE)
        val editor = gsp.edit()
        editor.putBoolean("is Login", status)
        editor.putString("loginUserName", UName)
        editor.apply()
    }
    fun onEmptyUser() {
        loginView.onUserNameEmpty()
    }

    fun onEmptyPw() {
        loginView.onPasswordEmpty()
    }

    fun onPwError() {
        loginView.onPasswordError()
    }

    fun onUnExistUser() {
        loginView.onUserNameError()
    }

    fun onSuccess() {
        loginView.onSuccess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val UName = data.getStringExtra("Name")
            if (!TextUtils.isEmpty(UName)) {
                editText5!!.setText(UName)
                editText5!!.setSelection(UName!!.length)
            }
        }
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




}


