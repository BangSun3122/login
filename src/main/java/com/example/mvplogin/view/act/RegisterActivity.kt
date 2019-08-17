package com.example.mvplogin.view.act

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvplogin.contract.registerContract
import com.example.mvplogin.presenter.RegisterPresenter
import com.example.mvplogin.view.act.iview.baseactivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.testregister.*

class RegisterActivity: baseactivity<registerContract.View, registerContract.Presenter>(),
    registerContract.View, View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testregister)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val presenter = RegisterPresenter(this)
        when(p0?.id)
        {
            R.id.button5 -> setBackToLogin()
            R.id.button4 -> presenter.register(editText2.text.toString(),editText6,editText3.text.toString(),editText4.text.toString(),this)
        }
    }
    fun setEmptyUser() {
        Toast.makeText(this@RegisterActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyPw() {
        Toast.makeText(this@RegisterActivity, "请输入密码", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyPw2() {
        Toast.makeText(this@RegisterActivity, "请再次输入密码", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyEmail(){
        Toast.makeText(this@RegisterActivity, "请输入邮箱", Toast.LENGTH_SHORT).show()
    }
    fun setWrongEmail(){
        Toast.makeText(this@RegisterActivity, "请输入正确的邮箱", Toast.LENGTH_SHORT).show()
    }

    fun setPwDifference() {
        Toast.makeText(this@RegisterActivity, "输入两次的密码不一样", Toast.LENGTH_SHORT).show()
    }

    fun setWrongPw() {
        Toast.makeText(this@RegisterActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
    }

    fun setExistUser() {
        Toast.makeText(this@RegisterActivity, "此用户名已存在", Toast.LENGTH_SHORT).show()
    }

    fun setExistEmail(){
        Toast.makeText(this@RegisterActivity, "此邮箱已注册", Toast.LENGTH_SHORT).show()
    }

    fun setSuccess(){
        Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
        this@RegisterActivity.finish()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    fun setBackToLogin(){
        this@RegisterActivity.finish()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }


}