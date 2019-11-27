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
import android.graphics.Bitmap
import android.R.layout
import android.view.View.MeasureSpec

import android.content.Intent

import android.net.Uri
import android.widget.Toast

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var MY_PERMISSIONS_REQUEST_READ_PHONE_STATE=120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var phone_number=getMyPhoneNO()
        snackbarMesssage(parentView,phone_number)
        layoutToBitmap()
      //  opneWhatappChat("8200900411","hello nice ti miss you")

    }
fun opneWhatappChat(mobile:String,message:String){

    try {
        val whatsAppRoot = "http://api.whatsapp.com/"
        val number =
            "send?phone=+91$mobile" //here the mobile number with its international prefix
        val text = "&text=$message"
        val uri = whatsAppRoot + number + text

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(uri)
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            applicationContext,
            "WhatsApp cannot be opened", Toast.LENGTH_SHORT
        ).show()
    }

}

 fun layoutToBitmap(){
     layout.setDrawingCacheEnabled(true)
     // this is the important code :)
     // Without it the view will have a dimension of 0,0 and the bitmap will be null

     layout.measure(
         MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
         MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
     )

     layout.layout(0, 0, layout.getMeasuredWidth(), layout.getMeasuredHeight())

     layout.buildDrawingCache(true)
     val b = Bitmap.createBitmap(layout.getDrawingCache())
     layout.setDrawingCacheEnabled(false)
     img.setImageBitmap(b)// clear drawing cache
     var url=saveImageExternal(b)
     //shareImageUri(url!!)
 }


    /*====================================================*/

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

  fun shareImageUri(uri:Uri){
    var intent =Intent(android.content.Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.setType("image/png")
    startActivity(intent)
}

    private fun saveImageExternal(image: Bitmap): Uri? {
        //TODO - Should be processed in another thread
        var uri: Uri? = null
        try {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png")
            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.close()
            uri = Uri.fromFile(file)
        } catch (e: IOException) {
            Log.d(
                "test",
                "IOException while trying to write file for sharing: " + e.localizedMessage
            )
        }

        return uri
    }
}

