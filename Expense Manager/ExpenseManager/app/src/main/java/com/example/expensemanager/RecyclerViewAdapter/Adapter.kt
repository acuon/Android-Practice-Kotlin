package com.example.expensemanager.RecyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.Database.DBHelper
import com.example.expensemanager.Fragments.Income
import com.example.expensemanager.Interface.ClickListener
import com.example.expensemanager.Model.Money
import com.example.expensemanager.R
import kotlinx.android.synthetic.main.item_layout.view.*
import android.R.id

class Adapter(private val list: ArrayList<Money>, private val clickListener: ClickListener): RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val money = list[position]
        holder.setData(money)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val view: View, private val clickListener: ClickListener): RecyclerView.ViewHolder(view) {

        internal fun setData(money: Money) {
            view.apply {
                tvDate.text = money.date
                tvDescription.text = money.description
                tvAmount.text = money.amount.toString()

                ibEdit.setOnClickListener {
                    clickListener.editClick(money, adapterPosition)
                }

                ibDelete.setOnClickListener {
                    clickListener.deleteClick(money, adapterPosition)
                }

//                itemLayout.setOnLongClickListener {
//                    clickListener.onClick(money, adapterPosition)
//                    return@setOnLongClickListener true
//                }
            }
        }

    }


}