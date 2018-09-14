package com.nb.ktextension.sharep

import android.content.SharedPreferences
import com.nb.ktextension.NBCommonUtil

/**
 * Created by NieBin on 18-8-17
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 *This String before the function is the key of the share preference.
 */

private val SP: SharedPreferences
    get() = NBCommonUtil.context.getSharedPreferences(NBCommonUtil.context.packageName, 0)

private val editor: SharedPreferences.Editor
    get() = SP.edit()

fun String.putSPString(content: String = "") {
    editor.putString(this, content).apply()
}

fun String.putSPBoolean(value: Boolean = false) {
    editor.putBoolean(this, value).apply()
}

fun String.putSPInt(num: Int = 0) {
    editor.putInt(this, num).apply()
}

fun String.putSPLong(num: Long = 0L) {
    editor.putLong(this, num).apply()
}

fun String.putSPFloat(value: Float = 0f) {
    editor.putFloat(this, value).apply()
}


/**
 * obtain the value from the share preference.
 * */

fun String.getSPString(default: String = ""): String {
    return SP.getString(this, default)
}

fun String.getSPBoolean(default: Boolean = false): Boolean {
    return SP.getBoolean(this, default)
}

fun String.getSPInt(default: Int = 0): Int {
    return SP.getInt(this, default)
}

fun String.getSPLong(default: Long = 0): Long {
    return SP.getLong(this, default)
}

fun String.getSPFloat(default: Float = 0f): Float {
    return SP.getFloat(this, default)
}
