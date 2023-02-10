package com.example.wings.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wings.WingsApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEntryViewModel
        initializer {
            WingsViewModel(wingsApplication().container.wingsRepository)
        }
        initializer {
            ProductViewModel(wingsApplication().container.wingsRepository)
        }
        initializer {
            ProductDetailViewModel(
                this.createSavedStateHandle(),
                wingsApplication().container.wingsRepository
            )
        }
        initializer {
            CheckoutViewModel(
                wingsApplication().container.wingsRepository
            )
        }
    }
}

fun CreationExtras.wingsApplication(): WingsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WingsApplication)
