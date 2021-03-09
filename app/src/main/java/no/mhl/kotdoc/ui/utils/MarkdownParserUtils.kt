package no.mhl.kotdoc.ui.utils

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import org.commonmark.node.*

@Composable
fun MarkdownDocument(parent: Node) {
    MarkdownBlocks(parent)
}

@Composable
fun MarkdownBlocks(parent: Node) {
    var child = parent.firstChild

    while (child != null) {
        when (child) {
            is Paragraph -> {}
            is Heading -> {}
            is BlockQuote -> {}
            is FencedCodeBlock -> {}
            is IndentedCodeBlock -> {}
            is BulletList -> {}
            is OrderedList -> {}
            is ThematicBreak -> {}
        }
        child = child.next
    }
}

fun AnnotatedString.Builder.appendMarkdown(
    parent: Node,
    colors: Colors
) {
    var child = parent.firstChild
    while (child != null) {
        when (child) {
            is Paragraph -> {}
            is Text -> append(child.literal)
            is Emphasis -> {}
            is StrongEmphasis -> {}
            is Code -> {}
            is HardLineBreak -> {}
            is Link -> {}
        }
        child = child.next
    }
}

// region Markdown Components
@Composable
fun MarkdownHeading(heading: Heading, modifier: Modifier = Modifier) {
    
}
// endregion