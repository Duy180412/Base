package com.example.demo.wiget.apdater

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demo.R
import com.example.demo.extension.Bindable
import com.example.demo.extension.cast
import com.example.demo.model.ItemHomeMenu
import com.example.demo.wiget.decoration.DecorationExt
import com.example.demo.wiget.viewholder.ItemHomeMenuViewHolder


class HomeMenuAdapter(private val ryc: RecyclerView) : RecyclerView.Adapter<ViewHolder>() {

    private var listApp: List<ItemHomeMenu> = emptyList()

    init {
        val spanCount = getSpanCountFromScreen()
        val decoration = DecorationExt().apply {
            setSpanCount(spanCount)
            setMaxWithDPI(getMaxWithDPI().toInt())
        }
        ryc.adapter = this
        ryc.layoutManager = GridLayoutManager(ryc.context, spanCount)
        ryc.addItemDecoration(decoration)
    }

    private fun getSpanCountFromScreen(): Int {
        return if (getMaxWithDPI() < 350) return 3 else 4
    }

    private fun getMaxWithDPI(): Float {
        val maxWithPixels = ryc.context.resources.displayMetrics.widthPixels
        val density = ryc.context.resources.displayMetrics.density
        return maxWithPixels / density
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemHomeMenuViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return listApp.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cast<Bindable<ItemHomeMenu>>()?.bind(listApp[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListApp(list: List<ItemHomeMenu>) {
        this.listApp = list
        notifyDataSetChanged()
    }
}

