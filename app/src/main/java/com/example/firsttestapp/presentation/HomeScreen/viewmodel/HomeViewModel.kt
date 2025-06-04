package com.example.firsttestapp.presentation.HomeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
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


    private val _cargoFlow = MutableStateFlow(emptyList<CargoEntity>())
    val cargoFlow = _cargoFlow.asStateFlow()

    private val _selectedCargo = MutableStateFlow(CargoEntity())
    val selectedCargo = _selectedCargo.asStateFlow()

    init {
        _cargoFlow.value = listOfCargos
    }


    fun setSelectedCargo(cargo: CargoEntity){
        viewModelScope.launch {
            _selectedCargo.value = cargo
        }
    }

    fun changeItemToSelected(id: Int) {
        viewModelScope.launch {
            _cargoFlow.value = _cargoFlow.value.map {
                if (it.id == id) {
                    it.copy(isSelected = true)
                } else {
                    it.copy(isSelected = false)
                }
            }
        }
    }

    fun changeItemToUnSelected() {
        viewModelScope.launch {
            _cargoFlow.value = _cargoFlow.value.map {
                it.copy(isSelected = null)
            }
        }
    }




}