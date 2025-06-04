package com.example.firsttestapp.presentation.HomeScreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
import com.example.firsttestapp.presentation.HomeScreen.intent.HomeEvents
import com.example.firsttestapp.presentation.HomeScreen.intent.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val listOfCargos = listOf<CargoEntity>(
        CargoEntity(
            id = 1,
            city = CityEntity(
                1,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "کرمان",
                destinationState = "(استان کرمان)"
            ),
            weight = 2f,
            price = 3f
        ),
        CargoEntity(
            id = 2,
            city = CityEntity(
                2,
                origin = "شیراز",
                originState = "(استان فارس)",
                destination = "کرمان",
                destinationState = "(استان کرمان)"
            ),
            weight = 3f,
            price = 7f
        ),
        CargoEntity(
            id = 3,
            city = CityEntity(
                3,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "شیراز",
                destinationState = "(استان فارس)"
            ),
            weight = 1f,
            price = 6f
        ),
        CargoEntity(
            id = 4,
            city = CityEntity(
                4,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "رشت",
                destinationState = "(استان رشت)"
            ),
            weight = 2f,
            price = 5f
        ),
        CargoEntity(
            id = 5,
            city = CityEntity(
                5,
                origin = "تفرش",
                originState = "(استان مرکزی)",
                destination = "رشت",
                destinationState = "(استان رشت)"
            ),
            weight = 3f,
            price = 9f
        )
    )


    private val state = MutableStateFlow(HomeScreenState())
    val homeState = state.asStateFlow()


    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnItemClick -> {
                setSelectedCargo(event.item)
            }
            is HomeEvents.OnAddItemClick -> {
                event.item.id.let { id ->
                    id?.let {
                        changeItemToSelected(it)
                    }
                }
            }
            is HomeEvents.OnDismiss -> {
                onDismiss()
            }
            is HomeEvents.OnRemoveItemClick -> {
                changeItemToUnSelected()
            }
            is HomeEvents.OnShowSheet -> {
                onShow()
            }
        }
    }

    init {
        state.value = state.value.copy(
            showSheet = false,
            cargoList = listOfCargos,
            selectedCargo = null
        )
    }

    fun onShow(){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = true,
                cargoList = state.value.cargoList,
                selectedCargo = state.value.selectedCargo
            )
        }
    }

    fun onDismiss(){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = false,
                cargoList = state.value.cargoList,
                selectedCargo = state.value.selectedCargo
            )
        }
    }

    fun setSelectedCargo(cargo: CargoEntity){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = state.value.showSheet,
                cargoList = state.value.cargoList,
                selectedCargo = cargo
            )
        }
    }

    fun changeItemToSelected(id: Int) {
        viewModelScope.launch {

            state.value = state.value.copy(
                showSheet = state.value.showSheet,
                cargoList = state.value.cargoList.map {
                    if (it.id == id) {
                        it.copy(isSelected = true)
                    } else {
                        it.copy(isSelected = false)
                    }
                },
                selectedCargo = state.value.selectedCargo
            )

        }
    }

    fun changeItemToUnSelected() {
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = false,
                cargoList = listOfCargos,
                selectedCargo = null
            )
        }
    }




}