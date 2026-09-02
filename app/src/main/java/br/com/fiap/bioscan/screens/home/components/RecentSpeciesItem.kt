package br.com.fiap.bioscan.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun RecentSpeciesItem(plant: Plant, navController: NavController, email: String) {


    Card(
        modifier = Modifier
            .height(230.dp)
            .width(150.dp)
            .clickable(
                onClick = {
                    navController.navigate(Destination.DetailsScreen
                        .createRoute(email = email, speciesId = plant.id.toString()))
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme
                .colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = stringResource(R.string.plant_image),
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = plant.commonName ?: "uninformed name",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.outline
            )
                Text(
                    text = plant.scientificName,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.outline
                )}
            //Nome popular




        }
    }





}

@Preview(showBackground = true)
@Composable
private fun RecentSpeciesItemPreview() {
    BioScanTheme {
        //RecentSpeciesItem(plant)
    }
}