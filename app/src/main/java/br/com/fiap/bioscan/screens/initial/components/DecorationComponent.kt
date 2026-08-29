package br.com.fiap.bioscan.screens.initial.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun TopStartCard(modifier: Modifier = Modifier) {

    Row(modifier = modifier){
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(width = 160.dp, height = 40.dp),
            shape = RoundedCornerShape(bottomEnd = 90.dp, topEnd = 90.dp)
        ) { }

        Spacer(
            modifier = Modifier.width(40.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp),
            shape = RoundedCornerShape(
                topEnd = 90.dp,
                topStart = 90.dp,
                bottomEnd = 90.dp,
                bottomStart = 90.dp
            )
        ) { }

    }


}


@Composable
fun BottomEndCard(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
    ){
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp),
            shape = RoundedCornerShape(
                topEnd = 90.dp,
                topStart = 90.dp,
                bottomEnd = 90.dp,
                bottomStart = 90.dp
            )
        ) { }

        Spacer(
            modifier = Modifier.width(40.dp)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(width = 160.dp, height = 40.dp),
            shape = RoundedCornerShape(bottomStart = 90.dp, topStart = 90.dp)
        ) { }






    }


}

@Composable
@Preview
private fun topStartPreview() {
    BioScanTheme{
        TopStartCard()
    }
}

@Composable
@Preview
private fun BottomEndPreview() {
    BioScanTheme(

    ){
        BottomEndCard()
    }
}