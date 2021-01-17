package com.skfo763.component.bottomsheetdialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skfo763.base.extension.setTextWithVisibility
import com.skfo763.component.databinding.DialogMultiSelectBinding
import com.skfo763.component.databinding.HolderMultiSelectItemBinding

fun getTestableDialogItem() = MultiSelectDialog.Item(
    "http://www.pressm.kr/news/photo/202101/35837_23309_2236.jpg",
    "우리은행",
    bgColor = Color.parseColor("#FFFFFF"),
    titleColor = Color.parseColor("#323232")
)

class MultiSelectDialog : BottomSheetDialogFragment() {

    class Builder {
        private var data: List<Item>? = null

        fun setItem(data: List<Item>): Builder {
            this.data = data
            return this
        }

        fun build() = MultiSelectDialog().apply {
            setData(data)
        }
    }

    data class Item(
        val iconImageUrl: String,
        val title: String,
        val subtitle: String? = null,
        @ColorInt val bgColor: Int,
        @ColorInt val titleColor: Int,
        @ColorInt val subTitleColor: Int? = null
    )

    private lateinit var binding: DialogMultiSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMultiSelectBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialogContents.apply {
            adapter = Adapter()
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun setData(data: List<Item>?) = data?.let {
        (binding.dialogContents.adapter as? Adapter)?.setItem(it)
        binding.dialogContents.isVisible = true
    } ?: run {
        binding.dialogContents.isVisible = false
    }

    private class Adapter: RecyclerView.Adapter<ItemHolder>() {

        private val _itemList = mutableListOf<Item>()

        fun setItem(data: List<Item>) {
            _itemList.clear()
            _itemList.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            return ItemHolder(
                HolderMultiSelectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun getItemCount() = _itemList.size

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.setData(_itemList[position])
        }
    }

    private class ItemHolder(
        val binding: HolderMultiSelectItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Item) {
            setTextAndColor(binding.itemMultiSelectTitle, item.title, item.titleColor)
            setTextAndColor(binding.itemMultiSelectSubtitle, item.subtitle, item.subTitleColor)

            Glide.with(itemView.context).load(item.iconImageUrl).into(binding.itemMultiSelectIcon).clearOnDetach()
        }

        fun setTextAndColor(textView: TextView, text: String?, @ColorInt color: Int?) = with(textView) {
            this.setTextWithVisibility(text)
            setTextColor(color ?: currentTextColor)
        }
    }

}