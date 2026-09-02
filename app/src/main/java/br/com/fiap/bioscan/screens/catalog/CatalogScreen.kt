package br.com.fiap.bioscan.screens.catalog

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.repository.RoomPlantRepository
import br.com.fiap.bioscan.repository.RoomUserRepository
import br.com.fiap.bioscan.repository.UserRepository
import br.com.fiap.bioscan.screens.catalog.components.RecentAddedSection
import br.com.fiap.bioscan.screens.home.components.BottomAppBar
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.utils.convertByteArrayToBitmap

@Composable
fun CatalogScreen(navController: NavController, email: String) {

    val isPreview = LocalInspectionMode.current
    val context = LocalContext.current

    var profileBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var plantsList by remember { mutableStateOf<List<Plant>>(emptyList()) }

    if (!isPreview) {
        val userRepository: UserRepository = remember { RoomUserRepository(context) }
        val plantRepository = remember { RoomPlantRepository(context) }

        LaunchedEffect(email) {
            val user = userRepository.getUserByEmail(email)
            user?.userImage?.let { byteArray ->
                profileBitmap = convertByteArrayToBitmap(byteArray)
            }

            // Busca as plantas salvas no banco de dados para o catálogo
            val userId = user?.id?.toLong() ?: 1L
            plantsList = plantRepository.getPlantsByUser(userId)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = { BottomAppBar(navController, email) }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp, top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.my_catalog),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Card(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(48.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(
                                        Destination.UpdateScreen.createRoute(email)
                                    )
                                }
                            ),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        profileBitmap?.let { bitmap ->
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Profile image",
                                modifier = Modifier.size(70.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Passa a lista de plantas recuperada do banco para a seção do catálogo
                    RecentAddedSection(
                        navController = navController,
                        email = email,
                        plants = plantsList
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CatalogScreenPreview() {
    BioScanTheme {
        CatalogScreen(rememberNavController(), "")
    }
}