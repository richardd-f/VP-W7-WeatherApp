package com.felix.labw7_weatherapp.ui.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felix.labw7_weatherapp.R

@Composable
fun Homepage(
    viewModel: MainViewModel = viewModel()
){
    HomepageContent(
        query = viewModel.searchValue.collectAsState().value,
        onSearch = { viewModel.onSearchValueChange(it) },

        weatherUiState = viewModel.weatherUiState.collectAsState().value,

        location = "Denpasar",
        date = "September 24",
        updatedTime = "Updated as of 10:00 AM",
        weatherImgRes = R.drawable.cloud,
        weatherStatus = "Cloudy",
        temp = "31 C",
        pandaImgRes = R.drawable.panda_kehujanan,
        humidity = "49%",
        wind = "2km/h",
        feelsTemp = "32",
        rainFall = "0.0 mm",
        pressure = "1011 hPa",
        cloudsPercentage = "8%",
        sunriseTime = "06:00 AM",
        sunsetTime = "07:00 PM",
    )
}

@Composable
fun HomepageContent(
    query: String = "",
    onSearch: (String) -> Unit = {},
    weatherUiState: WeatherUiState,

    // Location, Date, Updated Time
    location: String,
    date: String,
    updatedTime: String,

    // Weather Logo, Temp, Panda
    weatherImgRes: Int,
    weatherStatus: String,
    temp: String,
    pandaImgRes: Int,

    // Detail Information
    humidity: String,
    wind: String,
    feelsTemp: String,
    rainFall: String,
    pressure: String,
    cloudsPercentage: String,

    // Sunrise, sunset
    sunriseTime: String,
    sunsetTime: String,
    
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
                    onValueChange = { onSearch(it) },
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
                        unfocusedLabelColor = Color.White
                    )
                )

                // Button
                Button(
                    onClick = { onSearch(query) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(50.dp)
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
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                            text = location,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    // Date Text
                    Text(
                        text = date,
                        color = Color.White,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    // Updated Time Text
                    Text(
                        text = updatedTime,
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
                            Image(
                                painter = painterResource(id = weatherImgRes),
                                contentDescription = "Weather Icon",
                                modifier = Modifier.size(70.dp) // Adjust size as needed
                            )
                            Text(
                                text = weatherStatus, // e.g., "Clear"
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = temp, // e.g., "31°C"
                                color = Color.White,
                                fontSize = 60.sp, // Larger font size for temperature
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
                                painter = painterResource(id = pandaImgRes),
                                contentDescription = "Panda illustration",
                                modifier = Modifier.size(200.dp) // Adjust size to fit your panda image well
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
                                value = humidity,
                                modifier = Modifier.weight(1f)
                            )
                            WeatherDetailItem(
                                imageRes = R.drawable.icon_wind,
                                label = "WIND",
                                value = wind,
                                modifier = Modifier.weight(1f)
                            )
                            WeatherDetailItem(
                                imageRes = R.drawable.icon_temp,
                                label = "FEELS LIKE",
                                value = "$feelsTemp°",
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
                                value = rainFall,
                                modifier = Modifier.weight(1f)
                            )
                            WeatherDetailItem(
                                imageRes = R.drawable.devices,
                                label = "PRESSURE",
                                value = pressure,
                                modifier = Modifier.weight(1f)
                            )
                            WeatherDetailItem(
                                imageRes = R.drawable.cloud,
                                label = "CLOUDS",
                                value = cloudsPercentage,
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
                            time = sunriseTime
                        )

                        // Sunset Item
                        SunTimeItem(
                            imageRes = R.drawable.icon_sunset,
                            label = "SUNSET",
                            time = sunsetTime
                        )
                    }
                }
            }

            else if(weatherUiState is WeatherUiState.Loading){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ){
                    Text(
                        text = "Loading..."
                    )
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

@Composable
private fun WeatherDetailItem(
    imageRes: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = Color.Black.copy(alpha = 0.25f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SunTimeItem(
    imageRes: Int,
    label: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = time,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}