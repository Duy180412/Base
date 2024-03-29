package com.example.demo.wiget.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.LayoutItemMenuHomeBinding
import com.example.demo.extension.Bindable
import com.example.demo.extension.Command
import com.example.demo.extension.HasCommandCallback
import com.example.demo.model.ItemHomeMenu

class ItemHomeMenuViewHolder(
    private val parent: ViewGroup,
    private val binding: LayoutItemMenuHomeBinding = LayoutItemMenuHomeBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(binding.root), Bindable<ItemHomeMenu>, HasCommandCallback {
    override var onCommand: (Command) -> Unit = {}
    override fun bind(item: ItemHomeMenu) {
        binding.iconApp.setImageDrawable(item.iconApp)
        binding.nameIcon.text = item.nameApp
    }
}