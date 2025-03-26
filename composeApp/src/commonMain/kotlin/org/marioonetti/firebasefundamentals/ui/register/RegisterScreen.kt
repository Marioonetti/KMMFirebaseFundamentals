package org.marioonetti.firebasefundamentals.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.marioonetti.firebasefundamentals.ui.shared.CustomTextField
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
) {

    when (state) {
        is RegisterState.Idle -> {
            RegisterContent(state, onEvent)
        }
    }

}

@Composable
fun RegisterContent(
    state: RegisterState.Idle,
    onEvent: (RegisterEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(Spacings.p64))
        CustomTextField(value = state.email,
            hint = "Email",
            onValueChange = { onEvent(RegisterEvent.OnEmailChanged(it)) })

        Spacer(modifier = Modifier.height(Spacings.p32))
        CustomTextField(value = state.userName,
            hint = "Username",
            onValueChange = { onEvent(RegisterEvent.OnUsernameChanged(it)) })
        Spacer(modifier = Modifier.height(Spacings.p32))
        CustomTextField(value = state.password,
            hint = "Password",
            onValueChange = { onEvent(RegisterEvent.OnPasswordChanged(it)) })
        Spacer(modifier = Modifier.height(Spacings.p32))
        Button(
            onClick = { onEvent(RegisterEvent.OnRegister) }
        ) {
            Text("Register")
        }
    }
}