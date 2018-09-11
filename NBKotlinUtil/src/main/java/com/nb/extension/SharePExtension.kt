package com.icoinbay.app.extension

import com.blankj.utilcode.util.SPUtils
import com.nb.nbContext

/**
 * Created by NieBin on 18-8-17
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 *This String before the function is the key of the share preference.
 */

fun String.putSPString(content: String = "") {
    SPUtils.getInstance(nbContext.packageName).put(this, content)
}

fun String.putSPBoolean(content: Boolean = false) {
    SPUtils.getInstance(nbContext.packageName).put(this, content)
}

fun String.putSPInt(num: Int = 0) {
    SPUtils.getInstance(nbContext.packageName).put(this, num)
}

fun String.putSPLong(num: Long = 0L) {
    SPUtils.getInstance(nbContext.packageName).put(this, num)
}

fun String.putSPFloat(dou: Float = 0f) {
    SPUtils.getInstance(nbContext.packageName).put(this, dou)
}


/**
 * obtain the value from the share preference.
 * */

fun String.getSPString(default: String = ""): String {
    return SPUtils.getInstance(nbContext.packageName).getString(this, default)
}

fun String.getSPBoolean(default: Boolean = false): Boolean {
    return SPUtils.getInstance(nbContext.packageName).getBoolean(this, default)
}

fun String.getSPInt(default: Int = 0): Int {
    return SPUtils.getInstance(nbContext.packageName).getInt(this, default)
}

fun String.getSPLong(default: Long = 0): Long {
    return SPUtils.getInstance(nbContext.packageName).getLong(this, default)
}

fun String.getSPFloat(default: Float = 0f): Float {
    return SPUtils.getInstance(nbContext.packageName).getFloat(this, default)
}
