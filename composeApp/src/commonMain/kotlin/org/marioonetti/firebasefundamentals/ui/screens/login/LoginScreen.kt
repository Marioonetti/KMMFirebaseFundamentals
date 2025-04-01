package org.marioonetti.firebasefundamentals.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.marioonetti.firebasefundamentals.ui.shared.CustomTextField
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    when (state) {
        is LoginState.Idle -> {
            LoginBodyComposable(
                state = state,
                onEvent = onEvent
            )
        }
    }

}

@Composable
private fun LoginBodyComposable(
    state: LoginState.Idle,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(Spacings.p64))
        CustomTextField(
            value = state.email,
            onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
            hint = "Email",
        )

        CustomTextField(
            value = state.password,
            onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
            hint = "Password"
        )

        Button(
            onClick = { onEvent(LoginEvent.OnLogIn) }
        ) {
            Text("Log in")
        }

        Button(
            onClick = { onEvent(LoginEvent.OnNavigateToRegister) }
        ) {
            Text("Register")
        }
    }
}