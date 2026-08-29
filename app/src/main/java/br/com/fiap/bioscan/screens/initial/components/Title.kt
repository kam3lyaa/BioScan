package br.com.fiap.bioscan.screens.initial.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun Title(modifier: Modifier = Modifier) {


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ) {
        Text(
            text = "Hello",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Welcome to our app",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall

        )}




}

@Preview(
    showBackground = true
)
@Composable
private fun TitlePrev() {
    BioScanTheme()  {
        Title()
    }
}