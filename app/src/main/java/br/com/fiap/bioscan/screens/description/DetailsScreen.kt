package br.com.fiap.bioscan.screens.description

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.repository.PlantRepository
import br.com.fiap.bioscan.repository.RoomPlantRepository
import br.com.fiap.bioscan.screens.home.components.BottomAppBar
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun DetailsScreen(navController: NavController, email: String, speciesId: String) {


    val plantRepository: PlantRepository = RoomPlantRepository(LocalContext.current)

    val plantId = speciesId.toLongOrNull()

    val plant = plantId?.let {
        plantRepository.getPlantById(it)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            bottomBar = { BottomAppBar(navController, email) }
        ){ contentPadding ->
            Box(
                modifier = Modifier.padding(contentPadding)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    if(plant == null){
                        Text(
                            text = "Plant do not found",
                            style = MaterialTheme.typography.titleMedium
                            )

                    }else {
                        Text(
                            text= plant.commonName ?: "Nome não informado",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )
                        Text(
                            text = plant.scientificName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                        Text(
                            text = "Família: ${plant.family ?: "Não informada"}"
                        )

                        Text(
                            text = "Gênero: ${plant.genus ?: "Não informado"}"
                        )

                        Text(
                            text = "Score: ${plant.score}"
                        )
                    }

                }


            }

        }
    }
}

@Preview
@Composable
private fun DetailssnScreennPrev() {
    BioScanTheme() {
        DetailsScreen(rememberNavController(), "","1")
    }

}