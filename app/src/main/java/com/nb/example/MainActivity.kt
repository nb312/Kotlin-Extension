package com.nb.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.nb.ktextension.event.NBaseEvent
import com.nb.ktextension.event.registerEventBus
import com.nb.ktextension.event.sendEBusMessage
import com.nb.ktextension.event.unEventBus
import com.nb.ktextension.intent.getCurrentData
import com.nb.ktextension.intent.startAct
import com.nb.ktextension.resource.*
import com.nb.ktextension.sharep.*
import com.nb.ktextension.view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.Serializable

class MainActivity : AppCompatActivity(), IMainEvent {

    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun onMainEvent(event: MainEvent) {
        when (event.eventType) {
            MainEvent.TYPE_INIT -> "init activity".print()
            MainEvent.TYPE_REFRESH -> "refresh data".print()
            MainEvent.TYPE_FINISH -> "finish MainActivity".print()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerEventBus()
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

    fun actExtension() {
        startAct(MainActivity::class.java)
        var activity = this
        activity.startAct(MainActivity::class.java)
        var mainData = MainData("Hello world")
        startAct(MainActivity::class.java, mainData)
        var mainData2: MainData = getCurrentData()

    }

    fun event() {
        var mainEvent = MainEvent("say hello world")
        mainEvent.sendEBusMessage()
    }

    fun textViewEx() {
        var textView: TextView = TextView(this)
        textView.text = "Hello world"
        var content = textView.trimStr
        textView.change2Pwd(true)
        textView.afterChange {
            "only see the afterTextChanged function".print()
        }
        textView.changeWithOne {
            "the all functions of beforeTextChanged,onTextChanged,afterTextChanged are call only one function".print()
        }
        mutableListOf(textView, textView).hasOneEmpty()

    }

    override fun onDestroy() {
        super.onDestroy()
        unEventBus()
    }

}

data class MainData(var content: String) : Serializable
class E : NBaseEvent()
class MainEvent(var data: String = "") : NBaseEvent() {
    init {
        eventType = TYPE_INIT
    }

    companion object {
        /**initialize the main activity*/
        const val TYPE_INIT = 0x101
        /**refresh the data*/
        const val TYPE_REFRESH = 0x102
        /**finish the activity*/
        const val TYPE_FINISH = 0x103
    }
}

interface IMainEvent {
    fun onMainEvent(event: MainEvent)
}