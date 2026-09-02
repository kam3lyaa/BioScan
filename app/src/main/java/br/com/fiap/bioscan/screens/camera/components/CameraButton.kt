package br.com.fiap.bioscan.screens.camera.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun CameraButton(onClick: () -> Unit) {

    Row() {
        Button(
            modifier = Modifier
                .height (40.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(50.dp)
                ),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Abrir câmera",
                style = MaterialTheme.typography.labelMedium)
        }


    }
}


@Composable
fun IdentifyButton(
    carregando: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick ,
        modifier = Modifier
            .height (40.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(50.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = if (carregando) "Identificando..." else "Identificar planta",
            style = MaterialTheme.typography.labelMedium
        )
    }
}


