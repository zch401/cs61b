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

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("boy"));
    }

    @Test
    public void testNewisPalindrome() {
        OffByOne offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("cat", offByOne));
        assertTrue(palindrome.isPalindrome("bbc", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));

        OffByN offBy2 = new OffByN(2);
        assertFalse(palindrome.isPalindrome("aaa", offBy2));
        assertTrue(palindrome.isPalindrome("abc", offBy2));
    }
}     /*Uncomment this class once you've created your Palindrome class. */