package com.callor.myapp.hellokotlin.util

import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by callor on 2017-11-16.
 */

fun AppCompatActivity.replace(@IdRes frameId : Int, fragment : android.support.v4.app.Fragment, tag : String?=null) {
    supportFragmentManager.beginTransaction().replace(frameId,fragment,tag).commit()
}