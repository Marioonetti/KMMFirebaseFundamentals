package org.marioonetti.firebasefundamentals.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.domain.core.AppError


@Preview(device = "id:pixel_9")
@Composable
fun ErrorPreview() {
    ErrorComposable(AppError.Remote("Error 404")) { }
}