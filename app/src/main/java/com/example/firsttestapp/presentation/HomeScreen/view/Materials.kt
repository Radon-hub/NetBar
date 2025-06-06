package com.example.firsttestapp.presentation.HomeScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.firsttestapp.presentation.ui.share.Theme
import com.example.firsttestapp.R
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity
import com.example.firsttestapp.presentation.ui.share.CircleBox
import com.example.firsttestapp.presentation.ui.share.HorizontalLine
import com.example.firsttestapp.presentation.ui.share.SquareBox
import com.example.firsttestapp.presentation.ui.share.VerticalLine



object Materials {


    @Composable
    fun Loading() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Theme.colors.primaryMain
            )
        }
    }




    object Cargo{


        @Composable
        fun DetailsItem(modifier: Modifier = Modifier,title:String,subtitle: String) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                    Text(
                        modifier = Modifier.padding(end = 5.dp).fillMaxWidth().weight(1f),
                        text = "$title :",
                        color = Theme.colors.textSecondary,
                        fontWeight = FontWeight.W300,
                        style = Theme.typography.h2
                    )

                    Text(
                        modifier = Modifier,
                        text = subtitle,
                        color = Theme.colors.textPrimary,
                        style = Theme.typography.h1
                    )
                }

            }
        }

        @Composable
        fun DetailsBottomSheet(modifier: Modifier = Modifier,cargoEntity: CargoEntity,onConfirm:()->Unit,onDismiss:()->Unit) {
            Column(
                modifier = modifier.fillMaxWidth().padding(top = 20.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){

                    Text(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        text = "جزئیات بار",
                        color = Theme.colors.textPrimary,
                        style = Theme.typography.h1
                    )

                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onDismiss()
                            },
                        painter = painterResource(R.drawable.remove),
                        contentDescription = "Close",
                        tint = Color.Unspecified
                    )

                }

                HorizontalLine()
                Spacer(Modifier.height(15.dp))


                DetailsItem(
                    title = "مبدا",
                    subtitle = cargoEntity.city?.origin ?: ""
                )
                HorizontalLine(modifier.padding(horizontal = 16.dp))
                DetailsItem(
                    title = "مقصد",
                    subtitle = cargoEntity.city?.destination ?: ""
                )
                HorizontalLine(modifier.padding(horizontal = 16.dp))
                DetailsItem(
                    title = "بار",
                    subtitle = "سیمان"
                )
                HorizontalLine(modifier.padding(horizontal = 16.dp))
                DetailsItem(
                    title = "وزن",
                    subtitle = cargoEntity.weight.toString()
                )
                HorizontalLine(modifier.padding(horizontal = 16.dp))
                DetailsItem(
                    title = "بسته بندی",
                    subtitle = "گونی"
                )
                HorizontalLine(modifier.padding(horizontal = 16.dp))
                DetailsItem(
                    title = "تاریخ بارگیری",
                    subtitle = "30 شهریور"
                )
                HorizontalLine()



                Box(
                    modifier = modifier
                        .testTag("acceptButton")
                        .padding(PaddingValues(18.dp, 13.dp))
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            color = Theme.colors.primaryMain
                        )
                        .clickable{
                            onConfirm()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(PaddingValues(18.dp, 13.dp))
                    ){
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = " با ${cargoEntity.price} میلیون تومان کرایه میبرم ",
                            textAlign = TextAlign.Center,
                            color = Theme.colors.textWhite,
                            style = Theme.typography.h1
                        )
                    }
                }

            }
        }




        @Composable
        fun Item(modifier: Modifier = Modifier,model: CargoEntity,onItemClick:()->Unit,onUnSelectItemClick:()->Unit) {

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                    .clickable{
                        onItemClick()
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {

                Column(modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp)) {

                    model.isSelected?.let{ status ->

                        if(status){
                            SelectedCargoBox(origin = model.city?.origin  ?: "", destination = model.city?.destination ?: ""){
                                onUnSelectItemClick()
                            }
                            Spacer(Modifier.height(15.dp))
                        }

                    }


                    Row(Modifier.fillMaxWidth()){

                        Column(Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                            City(modifier = Modifier,isDestination = false, title = model.city?.origin ?: "", subtitle = model.city?.originState ?: "")
                            VerticalLine()
                            City(isDestination = true, title = model.city?.destination ?: "", subtitle = model.city?.destinationState ?: "")
                        }

                        model.isSelected?.let { status ->
                            if(!status){
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable { },
                                    painter = painterResource(R.drawable.lock),
                                    contentDescription = "Locked",
                                    tint = Color.Unspecified
                                )
                            }
                        }

                    }

                    HorizontalLine()

                    CargoDetails(weight = model.weight ?: 0.0f, price = model.price ?: 0.0f)


                }




            }

        }





        @Composable
        fun City(modifier: Modifier = Modifier, title:String, subtitle: String, isDestination: Boolean) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(isDestination){
                    SquareBox()
                }else{
                    CircleBox()
                }

                Text(
                    modifier = Modifier.testTag("originTag").padding(end = 5.dp),
                    text = title,
                    color = Theme.colors.textPrimary,
                    style = Theme.typography.h2
                )

                Text(
                    modifier = Modifier,
                    text = subtitle,
                    color = Theme.colors.textPrimary,
                    style = Theme.typography.h3
                )
            }

        }


        @Composable
        fun CargoDetails(modifier: Modifier = Modifier,weight:Float,price: Float) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){

                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { },
                    painter = painterResource(R.drawable.weight),
                    contentDescription = "Weight",
                    tint = Color.Unspecified
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = "${weight} تن ",
                    color = Theme.colors.textPrimary,
                    style = Theme.typography.h1
                )

                Text(
                    modifier = Modifier,
                    text = "${price} میلیون تومان ",
                    color = Theme.colors.textPrimary,
                    style = Theme.typography.h1
                )


            }
        }

        @Composable
        fun SelectedCargoBox(modifier: Modifier = Modifier,origin:String,destination:String,onRemoveClicked:()->Unit) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(
                    Theme.colors.backgroundGrayDark,
                    RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 12.dp)
            ){

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = " بار $origin به $destination انتخاب شده ",
                    color = Theme.colors.textPrimary,
                    style = Theme.typography.h3
                )

                Text(
                    modifier = Modifier.clickable{
                        onRemoveClicked()
                    },
                    text = "لغو بار",
                    color = Theme.colors.textCancel,
                    style = Theme.typography.h3
                )

            }

        }


    }



}