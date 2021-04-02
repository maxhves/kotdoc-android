package no.mhl.markdownparser

enum class Pattern(val literal: String) {
    Heading("^#+\\s*(.*)\$"),
    FencedCode("^ {0,2}```(.*)\$"),
    Alert("^>+.*\$"),
    AlertType("^\\{type=\".+\"\\}\$"),
    Paragraph("^.*\$"),
    PageTitle("^(\\[\\/\\/\\]:\\s#\\s\\(title:\\s.+\\))\$"),
    Bullet("^\\*\\s(.*)\$"),
    Indentation("^  .+\$")
}