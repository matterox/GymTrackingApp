package com.mxtgames.gymtrack.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mxtgames.gymtrack.R
import com.mxtgames.gymtrack.ui.theme.GymTrackTheme

@Composable
fun AuthRoute(
    modifier: Modifier = Modifier,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val authState = authenticationViewModel.authUiState.collectAsStateWithLifecycle()
    AuthScreen(
        modifier = modifier,
        state = authState.value,
        onLoginClicked = { email, password ->
            authenticationViewModel.onAction(
                AuthScreenActions.OnLoginPressed(
                    email = email,
                    password = password
                )
            )
        }
    )
}

@Composable
internal fun AuthScreen(
    modifier: Modifier = Modifier,
    state: AuthScreenState,
    onLoginClicked: (String, String) -> Unit,
) {
    // A surface container using the 'background' color from the theme
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            Text(text = stringResource(id = R.string.app_name), style = LocalTextStyle.current.copy(
                fontSize = 32.sp // TODO: Move to GymTrackTheme
            ))
            OutlinedTextField(
                value = email,
                label = {
                    Text(stringResource(id = R.string.auth_email_label))
                },
                onValueChange = {
                    email = it
                },
                enabled = state != AuthScreenState.Loading,
                isError = state is AuthScreenState.Error
            )
            OutlinedTextField(
                value = password,
                label = {
                    Text(stringResource(id = R.string.auth_password_label))
                },
                onValueChange = {
                    password = it
                },
                enabled = state != AuthScreenState.Loading,
                isError = state is AuthScreenState.Error,
                visualTransformation = PasswordVisualTransformation()
            )
            if (state is AuthScreenState.Error) {
                Text(text = state.message)
            }
            Button(
                onClick = {
                    onLoginClicked.invoke(email, password)
                },
                enabled = state != AuthScreenState.Loading
            ) {
                Text(
                    modifier = Modifier
                        .padding(GymTrackTheme.spaces.XS),
                    text = stringResource(id = R.string.auth_login_button)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    GymTrackTheme {
        AuthScreen(
            state = AuthScreenState.Error("Wow, such an error!"),
            onLoginClicked = {_, _ -> }
        )
    }
}