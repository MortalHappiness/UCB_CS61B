public class ArrayDeque<T> {
  private static final int INITIAL_ARRAY_SIZE = 8;

  private T[] items;
  private int size;
  private int nextFirst;
  private int nextLast;

  public ArrayDeque() {
    this.items = (T[]) new Object[INITIAL_ARRAY_SIZE];
    this.size = 0;
    this.nextFirst = 0;
    this.nextLast = 1;
  }

  public ArrayDeque(ArrayDeque<T> other) {
    this.items = (T[]) new Object[other.items.length];
    this.size = other.size;
    this.nextFirst = other.nextFirst;
    this.nextLast = other.nextLast;
    System.arraycopy(other.items, 0, this.items, 0, other.items.length);
  }

  private int prevIndex(int index) {
    return (index == 0) ? (this.items.length - 1) : (index - 1);
  }

  private int nextIndex(int index) {
    return (index == this.items.length - 1) ? (0) : (index + 1);
  }

  private void expandArray() {
    T[] newArray = (T[]) new Object[this.items.length << 1];

    int index = nextIndex(this.nextFirst);
    for (int i = 0; i < this.size; ++i) {
      newArray[i] = this.items[index];
      index = nextIndex(index);
    }

    this.items = newArray;
    this.nextFirst = newArray.length - 1;
    this.nextLast = this.size;
  }

  private void shrinkArray() {
    T[] newArray = (T[]) new Object[this.items.length >> 1];

    int index = nextIndex(this.nextFirst);
    for (int i = 0; i < this.size; ++i) {
      newArray[i] = this.items[index];
      index = nextIndex(index);
    }

    this.items = newArray;
    this.nextFirst = newArray.length - 1;
    this.nextLast = this.size;
  }

  public void addFirst(T item) {
    if (this.size == this.items.length) expandArray();
    this.items[this.nextFirst] = item;
    this.size += 1;
    this.nextFirst = prevIndex(this.nextFirst);
  }

  public void addLast(T item) {
    if (this.size == this.items.length) expandArray();
    this.items[this.nextLast] = item;
    this.size += 1;
    this.nextLast = nextIndex(this.nextLast);
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  public void printDeque() {
    if (this.size == 0) return;
    int index = nextIndex(this.nextFirst);
    System.out.print(this.items[index]);
    index = nextIndex(index);
    for (int i = 1; i < this.size; ++i) {
      System.out.print(" " + this.items[index]);
      index = nextIndex(index);
    }
    System.out.print("\n");
  }

  public T removeFirst() {
    if (this.size == 0) return null;
    if (this.items.length > INITIAL_ARRAY_SIZE && this.size <= (this.items.length >> 2))
      shrinkArray();
    this.size -= 1;
    int index = nextIndex(this.nextFirst);
    T item = this.items[index];
    this.items[index] = null;
    this.nextFirst = index;
    return item;
  }

  public T removeLast() {
    if (this.size == 0) return null;
    if (this.items.length > INITIAL_ARRAY_SIZE && this.size <= (this.items.length >> 2))
      shrinkArray();
    this.size -= 1;
    int index = prevIndex(this.nextLast);
    T item = this.items[index];
    this.items[index] = null;
    this.nextLast = index;
    return item;
  }

  public T get(int index) {
    if (index < 0 || index >= this.size) return null;
    return this.items[(this.nextFirst + 1 + index) % this.items.length];
  }
}
