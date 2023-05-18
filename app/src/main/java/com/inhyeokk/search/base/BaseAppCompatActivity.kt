package com.inhyeokk.search.base

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printLifeCycleLog("onCreate savedInstanceState is null " + (savedInstanceState == null))
    }

    override fun onStart() {
        super.onStart()
        printLifeCycleLog("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        printLifeCycleLog("onRestart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        printLifeCycleLog("onRestoreInstanceState")
    }

    override fun onResume() {
        super.onResume()
        printLifeCycleLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        printLifeCycleLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        printLifeCycleLog("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        printLifeCycleLog("onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLifeCycleLog("onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        printLifeCycleLog("onConfigurationChanged")
    }

    private fun printLifeCycleLog(methodName: String) {
        Log.d(javaClass.simpleName, methodName)
    }

}
