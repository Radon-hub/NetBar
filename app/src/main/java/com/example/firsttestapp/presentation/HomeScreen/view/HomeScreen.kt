package com.example.firsttestapp.presentation.HomeScreen.view

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firsttestapp.presentation.ui.share.TopBar
import com.example.firsttestapp.R
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
import com.example.firsttestapp.presentation.HomeScreen.view.Materials.Cargo.DetailsBottomSheet
import com.example.firsttestapp.presentation.HomeScreen.viewmodel.HomeViewModel
import com.example.firsttestapp.presentation.ui.share.Theme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class HomeScreen {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun onCreate(){

        val context = LocalContext.current
        val viewModel = koinViewModel<HomeViewModel>()

        val baseItems by viewModel.cargoFlow.collectAsStateWithLifecycle()
        val selectedItem by viewModel.selectedCargo.collectAsStateWithLifecycle()

        val repeatCount = 1000 // Repeat base list many times
        val totalItems = baseItems.size * repeatCount
        val centerIndex = totalItems / 2


        val listState = rememberLazyListState(initialFirstVisibleItemIndex = 0)
        val scope = rememberCoroutineScope()
        // Auto-reset scroll to center when near edges
        LaunchedEffect(listState.firstVisibleItemIndex) {
            if (listState.firstVisibleItemIndex < baseItems.size ||
                listState.firstVisibleItemIndex > totalItems - baseItems.size
            ) {
                scope.launch {
                    listState.scrollToItem(centerIndex)
                }
            }
        }
        val color = Theme.colors

        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var showSheet by remember { mutableStateOf(false) }

        if (showSheet) {
            ModalBottomSheet(
                containerColor = Color.White,
                dragHandle = null,
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                DetailsBottomSheet(
                    cargoEntity = selectedItem,
                    onConfirm = {
                        selectedItem.let {

                            if(it.isSelected == null){
                                it.id?.let{
                                    viewModel.changeItemToSelected(id = it)
                                    Toast.makeText(context,"بار انتخاب شد ...", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                    Toast.makeText(context,"بار دیگری نمیتونی انتخاب کنی !", Toast.LENGTH_SHORT).show()
                            }

                        }
                    },
                    onDismiss = {
                        showSheet = false
                    }
                )
            }
        }

        Scaffold(
            topBar = {
                TopBar(
                    start = {

                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {  },
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "Back",
                            tint = Color.Unspecified
                        )

                    },
                    end = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {  },
                            painter = painterResource(R.drawable.help),
                            contentDescription = "Help",
                            tint = Color.Unspecified
                        )

                    }
                ) {
                    Text(
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        text = "لیست بارها"
                    )
                }
            },
            containerColor = Color.White,
            modifier = Modifier.background(Color.White)
        ) { innerPadding ->

            Column(modifier = Modifier
                .fillMaxSize()
                .background(color.backgroundGrayLight)
                .padding(innerPadding)
            ){

                Spacer(Modifier.height(50.dp))


                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    items(totalItems) { index ->
                        val item = baseItems[index % baseItems.size]
                        Materials.Cargo.Item(
                            model = item,
                            onItemClick = {

                                viewModel.setSelectedCargo(item)

                                scope.launch {
                                    showSheet = true
                                }

                            },
                            onUnSelectItemClick = {
                                viewModel.changeItemToUnSelected()
                            }
                        )
                    }

                }


            }

        }


    }


}