package no.mhl.kotdoc.data.local

class Document {

    // region Root
    private var head: Node? = null
    // endregion

    // region Accessors
    fun first(): Node? = head
    fun last(): Node? {
        var node = head
        return if (node != null) {
            while (node?.next != null) {
                node = node.next
            }
            node
        } else {
            null
        }
    }
    // endregion

    // region Insertion
    fun append(block: Block) {
        val newNode = Node(block)
        val lastNode = last()

        if (lastNode != null) {
            newNode.previous = lastNode
            lastNode.next = newNode
        } else {
            head = newNode
        }
    }
    // endregion

}