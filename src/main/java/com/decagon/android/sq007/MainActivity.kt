package com.decagon.android.sq007

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.decagon.android.sq007.utils.NetworkLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkLiveData.init(application)

        if (NetworkLiveData.isNetworkAvailable()) {
            Toast.makeText(applicationContext, "Connection available", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                applicationContext, "Connection is not available at the moment",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
