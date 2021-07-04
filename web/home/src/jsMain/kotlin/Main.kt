import com.gmvalentino.MultiplatformApplication
import org.jetbrains.compose.web.renderComposable

fun main() {

    MultiplatformApplication.initialize()

    renderComposable(rootElementId = "root") {
        TaskScreen()
    }
}