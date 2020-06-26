import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        int a = 8, b = 9;
        assertEquals(false, Flik.isSameNumber(a, b));

        int a1 = 0, b1 = 0;
        assertEquals(true, Flik.isSameNumber(a1, b1));

        int a2 = -1, b2 = -1;
        assertEquals( true, Flik.isSameNumber(a2, b2));

        int a3 = 128, b3 = 128;
        assertEquals(true, Flik.isSameNumber(a3, b3));
    }
}