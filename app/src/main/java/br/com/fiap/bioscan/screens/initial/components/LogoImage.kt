package br.com.fiap.bioscan.screens.initial.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.ui.theme.BioScanTheme


@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(250.dp)
    )
    {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo da marca",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.Center)

        )
    }
}

@Preview
@Composable
private fun LogoImagePreview() {
    BioScanTheme {
        LogoImage()
    }
}