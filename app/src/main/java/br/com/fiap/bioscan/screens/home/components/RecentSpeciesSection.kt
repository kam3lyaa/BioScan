package br.com.fiap.bioscan.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun RecentSpeciesSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Últimos adicionados",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        // por enquanto, fixo com 2 itens de teste
        Row(modifier = Modifier.fillMaxWidth()) {
            RecentSpeciesItem(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(8.dp))

            RecentSpeciesItem(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun RecentSpeciesSectionPreview() {
    BioScanTheme {
        RecentSpeciesSection()
    }
}