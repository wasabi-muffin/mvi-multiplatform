import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.gmvalentino.StoreProvider
import com.gmvalentino.main.MainIntent
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexFlow
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Ul

@Composable
fun TaskScreen() {

    val store = StoreProvider.main()
    val state = store.state.collectAsState()
    val event = store.events.collectAsState(null)

    Div(
        attrs = {
            style {
                width(100.percent)
                height(100.percent)
                display(DisplayStyle.Flex)
                flexFlow(FlexDirection.Column, FlexWrap.Nowrap)
            }
        }
    ) {

        Ul(
            attrs = {
                style {
                    width(80.percent)
                    margin(0.px)
                    property("flex", "1 1 auto")
                    property("overflow-y", "scroll")
                }
            }
        ) {
            state.value.tasks.forEach { task ->
                TaskItemView(task) {
                    store.dispatch(MainIntent.Toggle(task.id))
                }
            }
        }
    }
}