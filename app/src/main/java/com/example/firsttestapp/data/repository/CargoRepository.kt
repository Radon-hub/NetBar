package com.example.firsttestapp.data.repository

import com.example.firsttestapp.core.network.DataHolder
import com.example.firsttestapp.domain.model.CargoEntity

interface CargoRepository {
    suspend fun getCargoList(): DataHolder<List<CargoEntity>>
}