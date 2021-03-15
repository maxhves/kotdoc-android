package no.mhl.kotdoc.data.local

class InlineParser {

    // region Local Properties
    private var blocks: MutableList<InlineBlock> = mutableListOf()
    private var chars: List<Char> = listOf()

    private var currentIndex: Int = 0
    private val currentChar: Char
        get() = chars[currentIndex]
    private val validIndex: Boolean
        get() = currentIndex <= chars.lastIndex
    // endregion

    // region Value Functions
    private val matchFor = { char: Char -> currentChar == char }
    // endregion

    // region Parse Inline Blocks
    fun parseToInlineBlocks(paragraph: String): List<InlineBlock> {
        chars = paragraph.toCharArray().toList()
        beginInlineParse()
        return blocks
    }
    // endregion

    // region Inline Block Parsing
    private fun beginInlineParse() {
        if (validIndex) {
            when (currentChar) {
                '`' -> parseCode()
                '[' -> parseLink()
                else -> parseText()
            }
        }
    }
    // endregion

    // region Code
    private fun parseCode() {
        val code = Code("")
        var tagMatch = 0

        while (validIndex && tagMatch < 2) {
            if (currentChar == '`') {
                tagMatch++
            } else {
                code.content += currentChar
            }
            currentIndex++
        }

        blocks.add(code)
        beginInlineParse()
    }
    // endregion

    // region Link
    private fun parseLink() {
        val link = Link("")
        var linkTagMatches = 0

        while (linkTagMatches < 2) {
            if (currentChar == '(' || currentChar == ')') {
                linkTagMatches++
            }

            if (linkTagMatches == 0 && currentChar != '[' && currentChar != ']') {
                link.content += currentChar
            }

            currentIndex++
        }

        blocks.add(link)
        beginInlineParse()
    }
    // endregion

    // region Text
    private fun parseText() {
        val text = Text("")

        while (validIndex && isComplexBlock().not()) {
            text.content += currentChar
            currentIndex++
        }

        blocks.add(text)
        beginInlineParse()
    }
    // endregion

    // region Helpers
    private fun isComplexBlock(): Boolean {
        return when {
            matchFor('`') -> true
            matchFor('[') -> true
            else -> false
        }
    }
    // endregion

}