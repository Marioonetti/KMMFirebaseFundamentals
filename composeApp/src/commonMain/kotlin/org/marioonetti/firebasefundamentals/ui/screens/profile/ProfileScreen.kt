package org.marioonetti.firebasefundamentals.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginEvent
import org.marioonetti.firebasefundamentals.ui.shared.LoadingComposable
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun ProfileScreen(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
) {
    when (state) {
        is ProfileState.Loading -> {
            LoadingComposable()
        }
        is ProfileState.Error -> {
            // Handle error state, e.g., show a snackbar or dialog
        }
        is ProfileState.Idle -> {
            ProfileBody(state.userName, state.email, onLogout = { onEvent(ProfileEvent.OnLogout) })
        }
    }
}


@Composable
fun ProfileBody(
    userName: String,
    email: String,
    onLogout: () -> Unit
) {
    val initials = userName
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.first().uppercaseChar() }
        .take(2)
        .joinToString("")

    val avatarBackground = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacings.p16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(Spacings.p32))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(72.dp)
                    .background(color = avatarBackground, shape = CircleShape)
            ) {
                Text(
                    text = initials,
                    style = MaterialTheme.typography.headlineLarge.copy(color = Color.White)
                )
            }

            Spacer(modifier = Modifier.width(Spacings.p16))

            Column {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(Spacings.p4))
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacings.p64))

        Button(
            onClick = onLogout,
            modifier = Modifier.padding()
        ) {
            Text(text = "Log out")
        }
    }
}
