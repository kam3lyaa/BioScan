package br.com.fiap.bioscan.screens.catalog.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.screens.home.components.RecentSpeciesSection
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun RecentAddedSection(
    navController: NavController,
    email: String,
    plants: List<Plant> = emptyList()
) {
    RecentSpeciesSection(
        plants = plants,
        navController = navController,
        email = email
    )
}

@Preview(showBackground = true)
@Composable
private fun RecentSpeciesSectionPreview() {
    BioScanTheme {
        RecentAddedSection(
            navController = rememberNavController(),
            email = ""
        )
    }
}