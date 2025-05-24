package org.marioonetti.firebasefundamentals.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.login_email_hint
import firebasefundamentals.composeapp.generated.resources.login_password_hint
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginEvent
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun NormalTextFieldComposable(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text( hint ) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacings.p8),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.White,
            cursorColor = Color.Black
        )
    )
}

@Composable
fun PasswordTextFieldComposable(
    value: String,
    hint: String,
    passwordVisible: Boolean,
    onValueChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(hint) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(
                onClick = { onPasswordVisibilityChange() },
                content = {
                    Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacings.p8),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.White,
            cursorColor = Color.Black
        )
    )
}