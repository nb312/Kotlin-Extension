package com.nb.commonutil.extension

import android.content.Context
import com.nb.commonutil.NBCommonUtil
import com.nb.commonutil.base.NBaseEvent
import org.greenrobot.eventbus.EventBus


/**
 * Created by NieBin on 2018-08-01
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */
/**注册event bus*/
fun Any?.registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().register(this)
    }
}

/**注销event bus*/

fun Any?.unEventBus() {
    if (EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().unregister(this)
    }
}

/**利用event bus 发送消息*/
fun NBaseEvent?.sendEBusMessage() {
    EventBus.getDefault().post(this)
}

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



