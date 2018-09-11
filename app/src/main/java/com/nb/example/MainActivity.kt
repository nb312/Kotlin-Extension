package com.nb.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nb.nbContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nbContext = this
    }
}
