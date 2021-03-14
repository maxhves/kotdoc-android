package no.mhl.kotdoc.data.local

import no.mhl.kotdoc.data.local.Pattern.*

class BlockParser(
    private val lines: List<String>
) {

    // region Root Document
    private val document: Document = Document()
    // endregion

    // region Properties
    private var title: String = ""
    private var currentIndex: Int = 0
    private val lastIndex: Int = lines.lastIndex
    // endregion

    // region Document Retrieval
    fun parseAsDocument(): Document {
        beginBlockParse()
        return document
    }
    // endregion

    // region Block Parsing
    private fun beginBlockParse() {
        if (currentIndex <= lastIndex) {
            when {
                matchFor(FencedCode) -> parseFencedCode()
                matchFor(Alert) -> parseAlert()
                matchFor(Heading) -> parseHeading()
                matchFor(PageTitle) -> parsePageTitle()
                blankLine() -> parseNewLine()
                else -> parseParagraph()
            }
        }
    }
    // endregion

    // region Paragraph
    private fun parseParagraph() {
        val paragraph = Paragraph("")

        while (currentIndex <= lastIndex && isComplexBlock().not()) {
            paragraph.content += lines[currentIndex]
            currentIndex++
        }

        document.append(paragraph)
        beginBlockParse()
    }
    // endregion

    // region Heading
    private fun parseHeading() {
        val level = lines[currentIndex].count { it == '#' }
        val line = lines[currentIndex].replace("#", "").trim()

        document.append(Heading(line, level))
        currentIndex++
        beginBlockParse()
    }
    // endregion

    // region New Line
    private fun parseNewLine() {
        document.append(NewLine(""))
        currentIndex++
        beginBlockParse()
    }
    // endregion

    // region Fenced Code
    private fun parseFencedCode() {
        val code = FencedCode("")
        var codeBlockMatches = 0

        while (codeBlockMatches < 2) {
            if (matchFor(FencedCode))  {
                codeBlockMatches++
            } else {
                val potentialNewLine = if (code.content == "") "" else "\n"
                code.content += "$potentialNewLine${lines[currentIndex]}"
            }
            currentIndex++
        }

        document.append(code)
        beginBlockParse()
    }
    // endregion

    //region Alert
    private fun parseAlert() {
        val alert = Alert("")
        var fullMatch = false

        while(fullMatch.not()) {
            fullMatch = matchFor(AlertType)

            if (fullMatch.not()) {
                val line = lines[currentIndex].replace(">", "")
                alert.content += line
            } else {
                // TODO Create a more sophisticated approach to this
                val type = lines[currentIndex].replace("{type=\"", "").replace("\"}", "")
                alert.type = Alert.AlertType.fromString(type)
            }

            currentIndex++
        }

        document.append(alert)
        beginBlockParse()
    }

    private fun parsePageTitle() {
        val title = lines[currentIndex].replace("[//]: # (title: ", "").replace(")", "")
        this.title = title
        currentIndex++
        beginBlockParse()
    }
    // endregion

    // region Helper Methods
    private fun isComplexBlock(): Boolean {
        return when {
            matchFor(FencedCode) -> true
            matchFor(Alert) -> true
            matchFor(Heading) -> true
            matchFor(PageTitle) -> true
            blankLine() -> true
            else -> false
        }
    }

    private fun matchFor(pattern: Pattern): Boolean {
        return lines[currentIndex].matches(Regex(pattern.literal))
    }

    private fun blankLine(): Boolean {
        return lines[currentIndex].isBlank()
    }
    // endregion

}