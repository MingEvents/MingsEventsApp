package com.example.mingseventsapp.layouts.pages.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatConvPreview() {
    ChatConv()
}

@Composable
fun ChatConv() {
    Box(
        modifier = Modifier
            .background(Color(0xFFd7e9fc))
            .fillMaxSize()
    ) {
       Column (
           modifier = Modifier
               .fillMaxWidth()
               .background(Color(0xFF063970))
               .clip(RoundedCornerShape(16.dp))
       ) {

       }
    }
}
