package com.example.mvplogin.model.imodel

interface OnLoginListener {
    fun onUserNameError() : Unit
    fun onUserNameEmpty() : Unit
    fun onPasswordError() : Unit
    fun onPasswordEmpty() : Unit
    fun onSuccess() : Unit
    fun onError() : Unit
}

