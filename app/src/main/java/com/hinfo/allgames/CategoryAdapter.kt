package com.hinfo.allgames

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hinfo.allgames.databinding.TabBinding
import kotlin.collections.ArrayList

class CategoryAdapter(click: (CategoryModel) -> Unit) : Adapter<CategoryAdapter.CategoryHolder>() {
    lateinit var list: ArrayList<CategoryModel>
    lateinit var context: Context
    var click = click
    lateinit var oldCard : CardView
    class CategoryHolder(itemView: TabBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        context = parent.context
        var binding = TabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {

        if (position == 0) {
            oldCard = holder.binding.card
            holder.binding.card.setCardBackgroundColor(Color.parseColor("#D1C4E9"))
        }

        holder.binding.apply {

            title.text = list.get(position).name

        }
        holder.itemView.setOnClickListener {

            changeColor(holder.binding.card)

            click.invoke(list.get(position))
        }
    }

    private fun changeColor(card: CardView) {

        oldCard.setCardBackgroundColor(Color.WHITE)
        oldCard = card

        card.setCardBackgroundColor(Color.parseColor("#D1C4E9"))

    }

    fun setArray(list: ArrayList<CategoryModel>) {
        this.list = list
    }

}