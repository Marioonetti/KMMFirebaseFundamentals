package org.marioonetti.firebasefundamentals.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.error_screen_button
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun ErrorComposable(
    error: AppError,
    onClick: () -> Unit = {},
) {
    val errorMessage = when (error) {
        is AppError.Local -> error.message
        is AppError.Remote -> error.message
        AppError.Unknown -> "Unknown error occurred"
    }

    Column(
        modifier =
            Modifier
                .background(MyAppColors.LightBlue)
                .fillMaxSize()
                .padding(Spacings.p32),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(Spacings.p16))
                .background(MyAppColors.BackgroundBlue)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Spacings.p16))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            OnboardingButton(
                content = stringResource(Res.string.error_screen_button) ,
                onClick = onClick,
                modifier = Modifier.padding(Spacings.p32)
            )
        }
    }
}