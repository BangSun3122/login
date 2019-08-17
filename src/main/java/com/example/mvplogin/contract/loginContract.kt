package com.example.mvplogin.contract

import com.example.mvplogin.model.imodel.ILoginModel
import com.example.mvplogin.presenter.ipresenter.ILoginPresenter
import com.example.mvplogin.view.act.iview.ILoginView


/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/07
 * @Description input description
 **/
interface loginContract {

    interface IView : ILoginView

    interface IPresenter : ILoginPresenter

    interface IModel : ILoginModel
}
