package com.nb.ktextension.event

import java.io.Serializable

/**
 * Created by NieBin on 2018-08-01
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */
open class NBaseEvent() : Serializable {
    var eventType: Int = 0
}
