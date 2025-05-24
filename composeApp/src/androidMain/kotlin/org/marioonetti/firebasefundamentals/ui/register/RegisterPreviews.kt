package org.marioonetti.firebasefundamentals.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterScreen
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterState


@Preview
@Composable
fun RegisterPreviews() {
    RegisterScreen(
        state = RegisterState.Idle(
            email = "",
            password = "",
            confirmPassword = ""
        ),
        onEvent = {}
    )
}