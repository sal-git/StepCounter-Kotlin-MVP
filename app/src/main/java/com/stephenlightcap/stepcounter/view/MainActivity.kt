package com.stephenlightcap.stepcounter.view

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.stephenlightcap.stepcounter.R
import com.stephenlightcap.stepcounter.model.StepInteractorDBImplementation
import com.stephenlightcap.stepcounter.model.Steps
import com.stephenlightcap.stepcounter.presenter.MainPresenter
import com.stephenlightcap.stepcounter.presenter.MainPresenterImplementation
import org.w3c.dom.Text

/**
 * Created by Germex on 5/20/2017.
 */

class MainActivity : Activity(), MainView, View.OnClickListener, SensorEventListener {

    private var listView: ListView? = null
    private var addSteps: Button? = null
    private var currentSteps: TextView? = null
    private var presenter: MainPresenter? = null
    internal var sensorManager: SensorManager? = null
    internal var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listView = findViewById(R.id.list) as ListView
        addSteps = findViewById(R.id.button_add_current_steps) as Button
        currentSteps = findViewById(R.id.textview_current_steps) as TextView

        addSteps!!.setOnClickListener(this)

        presenter = MainPresenterImplementation(this, StepInteractorDBImplementation())
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_add_current_steps -> {
                Log.d("view", "View being pressed")
                presenter!!.saveSteps(Integer.parseInt(currentSteps!!.text.toString()))
            }
        }
    }

    override fun showCurrentSteps() {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        presenter!!.onDestroy()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        running = false
    }

    override fun onResume() {
        super.onResume()
        running = true
        val countSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (countSensor != null) {
            sensorManager!!.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_LONG).show()
        }

        presenter!!.onResume()
    }


    override fun setItems(items: List<Steps>) {
        listView!!.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (running) {
            currentSteps!!.text = sensorEvent.values[0].toInt().toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {

    }
}
