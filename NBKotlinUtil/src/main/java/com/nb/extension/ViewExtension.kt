package com.icoinbay.app.extension

import android.text.*
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView


/**
 * Created by NieBin on 2018-07-31
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

val TextView?.trimStr: String
    get() = this?.text.toString().trim()


fun <TV : TextView?> MutableList<TV>.hasOneEmpty(): Boolean {
    for (tv in this) {
        if (tv.trimStr.isNullOrEmpty()) return true
    }
    return false
}

fun <TV : View> MutableList<TV>.setClick(click: () -> Unit) {
    for (tv in this) {
        tv.setOnClickListener {
            click()
        }
    }
}

/**改变text view 的显示状态 密码或者明文 ;true password ,false text*/
fun TextView.change2Pwd(isPwd: Boolean) {
    this.inputType = if (!isPwd) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
}

/**只使用最后一个变化方法，然后执行*/
fun TextView?.afterChange(checkEmpty: () -> Unit) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            checkEmpty()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    })
}

/**使整个集合都只执行after change方法*/
fun <TV : TextView> MutableList<TV>.afterChange(change: () -> Unit) {
    for (tv in this) {
        tv.afterChange(change)
    }
}

/**所有变化都执行一个方法*/
fun TextView?.changeWithOne(change: () -> Unit) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            change
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            change
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            change
        }

    })
}

/**设置富文本
 * @param specialPos 正常位置的 开始位置和
 * */
fun TextView.setTextSizeColors(content: String,
                               specialColor: Int,
                               specialPos: MutableList<Int>,
                               specialSize: Int = 34) {
    val style = SpannableStringBuilder(content)
    style.setSpan(AbsoluteSizeSpan(specialSize), specialPos[0], specialPos[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    style.setSpan(ForegroundColorSpan(specialColor), specialPos[0], specialPos[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    text = style
}

/**使整个集合都只执行changeWithOne方法*/
fun <TV : TextView> MutableList<TV>.changeWithOne(change: () -> Unit) {
    for (tv in this) {
        tv.changeWithOne(change)
    }
}



