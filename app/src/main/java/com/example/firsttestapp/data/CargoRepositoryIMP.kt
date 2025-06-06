package com.example.firsttestapp.data

import android.util.Log
import com.example.firsttestapp.core.network.DataHolder
import com.example.firsttestapp.core.network.KtorService
import com.example.firsttestapp.core.network.mapIfSuccess
import com.example.firsttestapp.core.network.safeApiCall
import com.example.firsttestapp.data.mapper.map
import com.example.firsttestapp.data.model.CargoResponse
import com.example.firsttestapp.data.model.CityResponse
import com.example.firsttestapp.data.repository.CargoRepository
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

class CargoRepositoryIMP(private val ktorService: KtorService) :
    CargoRepository {


    private val listOfCargos = listOf<CargoResponse>(
        CargoResponse(
            id = 1,
            city = CityResponse(
                1,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "کرمان",
                destinationState = "(استان کرمان)"
            ),
            weight = 2f,
            price = 3f
        ),
        CargoResponse(
            id = 2,
            city = CityResponse(
                2,
                origin = "شیراز",
                originState = "(استان فارس)",
                destination = "کرمان",
                destinationState = "(استان کرمان)"
            ),
            weight = 3f,
            price = 7f
        ),
        CargoResponse(
            id = 3,
            city = CityResponse(
                3,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "شیراز",
                destinationState = "(استان فارس)"
            ),
            weight = 1f,
            price = 6f
        ),
        CargoResponse(
            id = 4,
            city = CityResponse(
                4,
                origin = "تهران",
                originState = "(استان تهران)",
                destination = "رشت",
                destinationState = "(استان رشت)"
            ),
            weight = 2f,
            price = 5f
        ),
        CargoResponse(
            id = 5,
            city = CityResponse(
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


    override suspend fun getCargoList(): DataHolder<List<CargoEntity>> {

//       TODO: We replace this for mock data entries\

//        return safeApiCall<List<CargoResponse>> {
//            ktorService.client {
//                get("v1/get-cargos")
//            }
//        }

        // TODO : Mock data list to json
        val jsonString = Json.encodeToString(listOfCargos)

        delay(3000)

        return safeApiCall<List<CargoResponse>>(jsonString).mapIfSuccess {
            it.map{
                it.map()
            }
        }
    }

}