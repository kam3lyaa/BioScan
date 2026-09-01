package br.com.fiap.bioscan.screens.camera

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class PlantIdentificationResult(
    val nomePopular: String,
    val nomeCientifico: String,
    val genero: String,
    val familia: String,
    val confianca: Int
)

@Composable
fun PlantResultCard(resultado: PlantIdentificationResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = resultado.nomePopular,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = resultado.nomeCientifico,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Gênero: ${resultado.genero}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )

            Text(
                text = "Família: ${resultado.familia}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )

            Text(
                text = "Confiança: ${resultado.confianca}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}