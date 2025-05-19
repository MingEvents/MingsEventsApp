package com.example.mingseventsapp.layouts.pages.Event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged

@Composable
fun BuyTicket(navController: NavHostController) {
    var placesLeft = "" //cojer las plazas que quedan
    var event = "" // evento del que compra

    Box(modifier = Modifier
        .background(Color(0xFFd7e9fc))
        .fillMaxSize(),
       ) {
        Column {
            Button(
                onClick = {
                    navController.navigate(Routes.MENU + "/0")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF14296F),
                    contentColor = Color.White
                                                    ),
                modifier = Modifier
                    .padding(top = 50.dp, start = 20.dp)
                    .height(40.dp)
                    .align(Alignment.Start)
                  ) {
                Text(
                    text = "Back"
                    )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(250.dp)
                        .height(80.dp)
                        .background(Color(0xFF7796CB))
                        .align(Alignment.TopCenter)
                   ) {
                    Text(
                        text = UserLogged.selectedEvent.name,
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                                                                                                   ),
                        modifier = Modifier.align(Alignment.Center)
                        )
                }
            }

         /*   Row() {
                Te
            }*/
        }

    }
}