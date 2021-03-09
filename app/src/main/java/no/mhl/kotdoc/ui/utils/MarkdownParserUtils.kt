package no.mhl.kotdoc.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.io.Reader

open class Element(open var content: String)

data class H2(override var content: String) : Element(content)
data class H3(override var content: String) : Element(content)
data class NewLine(override var content: String) : Element(content)
data class Bullet(override var content: String) : Element(content)
data class Info(override var content: String): Element(content)
data class CodeBlock(override var content: String, var complete: Boolean) : Element(content)

fun parseMarkdown(reader: Reader?): List<Element> {
    if (reader == null) return emptyList()

    val elements = mutableListOf<Element>()

    reader.readLines().forEachIndexed { index, line ->

        if (elements.lastIndex > -1) {
            val element = elements[elements.lastIndex]
            if (element is CodeBlock) {
                if (element.complete.not()) {
                    if (line.startsWith("```") || line.startsWith("  ```")) {
                        (elements[elements.lastIndex] as CodeBlock).complete = true
                    } else {
                        if (element.content == "") {
                            element.content += line
                        } else {
                            element.content += "\n$line"
                        }
                    }
                    return@forEachIndexed
                }
            }
        }

        if (line.startsWith("[//]: #")) {
            return@forEachIndexed
        } else if (line.startsWith("## ")) {
            elements.add(H2(line.replace("## ", "")))
        } else if (line.startsWith("### ")) {
            elements.add(H3(line.replace("### ", "")))
        } else if (line == "") {
            elements.add(NewLine(line))
        } else if (line.startsWith("*")) {
            elements.add(Bullet(line.replace("*", "\u2022")))
        } else if (line.startsWith(">")) {
            if (elements[elements.lastIndex] is Info) {
                elements[elements.lastIndex].content += " ${line.replace(">", "")}"
            } else {
                elements.add(Info(line.replace(">", "")))
            }
        } else if (line.startsWith("```") || line.startsWith("  ```")) {
            elements.add(CodeBlock("", false))
        } else {
            if (elements[elements.lastIndex] is NewLine) {
                elements.add(Element(line))
            } else {
                if (elements.lastIndex > -1) {
                    elements[elements.lastIndex].content += " $line"
                }
            }
        }
    }

    if (elements[0].content == "") {
        elements.removeAt(0)
    }

    return elements
}