package com.nb.commonutil.extension

import android.util.Log
import android.widget.Toast
import com.nb.commonutil.NBCommonUtil

/**
 * Created by NieBin on 2018-07-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


val Int.id2Color: Int
    get() = NBCommonUtil.context.resources.getColor(this)

val Int.id2String: String
    get() = NBCommonUtil.context.resources.getString(this).toString()


val Int.id2TrimStr: String
    get() = NBCommonUtil.context.resources.getString(this).toString().trim()

val Int.id2StrList: MutableList<String>
    get() = NBCommonUtil.context.resources.getStringArray(this).toMutableList()

val Int.id2StrArray: Array<String>
    get() = NBCommonUtil.context.resources.getStringArray(this)

fun String.toast() {
    Toast.makeText(NBCommonUtil.context, this, Toast.LENGTH_SHORT).show()
}

fun Int.id2Toast() {
    Toast.makeText(NBCommonUtil.context, NBCommonUtil.context.getString(this), Toast.LENGTH_SHORT).show()
}

/** print tag: content*/
fun String.print(content: String) {
    print("$this: $content")
}

fun Any.print(content: String) {
    println("${javaClass.simpleName}:$content")
}

fun String.print() {
    println(this)
}

/**print the string of id resource.*/
fun Int.print() {
    println(this.id2String)
}