package com.nb.ktextension.event

import org.greenrobot.eventbus.EventBus

/**
 * Created by NieBin on 18-9-14
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