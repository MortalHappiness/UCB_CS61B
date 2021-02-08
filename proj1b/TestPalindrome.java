import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
  // You must use this palindrome, and not instantiate
  // new Palindromes, or the autograder might be upset.
  static Palindrome palindrome = new Palindrome();

  @Test
  public void testWordToDeque() {
    Deque d = palindrome.wordToDeque("persiflage");
    String actual = "";
    for (int i = 0; i < "persiflage".length(); i++) {
      actual += d.removeFirst();
    }
    assertEquals("persiflage", actual);
  }

  /** test isPalindrome(String) */
  @Test
  public void testIsPalindrome1() {
    assertTrue(palindrome.isPalindrome(""));
    assertTrue(palindrome.isPalindrome("a"));
    assertTrue(palindrome.isPalindrome("racecar"));
    assertTrue(palindrome.isPalindrome("noon"));
    assertFalse(palindrome.isPalindrome("horse"));
    assertFalse(palindrome.isPalindrome("aaaaab"));
  }

  /** test isPalindrome(String, CharacterComparator) */
  @Test
  public void testIsPalindrome2() {
    CharacterComparator cc = new OffByOne();

    assertTrue(palindrome.isPalindrome("", cc));
    assertTrue(palindrome.isPalindrome("a", cc));
    assertTrue(palindrome.isPalindrome("flabke", cc));
    assertTrue(palindrome.isPalindrome("flake", cc));
    assertFalse(palindrome.isPalindrome("noon", cc));
    assertFalse(palindrome.isPalindrome("horse", cc));
    assertFalse(palindrome.isPalindrome("aaaaab", cc));
  }
}
