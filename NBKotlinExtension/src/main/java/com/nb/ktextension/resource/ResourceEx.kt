package com.nb.ktextension.resource

import android.widget.Toast
import com.nb.ktextension.NBExtensionUtil

/**
 * Created by NieBin on 2018-07-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


val Int.id2Color: Int
    get() = NBExtensionUtil.context.resources.getColor(this)

val Int.id2String: String
    get() = NBExtensionUtil.context.resources.getString(this)


val Int.id2TrimStr: String
    get() = NBExtensionUtil.context.resources.getString(this).trim()

val Int.id2StrList: MutableList<String>
    get() = NBExtensionUtil.context.resources.getStringArray(this).toMutableList()

val Int.id2StrArray: Array<String>
    get() = NBExtensionUtil.context.resources.getStringArray(this)

fun String.toast() {
    Toast.makeText(NBExtensionUtil.context, this, Toast.LENGTH_SHORT).show()
}

fun Int.id2Toast() {
    Toast.makeText(NBExtensionUtil.context, NBExtensionUtil.context.getString(this), Toast.LENGTH_SHORT).show()
}

/** print tag: content*/
fun String.print(content: String) {
    println("$this: $content")
}

fun Any.objectPrint(content: String) {
    println("${javaClass.simpleName}: $content")
}

fun String.print() {
    println(this)
}

/**print the string of id resource.*/
fun Int.print() {
    println(this.id2String)
}