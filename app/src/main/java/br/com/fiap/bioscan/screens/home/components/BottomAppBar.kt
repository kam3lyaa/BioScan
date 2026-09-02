package br.com.fiap.bioscan.screens.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.ui.theme.BioScanTheme


data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
@Composable
fun BottomAppBar(navController: NavController, email: String? = "",) {
    val items = listOf(
        BottomNavigationItem(stringResource(R.string.home), icon = Icons.Default.Home, route = Destination.HomeScreen.createRoute(email ?: "")),
        BottomNavigationItem(stringResource(R.string.camera), icon = Icons.Default.Camera,
            route = Destination.CameraScreen.route),
        BottomNavigationItem(stringResource(R.string.catalog), icon = Icons.Default.Collections, route= Destination.CatalogScreen.createRoute(email ?: "")),
    )

    val borderColor = MaterialTheme.colorScheme.onPrimaryContainer

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier
            .drawBehind{
                drawLine(
                    color = borderColor,
                    start = Offset(0f,0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 0.25.dp.toPx()
                )
            }
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(item.route)

                },
                icon ={
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            )
        }

    }
}


@Preview
@Composable
private fun BottomAppBarPreview() {
    BioScanTheme() {
        BottomAppBar(rememberNavController(), "")
    }
}