package com.nb.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nb.commonutil.extension.getSPBoolean
import com.nb.commonutil.extension.print
import com.nb.commonutil.extension.putSPBoolean
import com.nb.commonutil.extension.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "hello world".toast()
        "a".putSPBoolean(true)
        var a = "a".getSPBoolean(false)
        print("a = $a")
    }
}
