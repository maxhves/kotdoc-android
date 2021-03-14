package no.mhl.kotdoc.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.data.local.*
import no.mhl.kotdoc.ui.theme.*

@Composable
fun MarkdownDocument(document: Document) {
    MarkdownBlocks(document)
}

@Composable
fun MarkdownBlocks(document: Document) {
    var node = document.first()
    while (node != null) {
        when (node.block) {
            is Heading -> MarkdownHeading(node.block as Heading)
            is FencedCode -> MarkdownCode(node.block.content)
            is Paragraph -> MarkdownParagraph(node.block.content)
            is Alert -> MarkdownAlert(node.block as Alert)
            is NewLine -> MarkdownNewLine()
        }
        node = node.next
    }
}

@Composable
fun MarkdownHeading(heading: Heading) {
    Box(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val style = when (heading.level) {
            1 -> MaterialTheme.typography.h4
            2 -> MaterialTheme.typography.h5
            3 -> MaterialTheme.typography.h6
            else -> MaterialTheme.typography.h4
        }

        Text(
            text = heading.content,
            style = style
        )
    }
}

@Composable
fun MarkdownParagraph(content: String) {
    Box(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun MarkdownCode(content: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth()
    ) {
        Box(Modifier.padding(16.dp)) {
            Text(
                text = content,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = sorbus,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun MarkdownAlert(alert: Alert) {
    val backgroundColor = when (alert.type) {
        Alert.AlertType.Note -> peppermint
        Alert.AlertType.Warning -> peachSchnapps
    }

    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth()
    ) {
        Box(Modifier.padding(16.dp)) {
            Text(
                text = alert.content,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun MarkdownNewLine() {
    Box {
        Spacer(Modifier.height(16.dp))
    }
}