import com.example.lab3.TargetChecker;
import com.example.lab3.Point;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class HitTest {
    @Test
    public void testBoundaryCases() {
        TargetChecker checker = new TargetChecker();
        assertEquals("Hit", checker.checkAll(new Point("-0.5", "-0.5", "1")));
        assertEquals("Hit", checker.checkAll(new Point("0", "0", "1")));
        assertEquals("Hit", checker.checkAll(new Point("-0.5", "0", "1")));
    }

    @Test
    public void testInsideCases() {
        TargetChecker checker = new TargetChecker();
        assertEquals("Hit", checker.checkAll(new Point("-0.25", "-0.25", "1")));
        assertEquals("Hit", checker.checkAll(new Point("0.25", "0.25", "1")));
        assertEquals("Hit", checker.checkAll(new Point("-0.25", "0.25", "1")));
    }

    @Test
    public void testOutsideCases() {
        TargetChecker checker = new TargetChecker();
        assertEquals("No hit", checker.checkAll(new Point("-1", "-1", "1")));
        assertEquals("No hit", checker.checkAll(new Point("1", "1", "1")));
        assertEquals("No hit", checker.checkAll(new Point("-1", "1", "1")));
    }

    @Test
    public void testDifferentRValues() {
        TargetChecker checker = new TargetChecker();
        // Test with R = 2
        assertEquals("Hit", checker.checkAll(new Point("-1", "-1", "2")));
        assertEquals("Hit", checker.checkAll(new Point("0", "0", "2")));
        assertEquals("No hit", checker.checkAll(new Point("-2", "-2", "2")));
        assertEquals("No hit", checker.checkAll(new Point("2", "2", "2")));

        // Test with R = 0.5
        assertEquals("No hit", checker.checkAll(new Point("-0.5", "-0.5", "0.5")));
        assertEquals("Hit", checker.checkAll(new Point("0", "0", "0.5")));
        assertEquals("No hit", checker.checkAll(new Point("-1", "-1", "0.5")));
        assertEquals("No hit", checker.checkAll(new Point("1", "1", "0.5")));
    }

}