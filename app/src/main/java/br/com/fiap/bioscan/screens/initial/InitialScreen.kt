package br.com.fiap.bioscan.screens.initial

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.screens.initial.components.BottomEndCard
import br.com.fiap.bioscan.screens.initial.components.LogoImage
import br.com.fiap.bioscan.screens.initial.components.Title
import br.com.fiap.bioscan.screens.initial.components.TopStartCard
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.R


@Composable
fun InitialScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        TopStartCard(Modifier.align(Alignment.TopStart))
        BottomEndCard(Modifier.align(Alignment.BottomEnd))
        Column(
            modifier = Modifier
                .fillMaxWidth()

                .align(Alignment.Center),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            LogoImage()
            Text(
                text = stringResource(R.string.name),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                Modifier.height(40.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title()
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(Destination.SignupScreen.route)
                    },
                    Modifier.height(40.dp)
                        .width(100.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(

                        text = "Sign up",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium

                    )




                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )

                Button(
                    onClick = {
                        navController.navigate(Destination.LoginScreen.route)

                    },
                    Modifier.height(40.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.labelMedium
                    )




                }


            }
        }






    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun InitialScreenPreview() {
    BioScanTheme() {
        InitialScreen(rememberNavController())
    }
}