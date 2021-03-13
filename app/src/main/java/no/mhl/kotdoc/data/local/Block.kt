package no.mhl.kotdoc.data.local

// region Base Block
open class Block(
    open var content: String
)
// endregion

// region Markdown Blocks
data class Heading(
    override var content: String,
    val level: Int = 1
) : Block(content)

data class Alert(
    override var content: String
) : Block(content)

data class FencedCode(
    override var content: String
) : Block(content)

data class Paragraph(
    override var content: String
) : Block(content)

data class NewLine(
    override var content: String
) : Block(content)
// endregion