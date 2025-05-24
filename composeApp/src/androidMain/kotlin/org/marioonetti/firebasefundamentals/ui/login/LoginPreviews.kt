package org.marioonetti.firebasefundamentals.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginScreen
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginState

@Preview
@Composable
fun LoginBodyPreview() {
    LoginScreen(
        state = LoginState.Idle(
            email = "",
            password = ""
        ),
        onEvent = {}
    )
}