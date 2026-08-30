package br.com.fiap.bioscan.screens.singup

import android.graphics.Bitmap
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.R
import br.com.fiap.bioscan.model.User
import br.com.fiap.bioscan.navigation.Destination
import br.com.fiap.bioscan.repository.RoomUserRepository
import br.com.fiap.bioscan.repository.UserRepository
import br.com.fiap.bioscan.repository.UserSharedPreferencesRepository
import br.com.fiap.bioscan.ui.theme.BioScanTheme
import br.com.fiap.bioscan.utils.convertBitmapToByteArray

@Composable
fun SignupForms(navController: NavController, profileImage: Bitmap) {

    //variáveis de estado
    var name by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    //variaveis de estado para verificar se os dados estão corretos
    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    //Variável de estado para caixa de diálogo
    var showDialogSuccess by remember { mutableStateOf(false) }
    var showDialogError by remember { mutableStateOf(false) }

    //Função de validação dos Dados digitados
    fun validate(): Boolean {
        isNameError = name.length < 3
        isEmailError = email.length < 3 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = password.length < 3

        return !isNameError && !isEmailError && !isPasswordError
    }

    //instância da classe SheredPreferencesUserRepository
    val userRepository = RoomUserRepository(LocalContext.current)


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        //campo de texto para o nome do usuário.
        OutlinedTextField(
            value= name,
            onValueChange = {nameValue ->
                name = nameValue
            },
            modifier = Modifier.fillMaxWidth(),
            label= {
                Text(
                    text = stringResource(R.string.your_name),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.user_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            isError = isNameError,
            trailingIcon = {
                if(isNameError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = stringResource(R.string.error_icon)
                    )
                }
            },
            supportingText = {
                if(isNameError){
                    Text(
                        text = stringResource(R.string.user_name_is_required),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            )


        //campo de texto para o email do usuário.
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value= email,
            onValueChange = {emailValue ->
                email = emailValue
            },
            label= {
                Text(
                    text = stringResource(R.string.your_e_mail),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(R.string.email_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = isEmailError,
            trailingIcon = {
                if(isEmailError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = stringResource(R.string.error_icon)
                    )
                }
            },
            supportingText = {
                if(isEmailError){
                    Text(
                        text = stringResource(R.string.email_is_required),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

        )

        //campo de texto para a senha do usuário.
        OutlinedTextField(
            value= password,
            onValueChange = {passwordValue ->
                password = passwordValue
            },
            label= {
                Text(
                    text = stringResource(R.string.your_password),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = stringResource(R.string.password_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = isPasswordError,
            trailingIcon = {
                if(isPasswordError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        tint = MaterialTheme.colorScheme.error,
                        contentDescription = stringResource(R.string.error_icon)
                    )
                }
            },
            supportingText = {

                if(isPasswordError){
                    Text(
                        text = stringResource(R.string.password_is_required),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }


        )

        Spacer(
            modifier =  Modifier.height(16.dp)
        )

        Button(
            onClick = {
                if(validate()){
                    userRepository.saveUser(
                        User(
                            name = name,
                            password = password,
                            email = email,
                            userImage = convertBitmapToByteArray(profileImage)
                        )
                    )
                    showDialogSuccess = true
                }else {
                    showDialogError = true
                }

            },
            modifier =  Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium

            )
        }
        Spacer(modifier =  Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton (

                onClick = {
                    navController.navigate(Destination.InitialScreen.route)
                }
            ){
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        //Caixa de diálogo de sucesso
        if(showDialogSuccess){
            AlertDialog(
                onDismissRequest = { showDialogError = false },
                title = {
                    Text(
                        text = stringResource(R.string.success)
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.account_created_successfully)
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            navController.navigate(Destination.LoginScreen.route)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.ok)
                        )
                    }
                }
            )

        }
    }
        //Caixa de dialogo de erro
    if(showDialogError){
        AlertDialog(
            onDismissRequest = { showDialogError = false },
            title = {
                Text(
                text = stringResource(R.string.error)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.please_fill_in_all_fields_correctly)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogError = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.ok)
                    )
                }
            }
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
private fun SignupFormPreview() {
    BioScanTheme() {
        //SignupForms(rememberNavController(), profileImage)
    }

}