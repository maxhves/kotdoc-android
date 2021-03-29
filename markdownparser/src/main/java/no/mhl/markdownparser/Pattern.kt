package no.mhl.markdownparser

enum class Pattern(val literal: String) {
    Heading("^#+\\s*(.*)\$"),
    FencedCode("^```(.*)\$"),
    Alert("^>+.*\$"),
    AlertType("^\\{type=\".+\"\\}\$"),
    Paragraph("^.*\$"),
    PageTitle("^(\\[\\/\\/\\]:\\s#\\s\\(title:\\s.+\\))\$"),
    Bullet("^\\*\\s(.*)\$")
}