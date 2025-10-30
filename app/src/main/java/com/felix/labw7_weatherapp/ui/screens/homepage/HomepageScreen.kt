package com.felix.labw7_weatherapp.ui.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.felix.labw7_weatherapp.R
import com.felix.labw7_weatherapp.ui.screens.homepage.components.SunTimeItem
import com.felix.labw7_weatherapp.ui.screens.homepage.components.WeatherDetailItem

@Composable
fun Homepage(
    viewModel: MainViewModel = viewModel()
){
    HomepageContent(
        query = viewModel.searchValue.collectAsState().value,
        onSearchValueChange = {viewModel.onSearchValueChange(it)},
        onClickSearchButton = {viewModel.onClickSearchButton()},
        weatherUiState = viewModel.weatherUiState.collectAsState().value,
    )
}

@Composable
fun HomepageContent(
    query: String,
    onSearchValueChange: (String) -> Unit,
    onClickSearchButton: ()->Unit,
    weatherUiState: WeatherUiState,
    
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Overlay content on top
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Search Row
            Row(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TextField
                OutlinedTextField(
                    value = query,
                    onValueChange = { onSearchValueChange(it) },
                    placeholder = { Text("Enter city name...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon",
                            tint = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0x4FFFFFFF),
                        unfocusedBorderColor = Color(0x4FFFFFFF),

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedPlaceholderColor = Color.White.copy(alpha = 0.5f),
                        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.5f),

                        cursorColor = Color.White,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,

                        unfocusedContainerColor = Color.White.copy(alpha = 0.08f),
                        focusedContainerColor = Color.White.copy(alpha = 0.08f),
                    )
                )

                // Button
                Button(
                    onClick = { onClickSearchButton() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.08f),
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0x4FFFFFFF),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Icon(
                        modifier = Modifier
                            .width(20.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                    Text(
                        text = "Search",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            // If Ui state is success
            if(weatherUiState is WeatherUiState.Success){
                // Scrollable column
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item{
                        // Row Nama kota
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location icon",
                                tint = Color.White,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = weatherUiState.data.location,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        // Date Text
                        Text(
                            text = weatherUiState.data.date,
                            color = Color.White,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        // Updated Time Text
                        Text(
                            text = weatherUiState.data.updatedTime,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )

                        // Row Weather, Panda
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround // Distribute space between columns
                        ) {
                            // Left Column: Weather Icon, Status, and Temperature
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.weight(1f) // Takes half the width
                            ) {
                                AsyncImage(
                                    model = weatherUiState.data.weatherIconUrl,
                                    contentDescription = "Weather Icon",
                                    modifier = Modifier.size(70.dp),
                                )
                                Text(
                                    text = weatherUiState.data.weatherStatus,
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Text(
                                    text = weatherUiState.data.temp,
                                    color = Color.White,
                                    fontSize = 60.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            // Right Column: Panda Image
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.weight(1f) // Takes the other half the width
                            ) {
                                Image(
                                    painter = painterResource(weatherUiState.data.pandaImgRes),
                                    contentDescription = "Panda illustration",
                                    modifier = Modifier.size(200.dp)
                                )
                            }
                        }

                        // Detail Information
                        Column(
                            modifier = Modifier
                                .padding(top = 35.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Row 1
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                WeatherDetailItem(
                                    imageRes = R.drawable.icon_humidity,
                                    label = "HUMIDITY",
                                    value = weatherUiState.data.humidity,
                                    modifier = Modifier.weight(1f)
                                )
                                WeatherDetailItem(
                                    imageRes = R.drawable.icon_wind,
                                    label = "WIND",
                                    value = weatherUiState.data.wind,
                                    modifier = Modifier.weight(1f)
                                )
                                WeatherDetailItem(
                                    imageRes = R.drawable.icon_temp,
                                    label = "FEELS LIKE",
                                    value = weatherUiState.data.feelsTemp,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            // Row 2
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                WeatherDetailItem(
                                    imageRes = R.drawable.icon_umbrella,
                                    label = "RAIN FALL",
                                    value = weatherUiState.data.rainFall,
                                    modifier = Modifier.weight(1f)
                                )
                                WeatherDetailItem(
                                    imageRes = R.drawable.devices,
                                    label = "PRESSURE",
                                    value = weatherUiState.data.pressure,
                                    modifier = Modifier.weight(1f)
                                )
                                WeatherDetailItem(
                                    imageRes = R.drawable.cloud,
                                    label = "CLOUDS",
                                    value = weatherUiState.data.cloudsPercentage,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        // Sunrise sunset
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            // Sunrise Item
                            SunTimeItem(
                                imageRes = R.drawable.icon_sunrise,
                                label = "SUNRISE",
                                time = weatherUiState.data.sunriseTime
                            )

                            // Sunset Item
                            SunTimeItem(
                                imageRes = R.drawable.icon_sunset,
                                label = "SUNSET",
                                time = weatherUiState.data.sunsetTime
                            )
                        }
                    }

                }
            }

            // If Ui state is LOADING
            else if(weatherUiState is WeatherUiState.Loading){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Loading...",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }

            // If Ui state is Error
            else if(weatherUiState is WeatherUiState.Error){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Red Warning Icon
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error",
                            tint = Color.Red,
                            modifier = Modifier.size(48.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // "Oops!" Text
                        Text(
                            text = "Oops! Something went wrong",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Dynamic Error Message Text
                        Text(
                            text = weatherUiState.message,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // If Ui state is Empty
            else if(weatherUiState is WeatherUiState.Empty){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Search Icon
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Search for a city to get started",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 18.sp
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomepage(){
    Homepage()
}