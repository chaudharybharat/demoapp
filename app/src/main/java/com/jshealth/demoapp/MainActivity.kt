package com.jshealth.demoapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var MY_PERMISSIONS_REQUEST_READ_PHONE_STATE=120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var phone_number=getMyPhoneNO()
        snackbarMesssage(parentView,phone_number)

    }
    private fun getMyPhoneNO():String {
        var phoneNumber=""
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                MY_PERMISSIONS_REQUEST_READ_PHONE_STATE
            )
        } else {
            val tMgr = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            phoneNumber= tMgr.line1Number
            LogError.set("test","==>"+phoneNumber)
            LogError.set("test","phoneType==>"+tMgr.phoneType)
            LogError.set("test","deviceId==>"+tMgr.deviceId)
            LogError.set("test","simOperator==>"+tMgr.simOperator)

        }
        return phoneNumber
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val phoneNumber = getMyPhoneNO()
                LogError.set("test","==>"+phoneNumber)
                snackbarMesssage(parentView,phoneNumber)
            }
        }
    }
    fun snackbarMesssage(parentView: View, message:String){
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
            //  .setAction(R.string.snackbar_action, myOnClickListener)
            .show(); // Don’t forget to show!

    }
}

