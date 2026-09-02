package br.com.fiap.bioscan.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.mock.mockPlants
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RecentSpeciesSection(navController: NavController, email: String) {
    val plants = mockPlants


    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
        ) {
        Text(
            text = "Recently identified species:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(plants)  {plant ->
                RecentSpeciesItem(plant, navController,email)

            }
        }


//        // por enquanto, fixo com 2 itens de teste
//        Row(modifier = Modifier
//            .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            RecentSpeciesItem()
//            RecentSpeciesItem()
//
//
//        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun RecentSpeciesSectionPreview() {
    BioScanTheme {
        RecentSpeciesSection(rememberNavController(), "",)
    }
}