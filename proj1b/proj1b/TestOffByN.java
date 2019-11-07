import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    CharacterComparator offBy2 = new OffByN(2);
    CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testoffBy2() {
        assertFalse(offBy2.equalChars('a','b'));
        assertTrue(offBy2.equalChars('c','e'));
    }

    @Test
    public void testoffBy3() {
        assertFalse(offBy3.equalChars('a','b'));
        assertTrue(offBy3.equalChars('c','f'));
        assertFalse(offBy3.equalChars('a','c'));
        assertTrue(offBy3.equalChars('b','e'));
    }
}
