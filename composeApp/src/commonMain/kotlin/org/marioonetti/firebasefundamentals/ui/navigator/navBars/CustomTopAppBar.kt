package org.marioonetti.firebasefundamentals.ui.navigator.navBars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.marioonetti.firebasefundamentals.ui.theme.AudioWide
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun CustomTopAppBar(
    title: String,
    onProfileClick: () -> Unit,
    onBackClick: () -> Unit = {},
    showBackArrow: Boolean,
) {
    Box(modifier = Modifier.fillMaxWidth().height(Spacings.p64).background(MyAppColors.TopAppBarColor)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (showBackArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                    contentDescription = null,
                    tint = MyAppColors.TopAppBarTitleColor,
                    modifier = Modifier.clickable {
                        onBackClick()
                    }.fillMaxSize().weight(0.5f)
                )
            } else {
                Spacer(modifier = Modifier.weight(0.5f))
            }
            Text(
                text = title,
                fontFamily = AudioWide(),
                color = MyAppColors.TopAppBarTitleColor,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                tint = MyAppColors.TopAppBarTitleColor,
                modifier = Modifier.clickable {
                    onProfileClick()
                }.fillMaxSize().weight(0.5f)
            )
        }
    }
}