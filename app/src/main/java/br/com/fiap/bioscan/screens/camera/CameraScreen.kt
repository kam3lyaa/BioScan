package br.com.fiap.bioscan.screens.camera

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.BuildConfig
import br.com.fiap.bioscan.api.RetrofitInstance
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

@Composable
fun CameraScreen(navController: NavHostController) {

    var foto by remember { mutableStateOf<Bitmap?>(null) }
    var resultado by remember { mutableStateOf<PlantIdentificationResult?>(null) }
    var mensagemErro by remember { mutableStateOf<String?>(null) }
    var carregando by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { imagem ->
        foto = imagem
        resultado = null
        mensagemErro = null
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                cameraLauncher.launch(null)
            }
        ) {
            Text("Abrir câmera")
        }

        foto?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto tirada"
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        carregando = true
                        val resposta = identificarPlanta(it)
                        resultado = resposta.first
                        mensagemErro = resposta.second
                        carregando = false
                    }
                }
            ) {
                Text(if (carregando) "Identificando..." else "Identificar planta")
            }
        }

        resultado?.let {
            PlantResultCard(it)
        }

        mensagemErro?.let {
            Text(text = it)
        }
    }
}

private fun bitmapToMultipart(bitmap: Bitmap): MultipartBody.Part {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
    val byteArray = outputStream.toByteArray()

    val requestBody = byteArray.toRequestBody(
        "image/jpeg".toMediaTypeOrNull(),
        0,
        byteArray.size
    )

    return MultipartBody.Part.createFormData(
        "images",
        "foto_planta.jpg",
        requestBody
    )
}

private suspend fun identificarPlanta(bitmap: Bitmap): Pair<PlantIdentificationResult?, String?> {
    return try {
        val imagePart = bitmapToMultipart(bitmap)
        val organsBody = "auto".toRequestBody("text/plain".toMediaTypeOrNull())

        val response = RetrofitInstance.api.identifyPlant(
            apiKey = BuildConfig.PLANTNET_API_KEY,
            lang = "pt",
            images = imagePart,
            organs = organsBody
        )

        if (response.isSuccessful) {
            val body = response.body()
            val resultado = body?.results?.firstOrNull()

            if (resultado != null) {
                val identificado = PlantIdentificationResult(
                    nomePopular = resultado.species.commonNames?.firstOrNull() ?: "sem nome popular",
                    nomeCientifico = resultado.species.scientificNameWithoutAuthor,
                    genero = resultado.species.genus.scientificNameWithoutAuthor,
                    familia = resultado.species.family.scientificNameWithoutAuthor,
                    confianca = (resultado.score * 100).toInt()
                )
                Pair(identificado, null)
            } else {
                Pair(null, "Nenhuma planta identificada")
            }
        } else {
            Pair(null, "Erro na API: ${response.code()}")
        }
    } catch (e: Exception) {
        Pair(null, "Erro de conexão: ${e.message}")
    }
}

@Preview
@Composable
private fun CameraScreenPreview() {
    BioScanTheme {
        CameraScreen(navController = rememberNavController())
    }
}