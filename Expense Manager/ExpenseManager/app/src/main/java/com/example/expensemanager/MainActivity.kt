package com.example.expensemanager

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.TabLayoutAdapter.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()

        ibAdd.setOnClickListener {
            val intent = Intent(baseContext, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setAdapter() {
        val adapter = TabAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Income"
                1 -> tab.text = "Expense"
                2 -> tab.text = "Balance"
            }
        }.attach()
    }
}