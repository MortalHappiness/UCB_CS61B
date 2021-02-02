import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        // The bug is in Flik.java, should use a.equals(b) instead of a == b, or change Integer to int
        for (int i = 0; i < 500; ++i) {
            assertTrue(String.format("Flik failed when i = %d", i), Flik.isSameNumber(i, i));
        }
    }
}
