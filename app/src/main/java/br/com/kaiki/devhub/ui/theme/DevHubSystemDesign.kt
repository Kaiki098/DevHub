package br.com.kaiki.devhub.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

//colors
data class DevHubColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val gradientColors: List<Color>,
    val card: Color,
    val onCard: Color
)

//typography

//shape

//size

val LocalDevHubColorScheme = staticCompositionLocalOf {
    DevHubColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        gradientColors = listOf(Color.Unspecified),
        card = Color.Unspecified,
        onCard = Color.Unspecified
    )
}