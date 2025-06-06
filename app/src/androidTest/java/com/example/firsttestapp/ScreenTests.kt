package com.example.firsttestapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import com.example.firsttestapp.core.network.KtorService
import com.example.firsttestapp.data.CargoRepositoryIMP
import com.example.firsttestapp.data.repository.CargoRepository
import com.example.firsttestapp.domain.usecase.GetCargoListUseCase
import com.example.firsttestapp.presentation.HomeScreen.view.HomeScreen
import com.example.firsttestapp.presentation.HomeScreen.viewmodel.HomeViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class ScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

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
//                    viewModel { HomeViewModel() } // use a fake or real VM
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testMyComposable() {

        composeTestRule.setContent {
            HomeScreen().onCreate() // uses getViewModel()
        }

        composeTestRule
            .onNodeWithTag("closeIcon")
            .assertExists()


    }

    @Test
    fun testIsMyListScroll(){

        composeTestRule.setContent {
            HomeScreen().onCreate() // uses getViewModel()
        }

        composeTestRule.waitUntil(
            timeoutMillis = 3000,
            condition = {
                composeTestRule
                    .onNodeWithTag("lazyList")
                    .isDisplayed()
            }
        )

        composeTestRule
            .onNodeWithTag("lazyList")
            .assertExists()
            .performScrollToIndex(1000)

        composeTestRule
            .onNodeWithTag("originTagIndex1000")
            .assertIsDisplayed()

    }

    @Test
    fun testIsMyListClick(){

        composeTestRule.setContent {
            HomeScreen().onCreate() // uses getViewModel()
        }

        composeTestRule.waitUntil(
            timeoutMillis = 3000,
            condition = {
                composeTestRule
                    .onNodeWithTag("lazyList")
                    .isDisplayed()
            }
        )

        composeTestRule
            .onNodeWithTag("lazyList")
            .performScrollToIndex(1000)

        composeTestRule
            .onNodeWithTag("originTagIndex1000")
            .performClick()

    }

    @Test
    fun testIsMyBottomSheetDisplays(){

        composeTestRule.setContent {
            HomeScreen().onCreate() // uses getViewModel()
        }


        composeTestRule.waitUntil(
            timeoutMillis = 3000,
            condition = {
                composeTestRule
                    .onNodeWithTag("lazyList")
                    .isDisplayed()
            }
        )

        composeTestRule
            .onNodeWithTag("lazyList")
            .performScrollToIndex(1000)

        composeTestRule
            .onNodeWithTag("originTagIndex1000")
            .performClick()

        composeTestRule
            .onNodeWithTag("bottomSheet")
            .assertIsDisplayed()

    }
    @Test
    fun testIsMyBottomSheetDisplaysAndButtonClickAndDisappear(){

        composeTestRule.setContent {
            HomeScreen().onCreate() // uses getViewModel()
        }


        composeTestRule.waitUntil(
            timeoutMillis = 3000,
            condition = {
                composeTestRule
                    .onNodeWithTag("lazyList")
                    .isDisplayed()
            }
        )
        composeTestRule
            .onNodeWithTag("lazyList")
            .performScrollToIndex(1000)

        composeTestRule
            .onNodeWithTag("originTagIndex1000")
            .performClick()

        composeTestRule
            .onNodeWithTag("bottomSheet")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("acceptButton")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("bottomSheet")
            .assertIsNotDisplayed()

    }
}