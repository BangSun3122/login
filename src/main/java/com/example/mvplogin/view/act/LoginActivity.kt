package com.example.mvplogin.view.act

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvplogin.contract.loginContract
import com.example.mvplogin.model.loginModel
import com.example.mvplogin.presenter.LoginPresenter
import com.example.mvplogin.view.act.iview.baseactivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.finalact.*
import kotlinx.android.synthetic.main.testlogin.*



@SuppressLint("Registered")
class LoginActivity : baseactivity<loginContract.IView, loginContract.IPresenter>(),
    loginContract.IView, View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlogin)
        button2.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        var presenter = LoginPresenter(this@LoginActivity)
        when(p0?.id)
        {
            R.id.button2 -> presenter.login(getUserName(),getPassword(),this)
            R.id.button3 -> register()
            R.id.button5 -> change()
        }
    }
    fun getUserName(): String {
        return editText5!!.text.toString()
    }

    fun getPassword(): String {
        return editText!!.text.toString()
    }

    fun showToast(msg: String) {
        Toast.makeText(this@LoginActivity,msg,Toast.LENGTH_SHORT).show()
    }

    fun gotoMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
    fun onUserNameError() {
        showToast("onUserNameError--->")
    }
    fun onUserNameEmpty() {
        showToast("onUserNameEmpty--->")
    }

    fun onPasswordError() {
        showToast("onPasswordError--->")
    }
    fun onPasswordEmpty() {
        showToast("onPasswordEmpty--->")
    }

    fun onSuccess() {
        this@LoginActivity.finish()
        showToast("onSuccess--->")
        gotoMainActivity()
    }

    fun onError() {
        showToast("onError--->")
    }
    fun register(){
        this@LoginActivity.finish()
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)

    }
    fun change(){
        this@LoginActivity.finish()
        val intent = Intent(this@LoginActivity,ChangeActivity::class.java)
        startActivity(intent)

    }

}


