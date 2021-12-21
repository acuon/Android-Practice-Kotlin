package com.example.expensemanager_mvvm.tabLayoutAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.expensemanager_mvvm.fragments.BalanceFragment
import com.example.expensemanager_mvvm.fragments.ExpenseFragment
import com.example.expensemanager_mvvm.fragments.IncomeFragment

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return IncomeFragment()
            1 -> return ExpenseFragment()
            2 -> return BalanceFragment()
        }
        return IncomeFragment()
    }

}