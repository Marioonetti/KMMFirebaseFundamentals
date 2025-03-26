package org.marioonetti.firebasefundamentals.ui.shared

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun CustomTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(hint) }
    )
}