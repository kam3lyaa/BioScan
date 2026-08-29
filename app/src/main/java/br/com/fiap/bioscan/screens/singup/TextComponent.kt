package br.com.fiap.bioscan.screens.singup


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.R
@Composable
fun TextComponent() {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(R.string.sign_up),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,


            )

        Text(
            text = stringResource(R.string.first_let_s_create_a_new_account),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall

        )
    }


}

@Preview
@Composable
private fun TextComponentPreview() {
    BioScanTheme {
        TextComponent()
    }

}