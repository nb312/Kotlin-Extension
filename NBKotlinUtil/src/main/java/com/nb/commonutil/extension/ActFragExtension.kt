package com.nb.commonutil.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

/**
 * Created by NieBin on 2018-08-01
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

/**
 * this is for intent jump data from one activity to another
 * @d  d must be the serializable data.
 *
 */
const val KEY_DATA_INTENT = "key_data_intent"

fun <D : Serializable> Intent.putCurrentData(d: D) {
    this.putExtra(KEY_DATA_INTENT, d)
}

fun <D : Serializable> Activity.putCurrentData(d: D) {
    this.intent.putExtra(KEY_DATA_INTENT, d)
}

/**
 * this is can be obtain the data from current intent that jump from the other activity.
 * */
fun <D : Serializable> Intent.getCurrentData(): D {
    return this.getSerializableExtra(KEY_DATA_INTENT) as D
}

fun <D : Serializable> Activity.getCurrentData(): D {
    return this.intent.getSerializableExtra(KEY_DATA_INTENT) as D
}


/**jump activity but no data.*/
fun <T> Activity.startAct(act: Class<T>) {
    var intent = Intent(this, act)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

/**
 * if you want to put some data in the intent,you should be taking the data serializable.
 *
 */
fun <T, D : Serializable> Activity.startAct(act: Class<T>, d: D) {
    var intent = Intent(this, act)
    intent.putCurrentData(d)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

/**jump activity but no data.*/
fun <T> Context.startAct(act: Class<T>) {
    var intent = Intent(this, act)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

/**
 * if you want to put some data in the intent,you should be taking the data serializable.
 *
 */
fun <T, D : Serializable> Context.startAct(act: Class<T>, d: D) {
    var intent = Intent(this, act)
    intent.putCurrentData(d)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}



