package com.nb.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.io.Serializable

/**
 * Created by NieBin on 2018-07-19
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */
abstract class NBaseAdapter<DataItem : NBaseDataItemInter, DataBindView : ViewDataBinding>(var context: Context?, private var dataList: MutableList<DataItem> = mutableListOf()) : RecyclerView.Adapter<NBaseVHolder<DataBindView>>() {
    private var mDataList: MutableList<DataItem> = mutableListOf()
    /**this must be init by user, if you do not init it.*/
    abstract var layoutId: Int
    var clickItemFunc: (item: DataItem?) -> Unit = {}
    var clickItemEnable = true

    init {
        mDataList.clear()
        mDataList.addAll(dataList)
        mDataList = mDataList.filterNot {
            it == null
        }.toMutableList()

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NBaseVHolder<DataBindView> {
        var bindView: DataBindView = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
        return NBaseVHolder(bindView)
    }

    override fun getItemCount(): Int = mDataList?.size
    override fun onBindViewHolder(vHolder: NBaseVHolder<DataBindView>, pos: Int) {
        var item = getPosItem(pos)
        if(clickItemEnable){
            vHolder.mBindView.root.setOnClickListener {
                clickItemFunc(item)
            }
        }
        vHolder.mBindView.onBindView(item)
    }

    private fun getPosItem(pos: Int): DataItem? {
        return if (mDataList.size == 0) {
            null
        } else {
            var p = pos % mDataList.size
            mDataList[p]
        }
    }

    /**append the data to its trail.*/
    fun appendChangeData(list: MutableList<DataItem>) {
        if (list.isEmpty()) return
        mDataList.addAll(list)
        notifyItemRangeChanged(mDataList.size, list.size)
    }

    /** clear the data then add all to it.*/
    open fun allChangeData(list: MutableList<DataItem>?) {
        mDataList.clear()
        mDataList.addAll(list ?: mutableListOf())
        notifyDataSetChanged()

    }

    abstract fun DataBindView.onBindView(dataItem: DataItem?)
}

class NBaseVHolder<DataBindView : ViewDataBinding>(var mBindView: DataBindView) : RecyclerView.ViewHolder(mBindView.root)


interface NBaseDataItemInter : Serializable