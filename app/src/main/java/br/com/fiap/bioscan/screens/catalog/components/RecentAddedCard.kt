package br.com.fiap.bioscan.screens.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun RecentAddedCard(plant: Plant) {

    Card(
        modifier = Modifier.padding(8.dp)
            .height(200.dp)
            .width(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.size(24.dp)


                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        modifier = Modifier
                            .background(Color.Transparent)
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = stringResource(R.string.plant_image),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            )

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                text = plant.commonName ?: "Uniformed name",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Bold

            )

                Text(
                    text= plant.scientificName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }


        }


        Spacer(modifier = Modifier.height(4.dp))

    }
}

@Preview(
    showBackground = true
)
@Composable
private fun RecentAddedCardPreview() {
    BioScanTheme() {
        //RecentAddedCard(plant)
    }
}