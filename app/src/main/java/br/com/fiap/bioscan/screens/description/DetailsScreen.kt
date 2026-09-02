package br.com.fiap.bioscan.screens.description

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.repository.PlantRepository
import br.com.fiap.bioscan.repository.RoomPlantRepository

@Composable
fun DetailsScreen(
    navController: NavController,
    email: String,
    speciesId: String
) {
    val context = LocalContext.current
    val plantRepository: PlantRepository = remember { RoomPlantRepository(context) }

    // 1. Estado para guardar a planta encontrada no banco
    var plant by remember { mutableStateOf<Plant?>(null) }

    // 2. Coroutine em segundo plano para buscar a planta sem travar a interface
    LaunchedEffect(speciesId) {
        val plantId = speciesId.toLongOrNull()
        if (plantId != null) {
            plant = plantRepository.getPlantById(plantId)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        plant?.let { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = item.scientificName,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                item.commonName?.let {
                    Text(
                        text = "Nome popular: $it",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                item.family?.let {
                    Text(
                        text = "Família: $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                item.genus?.let {
                    Text(
                        text = "Gênero: $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Text(
                    text = "Precisão da API: ${(item.score * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}