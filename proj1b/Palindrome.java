public class Palindrome {
  public Deque<Character> wordToDeque(String word) {
    Deque<Character> d = new LinkedListDeque<>();
    for (char c : word.toCharArray()) {
      d.addLast(c);
    }
    return d;
  }

  private static boolean _isPalindrome(Deque<Character> d, CharacterComparator cc) {
    if (d.size() <= 1) return true;
    return cc.equalChars(d.removeFirst(), d.removeLast()) && _isPalindrome(d, cc);
  }

  public boolean isPalindrome(String word) {
    return _isPalindrome(wordToDeque(word), (x, y) -> x == y);
  }

  public boolean isPalindrome(String word, CharacterComparator cc) {
    return _isPalindrome(wordToDeque(word), cc);
  }
}
