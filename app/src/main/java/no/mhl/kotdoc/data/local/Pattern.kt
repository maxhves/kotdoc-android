package no.mhl.kotdoc.data.local

enum class Pattern(val literal: String) {
    Heading("^#+\\s*(.*)\$"),
    FencedCode("^```(.*)\$"),
    Alert("^>+.*\$"),
    AlertType("^\\{type=\".+\"\\}\$"),
    Paragraph("^.*\$"),
    PageTitle("(\\[\\/\\/\\]: # \\(title:.+\\))")
}