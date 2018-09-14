package com.nb.example

import android.app.Application
import com.nb.ktextension.NBCommonUtil

/**
 * Created by NieBin on 18-9-14
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */
class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NBCommonUtil.context = this
    }
}
