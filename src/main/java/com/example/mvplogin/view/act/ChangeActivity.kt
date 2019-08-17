package com.example.mvplogin.view.act

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvplogin.contract.ChangeContract
import com.example.mvplogin.presenter.ChangePresenter
import com.example.mvplogin.view.act.iview.baseactivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.testchange.*

class ChangeActivity: baseactivity<ChangeContract.View, ChangeContract.Presenter>(),
    ChangeContract.View, View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testchange)
        button7.setOnClickListener(this)
        button6.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var presenter = ChangePresenter(this)
        when(p0?.id)
        {
            R.id.button7 -> presenter.Change(editText7.text.toString(),editText8.text.toString(),editText9.text.toString(),this)
            R.id.button6 -> setGoBack()
        }
    }
    fun setEmptyUser() {
        Toast.makeText(this@ChangeActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }

    fun setEmptyPw() {
        Toast.makeText(this@ChangeActivity, "请输入密码", Toast.LENGTH_SHORT).show()
    }

    fun setEmptyPw2(){
        Toast.makeText(this@ChangeActivity, "请输入修改后新密码", Toast.LENGTH_SHORT).show()
    }
    fun setWrongstylePw() {
        Toast.makeText(this@ChangeActivity, "输入密码错误", Toast.LENGTH_SHORT).show()
    }

    fun setPwError() {
        Toast.makeText(this@ChangeActivity, "修改后密码格式错误", Toast.LENGTH_SHORT).show()
    }

    fun setUnknownUser() {
        Toast.makeText(this@ChangeActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
    }

    fun setSuccess() {
        Toast.makeText(this@ChangeActivity, "修改成功", Toast.LENGTH_SHORT).show()

        this@ChangeActivity.finish()
        val intent = Intent(this@ChangeActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    fun setGoBack(){

        this@ChangeActivity.finish()
        val intent = Intent(this@ChangeActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}