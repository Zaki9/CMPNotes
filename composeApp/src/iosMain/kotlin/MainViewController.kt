import androidx.compose.ui.window.ComposeUIViewController
import com.jetbrains.cmp.notes.koin.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }

