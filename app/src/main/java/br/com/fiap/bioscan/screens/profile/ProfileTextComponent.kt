package br.com.fiap.bioscan.screens.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.R
@Composable
fun ProfileTextComponent() {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(R.string.profile),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,


            )

        Text(
            text = stringResource(R.string.user_profile_details),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall

        )
    }


}

@Preview
@Composable
private fun TextComponentPreview() {
    BioScanTheme {
        ProfileTextComponent()
    }

}