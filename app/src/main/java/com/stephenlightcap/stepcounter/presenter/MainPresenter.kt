package com.stephenlightcap.stepcounter.presenter

/**
 * Created by Germex on 5/20/2017.
 */

interface MainPresenter {

    fun onResume()

    fun onItemClicked(position: Int)

    fun onDestroy()

    fun saveSteps(count: Int)

    fun deleteSteps()
}
