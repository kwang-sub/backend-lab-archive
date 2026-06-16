package gpt.recursion

fun main() {

}

data class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)

fun preorder(node: TreeNode?) {
    if (node == null) return
    println(node.value)
    preorder(node.left)
    preorder(node.right)
}