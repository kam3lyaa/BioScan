package br.com.fiap.bioscan.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.screens.initial.components.LogoImage
import br.com.fiap.bioscan.ui.theme.BioScanTheme


@Composable
fun TextComponent() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text= stringResource(R.string.user_login),
            color = MaterialTheme.colorScheme.onBackground,
            style= MaterialTheme.typography.titleLarge

        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Text(
            text= stringResource(R.string.please_sign_in_to_continue),
            color = MaterialTheme.colorScheme.onBackground,
            style= MaterialTheme.typography.titleSmall
        )


    }


}

@Preview(
    showBackground = true,

    )
@Composable
private fun TextComponentPreview() {
    BioScanTheme { }
    TextComponent()
}