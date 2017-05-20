package com.stephenlightcap.stepcounter.model

import android.os.Handler
import android.util.Log

import java.text.SimpleDateFormat
import java.util.Date

import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults

/**
 * Created by Germex on 5/20/2017.
 */

class StepInteractorDBImplementation : StepInteractorDB {

    private val realm = Realm.getDefaultInstance()

    override fun getAllSteps(listener: StepInteractorDB.OnFinishedListener) {
        Handler().postDelayed({
            val query = realm.where(Steps::class.java)
            val results = query.findAll()
            listener.onFinished(results)
        }, 2000)
    }

    override fun addSteps(id: Long, date: Date, count: Int, listener: StepInteractorDB.OnFinishedListener) {
        var id = id
        val query = realm.where(Steps::class.java)
        val realmResults = query.findAll()
        id = realmResults.size.toLong()
        val finalId = id
        Handler().postDelayed({
            Log.d("id", finalId.toString())
            val steps = Steps(finalId, date, count)
            realm.executeTransaction { realm -> realm.copyToRealmOrUpdate(steps) }
            listener.onFinished(realmResults)
        }, 2000)


    }

}
