package com.nb.extension

import android.widget.Toast
import com.nb.nbContext

/**
 * Created by NieBin on 2018-07-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


val Int.id2Color: Int
    get() = nbContext.resources.getColor(this, nbContext.theme)

val Int.id2String: String
    get() = nbContext.resources.getString(this).toString()


val Int.id2TrimStr: String
    get() = nbContext.resources.getString(this).toString().trim()

val Int.id2StrList: MutableList<String>
    get() = nbContext.resources.getStringArray(this).toMutableList()

val Int.id2StrArray: Array<String>
    get() = nbContext.resources.getStringArray(this)

fun String.toast() {
    Toast.makeText(nbContext, this, Toast.LENGTH_SHORT).show()
}

fun Int.id2Toast() {
    Toast.makeText(nbContext, nbContext.getString(this), Toast.LENGTH_SHORT).show()
}

fun String.print() {
    println(this)
}
/**print the string of id resource.*/
fun Int.print() {
    println(this.id2String)
}