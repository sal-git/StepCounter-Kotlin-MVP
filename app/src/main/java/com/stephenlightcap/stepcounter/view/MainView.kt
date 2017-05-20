package com.stephenlightcap.stepcounter.view

import com.stephenlightcap.stepcounter.model.Steps

/**
 * Created by Germex on 5/20/2017.
 */

interface MainView {

    fun showCurrentSteps()

    fun setItems(items: List<Steps>)

    fun showMessage(message: String)
}
