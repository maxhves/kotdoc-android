package no.mhl.markdownparser

import no.mhl.markdownparser.Pattern.*

class BlockParser() {

    // region Local Properties
    private val blocks: MutableList<Block> = mutableListOf()
    private var markdown: List<String> = listOf()

    private var currentIndex: Int = 0
    private val currentLine: String
        get() = markdown[currentIndex]
    private val validIndex: Boolean
        get() = currentIndex <= markdown.lastIndex
    // endregion

    // region Value Functions
    private val matchFor = { pattern: Pattern -> currentLine.matches(Regex(pattern.literal)) }
    private val blankLine = { currentLine.isBlank() }
    // endregion

    // region Parse Exposure
    fun parseToBlocks(lines: List<String>): List<Block> {
        markdown = lines
        beginBlockParse()
        return blocks
    }
    // endregion

    // region Block Parsing
    private fun beginBlockParse() {
        if (validIndex) {
            when {
                matchFor(FencedCode) -> parseFencedCode()
                matchFor(Alert) -> parseAlert()
                matchFor(Heading) -> parseHeading()
                matchFor(PageTitle) -> parsePageTitle()
                // matchFor(Bullet) -> parseBulletGroup()
                blankLine() -> parseNewLine()
                else -> parseParagraph()
            }
        }
    }
    // endregion

    // region Paragraph
    private fun parseParagraph() {
        val paragraph = Paragraph("")

        while (validIndex && isComplexBlock().not()) {
            paragraph.content += if (paragraph.content.isEmpty()) currentLine else " $currentLine"
            currentIndex++
        }

        paragraph.inlineContent = InlineParser().parseToInlineBlocks(paragraph.content)
        appendBlock(paragraph)
    }
    // endregion

    // region Heading
    private fun parseHeading() {
        val level = currentLine.count { it == '#' }
        val line = currentLine.replace("#", "").trim()

        currentIndex++
        appendBlock(Heading(line, level))
    }
    // endregion

    // region New Line
    private fun parseNewLine() {
        currentIndex++
        appendBlock(NewLine(""))
    }
    // endregion

    // region Fenced Code
    private fun parseFencedCode() {
        val code = FencedCode("")
        var codeBlockMatches = 0
        val indented = matchFor(Indentation)
        code.indented = indented

        while (codeBlockMatches < 2) {
            if (matchFor(FencedCode))  {
                codeBlockMatches++
            } else {
                val potentialNewLine = if (code.content == "") "" else "\n"
                var line = currentLine

                if (indented) {
                    line = currentLine.removePrefix("  ")
                }

                code.content += "$potentialNewLine$line"
            }
            currentIndex++
        }

        appendBlock(code)
    }
    // endregion

    //region Alert
    private fun parseAlert() {
        val alert = Alert("")
        var fullMatch = false

        while(fullMatch.not()) {
            fullMatch = matchFor(AlertType)

            if (fullMatch.not()) {
                val line = currentLine.removePrefix(">").trim()

                if (line.isNotBlank()) {
                    alert.content += if (alert.content.isEmpty()) line else " $line"
                }
            } else {
                val type = currentLine.removePrefix("{type=\"").removeSuffix("\"}")
                alert.type = Alert.AlertType.fromString(type)
            }

            currentIndex++
        }

        alert.inlineContent = InlineParser().parseToInlineBlocks(alert.content)
        appendBlock(alert)
    }
    // endregion

    // region Bullet Group
    private fun parseBulletGroup() {
        val bullets = mutableListOf<BulletGroup.BulletedText>()
        val group = BulletGroup("", bullets)

        while (blankLine().not() && matchFor(Indentation).not()) {
            group.content += currentLine

            val bullet = InlineParser().parseToInlineBlocks(currentLine.replace("*", "").trim())
            bullets.add(BulletGroup.BulletedText(bullet))

            currentIndex++
        }

        appendBlock(group)
        parseNewLine()
    }
    // endregion

    // region Page Title
    private fun parsePageTitle() {
        // TODO Here we can get the page title.
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
            //matchFor(Bullet) -> true
            blankLine() -> true
            else -> false
        }
    }

    private fun appendBlock(block: Block) {
        blocks.add(block)
        beginBlockParse()
    }
    // endregion

}