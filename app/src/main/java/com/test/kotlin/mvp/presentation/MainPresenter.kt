package com.test.kotlin.mvp.presentation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.test.kotlin.R
import com.test.kotlin.domain.interactor.GetStateList
import com.test.kotlin.mvp.view.MainView
import java.net.UnknownHostException

@SuppressLint("CheckResult")
@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    private val mGetStateList: GetStateList by inject()

    fun load(){
        mGetStateList.execute(
            {
                viewState.show(it)
            },
            {
                if (it is UnknownHostException) {
                    viewState.showMessage(R.string.network_error)
                } else viewState.showMessage(R.string.unknown_error)
                it.printStackTrace()
            },
            {
                viewState.loading(it)
            }
        )
    }

}