package com.example.firsttestapp.domain.usecase

import com.example.firsttestapp.core.network.DataHolder
import com.example.firsttestapp.core.network.UseCase
import com.example.firsttestapp.domain.model.CargoEntity


fun interface GetCargoListUseCase: suspend () -> DataHolder<List<CargoEntity>>,UseCase
