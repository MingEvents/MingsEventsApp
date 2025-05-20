package com.example.mingseventsapp.layouts.pages.Event

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun BuyTicket(navController: NavHostController) {
    val event = UserLogged.selectedEvent
    val placesLeft = 3
    var cantidadEntradas by remember { mutableStateOf(1) }
    val asientosSeleccionados = remember { mutableStateListOf<Pair<Int, Int>>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd7e9fc))
            .padding(16.dp)
    ) {
        // Botón "Back" alineado arriba a la izquierda
        Button(
            onClick = { navController.navigate(Routes.MENU + "/0") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF14296F),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.TopStart)
                .shadow(4.dp, RoundedCornerShape(50))
        ) {
            Text("Back")
        }

        // Contenido centrado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Título del evento
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF7796CB))
                    .padding(horizontal = 32.dp, vertical = 20.dp)
            ) {
                Text(
                    text = event.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Selección de entradas
            Text("Selecciona la cantidad de entradas", fontWeight = FontWeight.Bold)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Button(
                    onClick = {
                        if (cantidadEntradas > 1) {
                            cantidadEntradas--
                            if (asientosSeleccionados.size > cantidadEntradas) {
                                asientosSeleccionados.removeLast()
                            }
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF405A94))
                ) {
                    Text("-")
                }

                Text(
                    text = "$cantidadEntradas / $placesLeft",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(
                    onClick = {
                        if (cantidadEntradas < placesLeft) cantidadEntradas++
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF405A94))
                ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Selecciona tus asientos:", fontWeight = FontWeight.Bold)

            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(12.dp))
                    .background(Color(0xFFE8F0FC))
                    .padding(12.dp)
            ) {
                for (row in 0 until 5) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        for (col in 0 until 5) {
                            val asiento = Pair(row, col)
                            val seleccionado = asiento in asientosSeleccionados

                            Box(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            seleccionado -> Color(0xFF5CB85C)
                                            else -> Color(0xFFB0C4DE)
                                        }
                                    )
                                    .clickable {
                                        if (seleccionado) {
                                            asientosSeleccionados.remove(asiento)
                                        } else if (asientosSeleccionados.size < cantidadEntradas) {
                                            asientosSeleccionados.add(asiento)
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de compra
            Button(
                onClick = {
                    // lógica de compra aquí
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF14296F)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text("Comprar Entradas", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}


