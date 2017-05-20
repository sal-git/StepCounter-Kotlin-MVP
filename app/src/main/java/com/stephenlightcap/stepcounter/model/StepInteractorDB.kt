package com.stephenlightcap.stepcounter.model

import java.util.Date

/**
 * Created by Germex on 5/20/2017.
 */

interface StepInteractorDB {

    interface OnFinishedListener {
        fun onFinished(items: List<Steps>)
    }

    fun getAllSteps(listener: OnFinishedListener)

    fun addSteps(id: Long, date: Date, count: Int, listener: OnFinishedListener)
}
