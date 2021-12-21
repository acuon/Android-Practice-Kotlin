package com.example.expensemanager.TabLayoutAdapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.expensemanager.Fragments.Balance
import com.example.expensemanager.Fragments.Expense
import com.example.expensemanager.Fragments.Income

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return Income()
            1 -> return Expense()
            2 -> return Balance()
        }
        return Income()
    }

}