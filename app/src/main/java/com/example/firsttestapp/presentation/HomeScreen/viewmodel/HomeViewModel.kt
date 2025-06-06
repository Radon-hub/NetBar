package com.example.firsttestapp.presentation.HomeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsttestapp.core.network.doOnError
import com.example.firsttestapp.core.network.doOnSuccess
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.usecase.GetCargoListUseCase
import com.example.firsttestapp.presentation.HomeScreen.intent.HomeEvents
import com.example.firsttestapp.presentation.HomeScreen.intent.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCargoListUseCase: GetCargoListUseCase
): ViewModel() {


    private val listOfCargos = MutableStateFlow(listOf<CargoEntity>())

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
            is HomeEvents.OnRemoveItemClick -> {
                changeItemToUnSelected()
            }
            is HomeEvents.OnDismiss -> {
                onDismiss()
            }
            is HomeEvents.OnShowSheet -> {
                onShow()
            }
        }
    }

    init {
        fetchData()
    }

    fun fetchData(){
        viewModelScope.launch {
            getCargoListUseCase.invoke()
                .doOnSuccess {

                    listOfCargos.value = it

                    state.value = state.value.copy(
                        showSheet = false,
                        cargoList = it,
                        selectedCargo = null
                    )
                }
                .doOnError {
                    // TODO : Here we can create the error reaction
            }
        }
    }

    private fun onShow(){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = true,
                cargoList = state.value.cargoList,
                selectedCargo = state.value.selectedCargo
            )
        }
    }

    private fun onDismiss(){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = false,
                cargoList = state.value.cargoList,
                selectedCargo = state.value.selectedCargo
            )
        }
    }

    private fun setSelectedCargo(cargo: CargoEntity){
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = state.value.showSheet,
                cargoList = state.value.cargoList,
                selectedCargo = cargo
            )
        }
    }

    private fun changeItemToSelected(id: Int) {
        viewModelScope.launch {

            state.value = state.value.copy(
                showSheet = state.value.showSheet,
                cargoList = state.value.cargoList.map {
                    if (it.id == id) {

                        val changeditem = it.copy(isSelected = true)

                        setSelectedCargo(changeditem)

                        it.copy(
                            changeditem.id,
                            changeditem.city,
                            changeditem.weight,
                            changeditem.price,
                            changeditem.isSelected
                        )

                    } else {
                        it.copy(isSelected = false)
                    }
                },
                selectedCargo = state.value.selectedCargo
            )

        }
    }

    private fun changeItemToUnSelected() {
        viewModelScope.launch {
            state.value = state.value.copy(
                showSheet = false,
                cargoList = listOfCargos.value,
                selectedCargo = null
            )
        }
    }




}