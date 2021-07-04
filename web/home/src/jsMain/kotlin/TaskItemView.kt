
import androidx.compose.runtime.Composable
import com.gmvalentino.entities.Task
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexFlow
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLUListElement

@Composable
fun DOMScope<HTMLUListElement>.TaskItemView(
    task: Task,
    onClick: () -> Unit,
) {
    Li(
        attrs = {
            style {
                width(100.percent)
                display(DisplayStyle.Flex)
                flexFlow(FlexDirection.Row, FlexWrap.Nowrap)
                alignItems(AlignItems.Center)
                property("padding", "0px 0px 0px 16px")
            }
            onClick { onClick() }
        }
    ) {
        Checkbox(
            isChecked = task.isComplete,
            attrs = {
                style {
                    property("flex", "0 1 auto")
                }
            }
        )

        Div(
            attrs = {
                style {
                    height(48.px)
                    property("flex", "1 1 auto")
                    property("white-space", "nowrap")
                    property("text-overflow", "ellipsis")
                    property("overflow", "hidden")
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            Text(value = task.title)
        }
    }
}

@Composable
fun Checkbox(
    isChecked: Boolean,
    attrs: AttrBuilderContext<*> = {}
) {
    Div(attrs = attrs) {
        if (isChecked) {
            Img("checkbox_on.svg")
        } else {
            Img("checkbox_off.svg")
        }
    }
}