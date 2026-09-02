package br.com.fiap.bioscan.screens.singup

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.screens.initial.components.BottomEndCard
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun SignupScreen(navController: NavController) {
    val context = LocalContext.current

    val placeholderImage = BitmapFactory.decodeResource(
        Resources.getSystem(),
        android.R.drawable.ic_menu_gallery
    )

    var profileImage by remember { mutableStateOf<Bitmap>(placeholderImage) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (Build.VERSION.SDK_INT < 28) {
            profileImage = MediaStore
                .Images
                .Media
                .getBitmap(context.contentResolver, uri)
        } else {
            if (uri != null) {
                val source = ImageDecoder
                    .createSource(context.contentResolver, uri)
                profileImage = ImageDecoder.decodeBitmap(source)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        BottomEndCard(modifier = Modifier.align(alignment = Alignment.BottomEnd))

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent()
            Spacer(modifier = Modifier.height(30.dp))
            UserImage(profileImage, launcher)
            Spacer(modifier = Modifier.height(10.dp))

            SingUpForms(navController, profileImage)
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SignupScreenPreview() {
    BioScanTheme {
        SignupScreen(rememberNavController())
    }
}