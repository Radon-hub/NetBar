package com.example.firsttestapp.presentation.HomeScreen.intent

import com.example.firsttestapp.domain.model.CargoEntity

sealed class HomeEvents{
    data class OnItemClick(val item: CargoEntity): HomeEvents()
    data class OnAddItemClick(val item: CargoEntity): HomeEvents()
    data object OnRemoveItemClick: HomeEvents()
    data object OnDismiss: HomeEvents()
    data object OnShowSheet: HomeEvents()
}