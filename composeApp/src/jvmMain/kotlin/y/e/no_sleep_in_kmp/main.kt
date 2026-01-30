package y.e.no_sleep_in_kmp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "不困模拟器",
        state = rememberWindowState(width = 450.dp, height = 450.dp),
        resizable = false
    ) {
        App()
    }
}