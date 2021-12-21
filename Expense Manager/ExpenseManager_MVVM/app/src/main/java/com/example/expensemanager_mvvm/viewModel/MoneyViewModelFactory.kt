package com.example.expensemanager_mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager_mvvm.repository.MoneyRepo

class MoneyViewModelFactory(private val repo: MoneyRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoneyViewModel(repo) as T
    }

}