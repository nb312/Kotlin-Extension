package com.nb.view

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.DisplayMetrics
import com.nb.extension.R

/**
 * Created by NieBin on 2018-07-25
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

abstract class NBaseDialog<Param : BaseParam, DataBinding : ViewDataBinding>(context: Context) : Dialog(context, R.style.nb_custom_dialog) {
    protected lateinit var mBinding: DataBinding
    abstract var dialogParam: Param
    abstract var layoutId: Int

    open protected fun initView() {
        mBinding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        var view = mBinding.root
//        AutoUtils.auto(view)
        setContentView(view)
        val window = window
        val displayMetrics = DisplayMetrics()
        val layoutParams = window!!.attributes
        window.windowManager.defaultDisplay.getMetrics(displayMetrics)
        layoutParams.width = displayMetrics.widthPixels
        layoutParams.height = displayMetrics.heightPixels
        window.attributes = layoutParams
        setCancelable(dialogParam.isEnableCancel)
        setCanceledOnTouchOutside(dialogParam.isEnableCancel)
    }

    override fun show() {
        if (!isShowing) {
            initView()
            super.show()
        }
    }

}

open class BaseParam {
    /**wheather */
    var isEnableCancel = true
}
