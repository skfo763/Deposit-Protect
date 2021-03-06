package com.skfo763.component.bottomsheetdialog

import android.annotation.SuppressLint
import android.app.Dialog
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jakewharton.rxbinding4.view.clicks
import com.skfo763.base.extension.logException
import com.skfo763.base.extension.setTextWithVisibility
import com.skfo763.component.databinding.DialogMultiSelectBinding
import com.skfo763.component.databinding.HolderMultiSelectItemBinding
import com.skfo763.component.extension.plusAssign
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

fun getFlatWhiteDialogItem(bankId: String, title: String, iconImageUrl: String) = MultiSelectDialog.Item(
    bankId,
    iconImageUrl,
    title,
    bgColor = Color.parseColor("#FFFFFF"),
    titleColor = Color.parseColor("#323232")
)

class MultiSelectDialog : BottomSheetDialogFragment() {

    class Builder {
        private var title: String = ""
        private var data: List<Item> = listOf()
        private var onItemClicked: ((BottomSheetDialogFragment, Item) -> Unit)? = null
        private var onButtonClicked: ((BottomSheetDialogFragment) -> Unit)? = null

        fun setTitle(titleText: String): Builder {
            this.title = titleText
            return this
        }

        fun setItem(data: List<Item>): Builder {
            this.data = data
            return this
        }

        fun setOnItemClickListener(onItemClicked: ((BottomSheetDialogFragment, Item) -> Unit)): Builder {
            this.onItemClicked = onItemClicked
            return this
        }

        fun onMoreButtonClickListener(onButtonClicked: ((BottomSheetDialogFragment) -> Unit)): Builder {
            this.onButtonClicked = onButtonClicked
            return this
        }

        fun build() = MultiSelectDialog().apply {
            title = this@Builder.title
            items = this@Builder.data
            onItemClick = onItemClicked
            onButtonClicked = this@Builder.onButtonClicked
        }
    }

    data class Item(
        val id: String,
        val iconImageUrl: String,
        val title: String,
        val subtitle: String? = null,
        @ColorInt val bgColor: Int,
        @ColorInt val titleColor: Int,
        @ColorInt val subTitleColor: Int? = null
    )

    private lateinit var binding: DialogMultiSelectBinding
    private var onItemClick: ((BottomSheetDialogFragment, Item) -> Unit)? = null
    private var onButtonClicked: ((BottomSheetDialogFragment) -> Unit)? = null
    private var title: String = ""
    private var items = listOf<Item>()
    private val itemAdapter = Adapter()

    private var disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMultiSelectBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("AutoDispose")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialogContents.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        disposable += itemAdapter.itemClickSubject.toFlowable(BackpressureStrategy.BUFFER)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
               onItemClick?.invoke(this, it)
            }) {
                logException(it)
            }

        disposable += binding.searchOther.clicks()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onButtonClicked?.invoke(this)
            }) {
                logException(it)
            }

        setData(items)
    }

    private fun setData(data: List<Item>) = data.let {
        (binding.dialogContents.adapter as? Adapter)?.setItem(it)
        binding.dialogContents.isVisible = true
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }

    private class Adapter: RecyclerView.Adapter<ItemHolder>() {

        private val _itemList = mutableListOf<Item>()
        val itemClickSubject: Subject<Item> = BehaviorSubject.create()

        fun setItem(data: List<Item>) {
            _itemList.clear()
            _itemList.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            return ItemHolder(
                HolderMultiSelectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ).apply {
                itemView.setOnClickListener {
                    itemClickSubject.onNext(_itemList[adapterPosition])
                }
            }
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