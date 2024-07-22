package com.example.apk_galeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var currentIndex by remember { mutableStateOf(0) }
    val artworks = listOf(
        Artwork(
            id = 1,
            imageRes = R.drawable.obra, // Substitua pelo ID correto da imagem
            title = "Still Life of Blue Rose and Other Flowers",
            artist = "Owen Scott",
            year = 2021
        ),
        Artwork(
            id = 2,
            imageRes = R.drawable.obra2, // Substitua pelo ID correto da imagem
            title = "Another Artwork Title",
            artist = "Another Artist",
            year = 2020
        ),
        Artwork(
            id = 3,
            imageRes = R.drawable.obra3, // Substitua pelo ID correto da imagem
            title = "Third Artwork Title",
            artist = "Third Artist",
            year = 2019
        )
        // Adicione mais obras de arte conforme necessário
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Parede de obras de arte
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(BorderStroke(8.dp, MaterialTheme.colorScheme.onSurface), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = artworks[currentIndex].imageRes),
                    contentDescription = artworks[currentIndex].title,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface), shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descritor de obras de arte
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
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

            Spacer(modifier = Modifier.height(16.dp))

            // Controlador de exibição
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
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
                        .padding(end = 8.dp),
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
                        .padding(start = 8.dp),
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
