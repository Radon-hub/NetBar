package com.example.firsttestapp

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firsttestapp.core.network.KtorService
import com.example.firsttestapp.data.CargoRepositoryIMP
import com.example.firsttestapp.data.repository.CargoRepository
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
import com.example.firsttestapp.domain.usecase.GetCargoListUseCase
import com.example.firsttestapp.presentation.HomeScreen.intent.HomeEvents
import com.example.firsttestapp.presentation.HomeScreen.viewmodel.HomeViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

class ViewModelTests: KoinComponent {

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    val mockModel = CargoEntity(
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
    )


    @Before
    fun setupKoin() {
        stopKoin() // clean up previous instance

        startKoin {
            modules(
                module {
                    viewModelOf (::HomeViewModel)
                    factory { GetCargoListUseCase(get<CargoRepository>()::getCargoList) }
                    factory<CargoRepository> { CargoRepositoryIMP(get()) }
                    single<HttpClientEngine> { OkHttp.create() }
                    single { KtorService(get()) }
                }
            )
        }
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val viewMo: HomeViewModel by inject()
        viewModel = viewMo
        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }


    @Test
    fun `test view model state list size`() = runTest {

        println("Start test...")

        assert(viewModel.homeState.value.cargoList.isNotEmpty())

    }

    @Test
    fun `on remove selected item selected`() = runTest {

        println("Start test...")

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        viewModel.onEvent(HomeEvents.OnItemClick(mockModel))

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        viewModel.onEvent(HomeEvents.OnAddItemClick(mockModel))

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        viewModel.onEvent(HomeEvents.OnRemoveItemClick)

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        assertEquals(null,viewModel.homeState.value.selectedCargo?.id)
        assertEquals(null,viewModel.homeState.value.selectedCargo?.isSelected)

    }


    @Test
    fun `on item add to selected`() = runTest {

        println("Start test...")

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        viewModel.onEvent(HomeEvents.OnItemClick(mockModel))

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        viewModel.onEvent(HomeEvents.OnAddItemClick(mockModel))

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        assertEquals(4,viewModel.homeState.value.selectedCargo?.id)
        assertEquals(true,viewModel.homeState.value.selectedCargo?.isSelected)

    }

    @Test
    fun `on item click`() = runTest {

        println("Start test...")

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        viewModel.onEvent(HomeEvents.OnItemClick(mockModel))

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.selectedCargo?.id.toString() +  " | " +viewModel.homeState.value.selectedCargo?.isSelected)

        assertEquals(4,viewModel.homeState.value.selectedCargo?.id)


    }


    @Test
    fun `check the bottom sheet dismiss state event`() = runTest {


        println("Start test...")

        println(viewModel.homeState.value.showSheet.toString())

        viewModel.onEvent(HomeEvents.OnDismiss)

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.showSheet.toString())

        assert(viewModel.homeState.value.showSheet == false)





    }


    @Test
    fun `check the bottom sheet show state event`() = runTest {


        println("Start test...")

        println(viewModel.homeState.value.showSheet.toString())

        viewModel.onEvent(HomeEvents.OnShowSheet)

        testDispatcher.scheduler.advanceUntilIdle() // Let coroutines run

        println(viewModel.homeState.value.showSheet.toString())

        assert(viewModel.homeState.value.showSheet)





    }


}