public class OffByN implements CharacterComparator {
  private final int N;

  public OffByN(int N) {
    this.N = N;
  }

  @Override
  public boolean equalChars(char x, char y) {
    int diff = (int) x - (int) y;
    return Math.abs(diff) == this.N;
  }
}
