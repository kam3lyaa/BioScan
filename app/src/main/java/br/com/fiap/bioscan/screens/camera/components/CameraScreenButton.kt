package br.com.fiap.bioscan.screens.camera.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun CameraButton(onClick: () -> Unit)
{
    Button(
        modifier = Modifier
            .height(35.dp)
            .border(
                width = 0.7.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(50.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),

        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)

        ){
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = "Câmera icon",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .height(40.dp)
                )

            Text(
                text = "Abrir câmera",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )


        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CameraButtonPreview() {
    BioScanTheme() {
        CameraButton() {  }
    }
}


@Composable
fun IdentifyButton(
    carregando: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick ,
        modifier = Modifier.height(45.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            if(carregando){
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

            } else{
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Câmera icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .height(40.dp)
                )
            }

            Text(
                text = if (carregando) "Identificando..." else "Identificar",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
                )
        }
    }
}

@Preview
@Composable
private fun IdentifyButtonPreview() {
    BioScanTheme() {
        IdentifyButton(false, onClick = {})
    }
}


