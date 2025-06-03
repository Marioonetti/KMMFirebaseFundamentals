package org.marioonetti.firebasefundamentals.ui.profile

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.ui.screens.profile.ProfileScreen
import org.marioonetti.firebasefundamentals.ui.screens.profile.ProfileState

@Preview(device = "id:pixel_9",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ProfilePreview() {
    ProfileScreen(
        state = ProfileState.Idle("Fermin Trujillo", "fermin@gmail.com"),
        onEvent = {}
    )
}