package br.com.fiap.bioscan.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.screens.home.components.BottomAppBar
import br.com.fiap.bioscan.screens.home.components.CatalogedSpeciesCard
import br.com.fiap.bioscan.screens.home.components.RecentSpeciesSection
import br.com.fiap.bioscan.screens.home.components.TopAppBar
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun HomeScreen(navController: NavController, email: String) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
                topBar = { TopAppBar(email, navController) },
                bottomBar = { BottomAppBar(navController, email) }

        ) {paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ){
                CatalogedSpeciesCard()
                RecentSpeciesSection(navController, email)

            }
        }
    }
}

@Preview
@Composable
private fun HomeeScreenPrev() {
    BioScanTheme() {
        HomeScreen( rememberNavController(), "")
    }
}