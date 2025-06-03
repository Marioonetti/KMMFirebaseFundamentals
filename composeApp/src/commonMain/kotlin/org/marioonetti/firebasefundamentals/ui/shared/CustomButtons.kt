package org.marioonetti.firebasefundamentals.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    content: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(Spacings.p16),
        colors = ButtonDefaults.buttonColors(
            containerColor = MyAppColors.YellowButton,
            contentColor = MyAppColors.PurpleButtonText
        )
    ) {
        Text(
            text = content,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}