package com.nb.commonutil.extension

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri


/**
 * Created by NieBin on 2018-08-06
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

fun Context.link2GooglePlay(app_id: String) {
    val intent = Intent(ACTION_VIEW)
    intent.data = Uri.parse("market://details?id=$app_id")
    startActivity(intent)
}