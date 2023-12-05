class Node<T> {
    T data;
    Node<T> left, right;

    public Node(T item) {
        data = item;
        left = right = null;
    }
}