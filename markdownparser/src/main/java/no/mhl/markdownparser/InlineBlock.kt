package no.mhl.markdownparser

// region Base Inline Block
open class InlineBlock(
    open var content: String
)
// endregion

// region Inline Blocks
data class Bold(
    override var content: String
) : InlineBlock(content)

data class Italic(
    override var content: String
) : InlineBlock(content)

data class Code(
    override var content: String
) : InlineBlock(content)

data class Link(
    override var content: String
) : InlineBlock(content)

data class Text(
    override var content: String
) : InlineBlock(content)
// endregion