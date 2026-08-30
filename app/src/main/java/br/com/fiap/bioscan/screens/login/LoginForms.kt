package br.com.fiap.bioscan.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.repository.RoomUserRepository
import br.com.fiap.bioscan.repository.UserRepository
import br.com.fiap.bioscan.repository.UserSharedPreferencesRepository
import br.com.fiap.bioscan.ui.theme.BioScanTheme

@Composable
fun LoginForm(navController: NavHostController) {

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    //variavel de instancia
    val userRepository: UserRepository = RoomUserRepository(LocalContext.current)

    //variavel de estado para a senha
    var showPassword by remember { mutableStateOf(false) }

    //variavel para verificar a autenticação
    var authenticateError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //campo de texto para o email do usuário.
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value= email,
            onValueChange = { emailValue ->
                email = emailValue
            },
            label= {
                Text(
                    text = stringResource(R.string.your_e_mail),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(R.string.email_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        )

        //campo de texto para a senha do usuário.
        OutlinedTextField(
            value= password,
            onValueChange = { passwordValue ->
                password  = passwordValue
            },
            label= {
                Text(
                    text = stringResource(R.string.your_password),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall

                )
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = stringResource(R.string.password_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                Icon(
                    contentDescription = stringResource(R.string.password_icon),
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Default.RemoveRedEye,
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    }
                )
            }

        )

        Spacer(
            modifier =  Modifier.height(16.dp)
        )

        Button(
            onClick = {
                val authenticate = userRepository.login(email, password)
                if(authenticate){
                    navController.navigate(Destination.HomeScreen.createRoute(email))
                }else{
                    authenticateError = true
                }
            },
            modifier =  Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(R.string.login),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium

            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (authenticateError){
            Row(
                modifier = Modifier.fillMaxWidth(),

            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = stringResource(R.string.error),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = stringResource(R.string.authentication_error),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.End
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(R.string.don_t_have_an_accont),
                color = MaterialTheme.colorScheme.onBackground,
                style= MaterialTheme.typography.bodySmall


            )
            TextButton(
                onClick = {
                    navController.navigate(Destination.SignupScreen.route)
                },

                ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginFormPreview() {
    BioScanTheme {
        LoginForm(rememberNavController())
    }

}