import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("assbssa"));
        assertFalse(palindrome.isPalindrome("assb"));
    }

    @Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("aeb", cc));
        assertFalse(palindrome.isPalindrome("assb", cc));
        assertTrue(palindrome.isPalindrome("astb", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
    }

}
