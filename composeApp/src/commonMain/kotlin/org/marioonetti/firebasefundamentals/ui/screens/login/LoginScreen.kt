package org.marioonetti.firebasefundamentals.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.digimon_login
import firebasefundamentals.composeapp.generated.resources.login_button_text
import firebasefundamentals.composeapp.generated.resources.login_email_hint
import firebasefundamentals.composeapp.generated.resources.login_password_hint
import firebasefundamentals.composeapp.generated.resources.login_register_button_text
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.ui.shared.NormalTextFieldComposable
import org.marioonetti.firebasefundamentals.ui.shared.OnboardingButton
import org.marioonetti.firebasefundamentals.ui.shared.PasswordTextFieldComposable
import org.marioonetti.firebasefundamentals.utils.MyAppColors
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
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(MyAppColors.DarkBlueBase, MyAppColors.LightBlue)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacings.p32),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(resource = Res.drawable.digimon_login),
                contentDescription = "Digimon Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )

            NormalTextFieldComposable(
                value = state.email,
                hint = stringResource(Res.string.login_email_hint),
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) }
            )

            PasswordTextFieldComposable(
                value = state.password,
                hint = stringResource(Res.string.login_password_hint),
                passwordVisible = passwordVisible,
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(Spacings.p16))

            OnboardingButton(
                content = stringResource(Res.string.login_button_text),
                onClick = { onEvent(LoginEvent.OnLogIn) },
            )

            TextButton(
                onClick = { onEvent(LoginEvent.OnNavigateToRegister) },
                modifier = Modifier.padding(top = Spacings.p8)
            ) {
                Text(
                    text = stringResource(Res.string.login_register_button_text),
                    fontFamily = FontFamily.Monospace,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MyAppColors.YellowButton
                )
            }
        }
    }
}