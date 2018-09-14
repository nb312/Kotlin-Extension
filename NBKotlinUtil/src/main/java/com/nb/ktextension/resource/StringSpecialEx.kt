package com.nb.ktextension.resource

import android.content.Context
import com.nb.ktextension.NBCommonUtil


/**
 * Created by NieBin on 2018-08-01
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


/**将内容粘帖到粘帖板*/
fun String.copy2Board() {
    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = NBCommonUtil.context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager?
        clipboard!!.text = this
    } else {
        val clipboard = NBCommonUtil.context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager?
        val clip = android.content.ClipData.newPlainText("Copied Text", this)
        clipboard!!.primaryClip = clip
    }
}

/**获取粘帖板上的数据
 * copy the string from the clipboard.
 * */
fun getClipString(): String {
    return if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = NBCommonUtil.context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager?
        clipboard?.text.toString()
    } else {
        val clipboard = NBCommonUtil.context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager?
        clipboard?.primaryClip?.getItemAt(0)?.text.toString()
    }
}



