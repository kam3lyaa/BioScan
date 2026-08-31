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
    var resultado by remember { mutableStateOf<String?>(null) }
    var carregando by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { imagem ->
        foto = imagem
        resultado = null
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
                        resultado = identificarPlanta(it)
                        carregando = false
                    }
                }
            ) {
                Text(if (carregando) "Identificando..." else "Identificar planta")
            }
        }

        resultado?.let {
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

private suspend fun identificarPlanta(bitmap: Bitmap): String {
    return try {
        val imagePart = bitmapToMultipart(bitmap)
        val organsBody = "auto".toRequestBody("text/plain".toMediaTypeOrNull())

        val response = RetrofitInstance.api.identifyPlant(
            apiKey = BuildConfig.PLANTNET_API_KEY,
            images = imagePart,
            organs = organsBody
        )

        if (response.isSuccessful) {
            val body = response.body()
            val resultados = body?.results?.take(3)

            if (!resultados.isNullOrEmpty()) {
                resultados.joinToString(separator = "\n\n") { resultado ->
                    val nome = resultado.species.scientificNameWithoutAuthor
                    val genero = resultado.species.genus.scientificNameWithoutAuthor
                    val familia = resultado.species.family.scientificNameWithoutAuthor
                    val confianca = (resultado.score * 100).toInt()

                    "Espécie: $nome\nGênero: $genero\nFamília: $familia\nConfiança: $confianca%"
                }
            } else {
                "Nenhuma planta identificada"
            }
        } else {
            "Erro na API: ${response.code()}"
        }
    } catch (e: Exception) {
        "Erro de conexão: ${e.message}"
    }
}

@Preview
@Composable
private fun CameraScreenPreview() {
    BioScanTheme {
        CameraScreen(navController = rememberNavController())
    }
}