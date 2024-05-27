package br.com.kaiki.devhub.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorScheme = DevHubColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    onBackground = onBackgroundDark,
    background = backgroundDark,
    gradientColors = gradientDarkColors,
    card = cardDark,
    onCard = onCardDark
)

private val LightColorScheme = DevHubColorScheme(
    primary = primaryLight,//OutlinedTextField focused, button
    onPrimary = onPrimaryLight,
    onBackground = onBackgroundLight,
    background = backgroundLight,
    gradientColors = gradientLightColors,
    card = cardLight,
    onCard = onCardLight
)

@Composable
fun DevHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val rippleIndication = rememberRipple()
    
    CompositionLocalProvider (
        LocalDevHubColorScheme provides colorScheme,
        LocalIndication provides rippleIndication,
        content = content
    )

}

object DevHubTheme {
    val colorScheme: DevHubColorScheme
        @Composable get() = LocalDevHubColorScheme.current

}