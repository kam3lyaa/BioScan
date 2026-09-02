package br.com.fiap.bioscan.screens.camera

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.BuildConfig
import br.com.fiap.bioscan.api.RetrofitInstance
import br.com.fiap.bioscan.api.toPlant
import br.com.fiap.bioscan.repository.RoomPlantRepository
import br.com.fiap.bioscan.screens.camera.components.CameraButton
import br.com.fiap.bioscan.screens.camera.components.IdentifyButton
import br.com.fiap.bioscan.screens.camera.components.PlantIdentificationResult
import br.com.fiap.bioscan.screens.camera.components.PlantResultCard
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

@Composable
fun CameraScreen(navController: NavHostController) {

    val context = LocalContext.current
    val repository = remember { RoomPlantRepository(context) }

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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        foto?.let { fotoAtual ->
            Image(
                bitmap = fotoAtual.asImageBitmap(),
                contentDescription = "Foto tirada",
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .size(270.dp, 360.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        resultado?.let {
            PlantResultCard(it)
        }

        mensagemErro?.let {
            Text(text = it)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 30.dp)
        ) {
            CameraButton(
                onClick = { cameraLauncher.launch(null) }
            )

            foto?.let { fotoAtual ->
                IdentifyButton(
                    carregando = carregando,
                    onClick = {
                        coroutineScope.launch {
                            carregando = true
                            // Passamos o repositório e o ID do usuário (1L para testes)
                            val resposta = identificarEGravarPlanta(fotoAtual, repository, userId = 1L)
                            resultado = resposta.first
                            mensagemErro = resposta.second
                            carregando = false
                        }
                    }
                )
            }
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

private suspend fun identificarEGravarPlanta(
    bitmap: Bitmap,
    repository: RoomPlantRepository,
    userId: Long
): Pair<PlantIdentificationResult?, String?> {
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

            if (body != null && resultado != null) {

                // 1. Converte a resposta da API para a entidade do banco e salva
                val plantEntity = body.toPlant(userId = userId)
                if (plantEntity != null) {
                    repository.savePlant(plantEntity)
                }

                // 2. Retorna a estrutura para exibição na tela
                val identificado = PlantIdentificationResult(
                    nomePopular = resultado.species.commonNames?.firstOrNull()
                        ?: "sem nome popular",
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