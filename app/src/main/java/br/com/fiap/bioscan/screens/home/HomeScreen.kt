package br.com.fiap.bioscan.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.screens.home.components.CatalogedSpeciesCard
import br.com.fiap.bioscan.screens.home.components.HomeBottomBar
import br.com.fiap.bioscan.screens.home.components.HomeTopBar
import br.com.fiap.bioscan.screens.home.components.RecentSpeciesSection
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            HomeTopBar(userName = "Olá, Mike", navController = navController)
        },
        bottomBar = {
            HomeBottomBar()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CatalogedSpeciesCard()
            RecentSpeciesSection()
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun HomeScreenPreview() {
    BioScanTheme {
        HomeScreen(navController = rememberNavController())
    }
}