package com.iagora.wingman.market.features.add_product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.Util.convertToLong
import com.iagora.wingman.market.features.add_product.databinding.ItemListVariantPriceBinding

class AddProductAdapter : BaseListAdapter<AddProductFragment.Companion.TempListVariant>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {


    private var onClickDeleteItem: ((AddProductFragment.Companion.TempListVariant) -> Unit)? = null
    fun setonClickDeleteItem(listener: (AddProductFragment.Companion.TempListVariant) -> Unit) {
        onClickDeleteItem = listener
    }

    inner class AddProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListVariantPriceBinding>(
            ItemListVariantPriceBinding.inflate(inflater, parent, false)
        ) {


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(data: AddProductFragment.Companion.TempListVariant) {


            binding.apply {
                ivDeleteVariant.apply {
                    isEnabled = this@AddProductAdapter.itemCount > 1
                    setImageDrawable(
                        if (this@AddProductAdapter.itemCount == 1) resources.getDrawable(
                            R.drawable.ic_close,
                            null
                        ) else resources.getDrawable(R.drawable.ic_close_round_red, null)
                    )
                    setOnClickListener {
                        onClickDeleteItem?.let { delete ->
                            delete(data)
                        }
                        tilPrice.editText?.text?.clear()
                        tilVariantScale.editText?.text?.clear()
                        this@AddProductAdapter.notifyItemRemoved(adapterPosition)
                    }
                }


                tilVariantScale.apply {
                    editText?.addTextChangedListener {
                        data.variant = it?.convertToLong()
                    }
                    data.variant = editText?.text?.convertToLong()
                }.isVisible =
                    data.unit == itemView.resources.getString(R.string.text_gram)

                tilPrice.apply {
                    data.price = editText?.text?.convertToLong()
                }.editText?.addTextChangedListener {
                    data.price = it?.convertToLong()
                }
            }
        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = AddProductViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddProductViewHolder -> holder.bind(getItem(position))
        }
    }
}