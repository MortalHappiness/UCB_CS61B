public class LinkedListDeque<T> implements Deque<T> {
  private static class Node<T> {
    private T item;
    private Node<T> prev;
    private Node<T> next;

    public Node() {
      this.item = null;
      this.prev = null;
      this.next = null;
    }

    public Node(T item, Node<T> prev, Node<T> next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  private int size;
  private Node<T> sentinel;

  public LinkedListDeque() {
    this.size = 0;
    this.sentinel = new Node<>();
    this.sentinel.prev = this.sentinel;
    this.sentinel.next = this.sentinel;
  }

  public LinkedListDeque(LinkedListDeque<T> other) {
    this.size = 0;
    this.sentinel = new Node<>();
    this.sentinel.prev = this.sentinel;
    this.sentinel.next = this.sentinel;

    Node<T> node = other.sentinel.next;
    for (int i = 0; i < other.size(); i++) {
      this.addLast(node.item);
      node = node.next;
    }
  }

  @Override
  public void addFirst(T item) {
    Node<T> node = new Node<>(item, this.sentinel, this.sentinel.next);
    node.prev.next = node;
    node.next.prev = node;
    this.size += 1;
  }

  @Override
  public void addLast(T item) {
    Node<T> node = new Node<>(item, this.sentinel.prev, this.sentinel);
    node.prev.next = node;
    node.next.prev = node;
    this.size += 1;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void printDeque() {
    if (this.size == 0) return;
    Node<T> node = this.sentinel.next;
    System.out.print(node.item);
    node = node.next;
    for (int i = 1; i < this.size; i++) {
      System.out.print(" " + node.item);
      node = node.next;
    }
    System.out.print("\n");
  }

  @Override
  public T removeFirst() {
    if (this.size == 0) return null;
    Node<T> node = this.sentinel.next;
    node.prev.next = node.next;
    node.next.prev = node.prev;
    this.size -= 1;
    return node.item;
  }

  @Override
  public T removeLast() {
    if (this.size == 0) return null;
    Node<T> node = this.sentinel.prev;
    node.prev.next = node.next;
    node.next.prev = node.prev;
    this.size -= 1;
    return node.item;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= this.size) return null;
    Node<T> node = this.sentinel.next;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.item;
  }

  private T _getRecursive(Node<T> node, int index) {
    if (index == 0) return node.item;
    return _getRecursive(node.next, index - 1);
  }

  public T getRecursive(int index) {
    if (index < 0 || index >= this.size) return null;
    return _getRecursive(this.sentinel.next, index);
  }
}
