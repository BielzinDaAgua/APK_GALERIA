package com.example.apk_galeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.apk_galeria.ui.theme.APKGaleriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APKGaleriaTheme {
                APKGaleriaApp()
            }
        }
    }
}

@Composable
fun APKGaleriaApp() {
    var currentIndex by remember { mutableIntStateOf(0) }
    val artworks = listOf(
        Artwork(
            id = 1,
            imageRes = R.drawable.obra,
            title = "Still Life of Blue Rose and Other Flowers",
            artist = "Owen Scott",
            year = 2021
        ),
        Artwork(
            id = 2,
            imageRes = R.drawable.obra2,
            title = "Another Artwork Title",
            artist = "Another Artist",
            year = 2020
        ),
        Artwork(
            id = 3,
            imageRes = R.drawable.obra3,
            title = "Third Artwork Title",
            artist = "Third Artist",
            year = 2019
        )
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagem da obra de arte
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val maxHeight = maxHeight
                Image(
                    painter = painterResource(id = artworks[currentIndex].imageRes),
                    contentDescription = artworks[currentIndex].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxHeight)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            // Descritor de obras de arte
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = artworks[currentIndex].title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${artworks[currentIndex].artist} (${artworks[currentIndex].year})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            // Controlador de exibição
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        currentIndex = when (currentIndex) {
                            0 -> artworks.size - 1
                            else -> currentIndex - 1
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Previous")
                }
                Button(
                    onClick = {
                        currentIndex = when (currentIndex) {
                            artworks.size - 1 -> 0
                            else -> currentIndex + 1
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

data class Artwork(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: Int
)
