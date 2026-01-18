package com.typerx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TyperXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeType: ThemeType = ThemeType.DYNAMIC,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        ThemeType.LIGHT -> LightThemeColors
        ThemeType.DARK -> DarkThemeColors
        ThemeType.CATPPUCCIN_LATTE -> CatppuccinLatteColors
        ThemeType.CATPPUCCIN_FRAPPE -> CatppuccinFrappeColors
        ThemeType.CATPPUCCIN_MACCHIATO -> CatppuccinMacchiatoColors
        ThemeType.CATPPUCCIN_MOCHA -> CatppuccinMochaColors
        ThemeType.DRACULA -> DraculaColors
        ThemeType.DYNAMIC -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                if (darkTheme) {
                    androidx.compose.material3.dynamicDarkColorScheme(androidx.compose.ui.platform.LocalContext.current)
                } else {
                    androidx.compose.material3.dynamicLightColorScheme(androidx.compose.ui.platform.LocalContext.current)
                }
            } else {
                if (darkTheme) DarkThemeColors else LightThemeColors
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

enum class ThemeType {
    LIGHT,
    DARK,
    CATPPUCCIN_LATTE,
    CATPPUCCIN_FRAPPE,
    CATPPUCCIN_MACCHIATO,
    CATPPUCCIN_MOCHA,
    DRACULA,
    DYNAMIC
}