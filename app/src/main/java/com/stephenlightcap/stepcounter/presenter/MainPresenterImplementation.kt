package com.stephenlightcap.stepcounter.presenter

import com.stephenlightcap.stepcounter.model.StepInteractorDB
import com.stephenlightcap.stepcounter.model.Steps
import com.stephenlightcap.stepcounter.view.MainView

import java.util.Date

import io.realm.RealmQuery

/**
 * Created by Germex on 5/20/2017.
 */
class MainPresenterImplementation(mainView: MainView, stepInteractorDB: StepInteractorDB) : MainPresenter, StepInteractorDB.OnFinishedListener {
    var mainView: MainView? = null
    var stepInteractorDB: StepInteractorDB? = null

    init {
        this.mainView = mainView
        this.stepInteractorDB = stepInteractorDB
    }


    override fun onResume() {
        if (mainView != null) {
            mainView!!.showCurrentSteps()
        }

        stepInteractorDB.getAllSteps(this)
    }

    override fun onItemClicked(position: Int) {

    }

    override fun onDestroy() {
        mainView = null
    }

    override fun deleteSteps() {

    }

    override fun onFinished(items: List<Steps>) {
        if (mainView != null) {
            mainView!!.setItems(items)
        }
    }

    override fun saveSteps(count: Int) {
        stepInteractorDB.addSteps(0, Date(), count, this)
    }
}
