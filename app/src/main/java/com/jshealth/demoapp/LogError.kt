package com.jshealth.demoapp

import android.util.Log

class LogError {

    companion object{
        fun set(key :String,value :String){
            Log.e(key,value)
        }
        fun set(value :String){
            Log.e("test",value)
        }
    }
}