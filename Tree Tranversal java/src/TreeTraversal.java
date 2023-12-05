class TreeTraversal<T> {
    Node<T> root;

    public TreeTraversal() {
        root = null;
    }

    // Metode untuk melakukan inorder traversal
    public void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    // Metode untuk melakukan preorder traversal
    public void preOrderTraversal(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Metode untuk melakukan postorder traversal
    public void postOrderTraversal(Node<T> node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }
}