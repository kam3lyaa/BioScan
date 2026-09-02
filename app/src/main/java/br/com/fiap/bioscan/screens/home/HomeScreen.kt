package br.com.fiap.bioscan.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.mock.mockPlants
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.repository.RoomPlantRepository
import br.com.fiap.bioscan.repository.RoomUserRepository
import br.com.fiap.bioscan.screens.home.components.BottomAppBar
import br.com.fiap.bioscan.screens.home.components.CatalogedSpeciesCard
import br.com.fiap.bioscan.screens.home.components.RecentSpeciesSection
import br.com.fiap.bioscan.screens.home.components.TopAppBar
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun HomeScreen(navController: NavController, email: String) {

    val isPreview = LocalInspectionMode.current
    val context = LocalContext.current

    var plantsList by remember { mutableStateOf<List<Plant>>(emptyList()) }

    if (!isPreview) {
        val userRepository = remember { RoomUserRepository(context) }
        val plantRepository = remember { RoomPlantRepository(context) }

        LaunchedEffect(email) {
            val user = userRepository.getUserByEmail(email)
            val userId = user?.id?.toLong() ?: 1L
            plantsList = plantRepository.getPlantsByUser(userId)
        }
    } else {
        plantsList = mockPlants
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            topBar = { TopAppBar(email, navController) },
            bottomBar = { BottomAppBar(navController, email) }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                CatalogedSpeciesCard(count = plantsList.size)
                RecentSpeciesSection(
                    plants = plantsList,
                    navController = navController,
                    email = email
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeeScreenPrev() {
    BioScanTheme {
        HomeScreen(rememberNavController(), "teste@fiap.com.br")
    }
}