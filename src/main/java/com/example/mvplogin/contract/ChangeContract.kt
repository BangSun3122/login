package com.example.mvplogin.contract

import com.example.mvplogin.model.imodel.ILoginModel
import com.example.mvplogin.presenter.ipresenter.ILoginPresenter
import com.example.mvplogin.view.act.iview.ILoginView

interface ChangeContract {
    interface View : ILoginView
    interface Presenter : ILoginPresenter
    interface Model : ILoginModel
}