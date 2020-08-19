import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class IteratorIterableTest
{
    private GenericStack<Integer> s;
    private GLIterator<Integer> sIterator;

    @BeforeEach
    void init()
    {
        s = new GenericStack<Integer>(200);
    }

    @Test
    void testNextAfterPush()
    {
        s.push(100);
        sIterator = s.createIterator();
        assertEquals(100, sIterator.next(), "next() doesn't work");
    }

    @Test
    void testHasNextAfterPush()
    {
        s.push(100);
        sIterator = s.createIterator();
        assertTrue(sIterator.hasNext());
    }

    @Test
    void testHasNextAfterPop()
    {
        s.pop();
        sIterator = s.createIterator();
        assertFalse(sIterator.hasNext());
    }

    @Test
    void testNextMiddle()
    {
        s.push(50);
        sIterator = s.createIterator();
        sIterator.next();
        assertEquals(200, sIterator.next(), "testNextMiddle isn't correct");
    }

    @Test
    void testIfStackIsNull()
    {
        sIterator = s.createIterator();
        s.pop();
        s.forEach(e->assertNull(e));
    }

    @Test
    void testIfStackIsntNull()
    {
        sIterator = s.createIterator();
        s.forEach(e->assertEquals(200, e, "testIfStackIsntNull is incorrect"));
    }

    @Test
    void testIfStackHasAVal(){
        sIterator = s.createIterator();
        assertTrue(sIterator.hasNext(),"testIfStackHasAVal is incorrect");
    }
}
