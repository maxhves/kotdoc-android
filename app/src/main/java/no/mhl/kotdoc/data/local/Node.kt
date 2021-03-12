package no.mhl.kotdoc.data.local

data class Node(
    var block: Block,
    var next: Node? = null,
    var previous: Node? = null
)