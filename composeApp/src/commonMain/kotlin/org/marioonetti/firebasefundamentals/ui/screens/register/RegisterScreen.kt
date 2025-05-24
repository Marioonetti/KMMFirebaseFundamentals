package org.marioonetti.firebasefundamentals.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.register_header
import org.jetbrains.compose.resources.painterResource
import org.marioonetti.firebasefundamentals.ui.shared.NormalTextFieldComposable
import org.marioonetti.firebasefundamentals.ui.shared.OnboardingButton
import org.marioonetti.firebasefundamentals.ui.shared.PasswordTextFieldComposable
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
) {
    when (state) {
        is RegisterState.Idle -> {
            RegisterBodyComposable(state, onEvent)
        }
    }

}

@Composable
fun RegisterBodyComposable(
    state: RegisterState.Idle,
    onEvent: (RegisterEvent) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

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
                painter = painterResource(resource = Res.drawable.register_header),
                contentDescription = "Digimon Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(250.dp)
            )

            NormalTextFieldComposable(
                value = state.email,
                hint = "Correo electrónico",
                onValueChange = { onEvent(RegisterEvent.OnEmailChanged(it)) }
            )

            NormalTextFieldComposable(
                value = state.userName,
                hint = "Nombre de usuario",
                onValueChange = { onEvent(RegisterEvent.OnUsernameChanged(it)) }
            )

            PasswordTextFieldComposable(
                value = state.password,
                hint = "Contraseña",
                passwordVisible = passwordVisible,
                onValueChange = { onEvent(RegisterEvent.OnPasswordChanged(it)) },
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
            )

            PasswordTextFieldComposable(
                value = state.confirmPassword,
                hint = "Confirmar contraseña",
                passwordVisible = confirmVisible,
                onValueChange = { onEvent(RegisterEvent.OnConfirmPasswordChanged(it)) },
                onPasswordVisibilityChange = { confirmVisible = !confirmVisible }
            )

            Spacer(modifier = Modifier.height(Spacings.p16))

            OnboardingButton(
                content = "Registrarse",
                onClick = { onEvent(RegisterEvent.OnRegister) }
            )

            TextButton(
                onClick = { onEvent(RegisterEvent.OnNavigateToLogin) },
                modifier = Modifier.padding(top = Spacings.p8)
            ) {
                Text(
                    text = "Volver al login",
                    fontFamily = FontFamily.Monospace,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MyAppColors.YellowButton
                )
            }
        }
    }
}