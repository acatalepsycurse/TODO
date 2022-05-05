package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tolist.view.*

class ListAdapter(
    private val lists: MutableList<List>
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tolist,
                parent,
                false
            )
        )
    }

    fun addToList(list: List) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }

    fun deleteMarkedLists() {
        lists.removeAll { list ->
            list.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToListTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvToListTitle.paintFlags = tvToListTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToListTitle.paintFlags = tvToListTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val curList = lists[position]
        holder.itemView.apply {
            tvToListTitle.text = curList.title
            cbDone.isChecked = curList.isChecked
            toggleStrikeThrough(tvToListTitle, curList.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToListTitle, isChecked)
                curList.isChecked = !curList.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}