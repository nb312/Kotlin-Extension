package com.nb.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nb.ktextension.resource.*
import com.nb.ktextension.sharep.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spExtenion()
    }

    fun idNormal() {
        var context = baseContext
        var hello = context.resources.getString(R.string.hello)
        var green = context.resources.getColor(R.color.green)
        var fruit = context.resources.getStringArray(R.array.fruit)

        Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, R.string.hello, Toast.LENGTH_SHORT).show()

        print("Hello world")
        print(context.resources.getString(R.string.hello))


    }

    fun idExtension() {
        var hello = R.string.hello.id2String //obtain the 'Hello world'
        var green = R.color.green.id2Color // 'obtain the color green of your define'
        var fruit = R.array.fruit.id2StrList // obtain the array and convert to mutableList

        "Hello world".toast() // toast 'Hello world'
        R.string.hello.id2Toast() //toast 'Hello world'

        "Hello world".print() // print 'Hello world'
        R.string.hello.print() //print 'Hello world'
        "TAG".print("Hello word") // print 'TAG: Hello World'
        objectPrint("Hello world") // it depend on your current calling class, if it's MainActivity then will print `MainActivity: Hello World`
    }

    fun spExtenion() {
        "key1".getSPString()
        "key2".getSPBoolean()
        "key3".getSPFloat()
        "key4".getSPInt()
        "key5".getSPLong()
        "key1".putSPString("Hello")
        "key2".putSPBoolean(false)
        "key3".putSPFloat(1.2f)
        "key4".putSPInt(12)
        "key5".putSPLong(2323L)
        var v = "your_key".getSPString() //get the value with the key 'your_key'
        v.print() // you can use "your_key1".getSPString("--").print(),but someone maybe confuse with it.
        "your_key".putSPString("Hello world") //set the value of the key 'your_key' to Hello world.
        "your_key".getSPString("--").print() //get the value and print,the default value is '--',you could not must put it at all.
    }
}
