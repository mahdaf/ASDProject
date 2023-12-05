public class Main {
    public static void main(String[] args) {
        TreeTraversal<Integer> tree = new TreeTraversal<>();
        tree.root = new Node<>(1);
        tree.root.left = new Node<>(2);
        tree.root.right = new Node<>(3);
        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);

        System.out.print("Inorder :");
        tree.inOrderTraversal(tree.root);

        System.out.print("\nPreorder :");
        tree.preOrderTraversal(tree.root);

        System.out.print("\nPostorder :");
        tree.postOrderTraversal(tree.root);
    }
}