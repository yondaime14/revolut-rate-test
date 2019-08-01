package com.carllewis14.revoluttest.callback

import android.support.v7.util.DiffUtil
import com.carllewis14.revoluttest.data.model.rates.Currency

/**
 * Compare item in list and move to new position 0
 */
class CurrencyDiffCallback(private val mOldList: List<Currency>, private val mNewList: List<Currency>) : DiffUtil.Callback() {

    override fun getOldListSize() = mOldList.size

    override fun getNewListSize() = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        mOldList[oldItemPosition].code == mNewList[newItemPosition].code

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        mOldList[oldItemPosition].rate == mNewList[newItemPosition].rate

}