package br.com.fiap.bioscan.screens.home.components

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.repository.RoomUserRepository
import br.com.fiap.bioscan.repository.UserRepository
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.utils.convertByteArrayToBitmap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(email: String? = "", navController: NavController) {

    //instancia do repositório
    val userRepository: UserRepository = RoomUserRepository(LocalContext.current)

    //busca do user pelo email
    val user = userRepository.getUserByEmail(email!!)

    //var de estado da imagem

    var profileBitmap by remember {
        mutableStateOf<Bitmap>(convertByteArrayToBitmap(user!!.userImage)) }



    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(){
                    Text(
                        text = "Hello ${user?.name} ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold

                    )

                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodySmall,

                        )
                }
                Card(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(48.dp)
                        .clickable(
                            onClick = {
                                navController.navigate(Destination.UpdateScreen.createRoute(email))
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
                ){
                    Image(
                        bitmap = profileBitmap.asImageBitmap(),
                        contentDescription = "Profile image",
                        modifier = Modifier.size(70.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    )


}

@Preview
@Composable
private fun TopAppBarPreview() {
    BioScanTheme{
        TopAppBar("", rememberNavController())
    }
}