package com.example.midmorningintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var mBtnSms:Button
    lateinit var mBtnEmail:Button
    lateinit var mBtnCamera:Button
    lateinit var mBtnShare:Button
    lateinit var mBtnMpesa:Button
    lateinit var mBtnCall:Button
    lateinit var mBtnWebsite:Button
    lateinit var mBtnMap:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnSms = findViewById(R.id.mBtnSms)
        mBtnEmail = findViewById(R.id.mBtnEmail)
        mBtnCamera = findViewById(R.id.mBtnCamera)
        mBtnShare = findViewById(R.id.mBtnShare)
        mBtnMpesa = findViewById(R.id.mBtnMpesa)
        mBtnCall = findViewById(R.id.mBtnCall)
        mBtnWebsite = findViewById(R.id.mBtnWebsite)
        mBtnMap = findViewById(R.id.mBtnMap)
        mBtnSms.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0714000000")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there...")
            startActivity(intent)
        }
        mBtnEmail.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir, following the job adv.....")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        mBtnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        mBtnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app on https//www...!")
            startActivity(shareIntent)
        }
        mBtnMpesa.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        mBtnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0714000000"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }
        mBtnWebsite.setOnClickListener {
            val tembea = Intent(this@MainActivity,WebsiteActivity::class.java)
            startActivity(tembea)
        }
        mBtnMap.setOnClickListener {
            var ramani = Intent(this, MapsActivity::class.java)
            startActivity(ramani)
        }
    }
}