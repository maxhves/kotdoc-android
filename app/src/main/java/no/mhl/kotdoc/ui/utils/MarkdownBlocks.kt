package no.mhl.kotdoc.ui.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.data.local.*
import no.mhl.kotdoc.ui.theme.*

@Composable
fun MarkdownBlocks(blocks: List<Block>) {
    for (i in blocks.indices) {
        when (val block = blocks[i]) {
            is Heading -> MarkdownHeading(block)
            is FencedCode -> MarkdownCode(block.content)
            is Paragraph -> MarkdownParagraph(block)
            is Alert -> MarkdownAlert(block)
            is NewLine -> MarkdownNewLine()
        }
    }
}

@Composable
fun MarkdownHeading(heading: Heading) {
    Box(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val style = when (heading.level) {
            1 -> MarkdownH1
            2 -> MarkdownH2
            3 -> MarkdownH3
            else -> MarkdownH1
        }

        Text(
            text = heading.content,
            style = style
        )
    }
}

@Composable
fun MarkdownParagraph(block: Paragraph) {
    val text = buildAnnotatedString { appendInlineContent(block.inlineContent) }

    Box(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = text,
            style = MarkdownBody1
        )
    }
}

@Composable
fun MarkdownCode(content: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth()
    ) {
        Row(
            Modifier.horizontalScroll(ScrollState(0))
        ) {
            Box(Modifier.padding(16.dp)) {
                Text(
                    text = content,
                    color = mediumPurple,
                    style = MarkdownCode,
                    softWrap = false
                )
            }
        }
    }
}

@Composable
fun MarkdownAlert(alert: Alert) {
    val backgroundColor = when (alert.type) {
        Alert.AlertType.Note -> peppermint
        Alert.AlertType.Warning -> peachSchnapps
    }
    val icon = when (alert.type) {
        Alert.AlertType.Warning -> R.drawable.ic_warning
        Alert.AlertType.Note -> R.drawable.ic_info
    }
    val iconTint = when (alert.type) {
        Alert.AlertType.Warning -> pomegranate
        Alert.AlertType.Note -> chateauGreen
    }

    val text = buildAnnotatedString { appendInlineContent(alert.inlineContent) }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth()
    ) {
        Box(Modifier.padding(16.dp)) {
            Row {
                Icon(
                    painterResource(icon),
                    "",
                    tint = iconTint,
                    modifier = Modifier.height(24.dp).padding(end = 16.dp)
                )
                Text(
                    text = text,
                    style = MarkdownBody1
                )
            }
        }
    }
}

@Composable
fun MarkdownNewLine() {
    Box {
        Spacer(Modifier.height(16.dp))
    }
}

// region Annotated String
fun AnnotatedString.Builder.appendInlineContent(inlineContent: List<InlineBlock>) {
    for (i in inlineContent.indices) {
        when (val block = inlineContent[i]) {
            is Link -> {
                pushStyle(SpanStyle(color = cerulean))
                append(block.content)
                pop()
            }
            is Code -> {
                pushStyle(MarkdownCode.toSpanStyle())
                pushStyle(SpanStyle(background = alabaster))
                append(block.content)
                pop()
                pop()
            }
            is Text -> append(block.content)
        }
    }
}
// endregion