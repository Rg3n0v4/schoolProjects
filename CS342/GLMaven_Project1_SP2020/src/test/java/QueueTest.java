import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueueTest {

    GenericQueue<Integer> q;

    @BeforeEach
        //this is created before every test case
    void init()
    {
        q = new GenericQueue<Integer>(300);
    }

    @Test
        //tests if the queue was created
    void testInitGS()
    {
        assertEquals("GenericQueue", q.getClass().getName(),"init failed on GS");
    }

    @Test
        //tests if the node was initialized in the stack
    void testInitNode()
    {
        assertEquals("GenericList$Node", q.getHead().getClass().getName(), "node not init");
    }

    @Test
        //tests if you're able to remove
    void testNodeVal()
    {
        assertEquals(300, q.dequeue(), "value wrong in Node: not 200");
    }

    @Test
        //tests if the head is null after dequeue
    void testEmptyList()
    {
        q.dequeue();
        assertNull(q.getHead());
    }

    @Test
        //tests if enqueue onto the queue works
    void testEnqueueNode()
    {
        q.enqueue(200);
        assertEquals(200, q.getHead().next.data, "push() is implemented wrong");
    }

    @Test
        //tests if pushing 2 onto the stack and then popping 4 off the stack
    void testPushTwoAndPopFour()
    {
        q.enqueue(100);
        q.enqueue(200);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        assertNull(q.getHead());
    }

    @Test
        //tests if it'q possible for the length of the stack to be negative
    void testGetLengthIsNegative()
    {
        q.dequeue();
        q.dequeue();
        assertEquals(0, q.getLength(), "length of Stack is negative");
    }

    @Test
        //tests if the length of the stack is incremented
    void testGetLengthIncremented()
    {
        q.enqueue(100);
        q.enqueue(200);
        assertEquals(3, q.getLength(), "length of Stack doesn't increment");
    }

    @Test
        //tests if the data in the head of the stack exists
    void testHeadOfQueue()
    {
        assertNotNull(q.getHead());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testFirstElement(int input)
    {
        q.enqueue(input);
        assertEquals(input, q.getHead().next.data, "Element wasn't pushed properly");
    }

    @Test
        //tests the stack
    void testInitHead()
    {
        assertEquals(300, q.getHead().data, "Stack wasn't initialized");
    }

    @Test
        //tests if getLength works and returns properly
    void testGetLength()
    {
        assertEquals(1, q.getLength(), "getLength() didn't return properly");
    }

    @Test
        //tests dumpList() length
    void testDumpListLength()
    {
        q.enqueue(200);
        q.enqueue(400);

        q.dumpList();
        assertEquals( 0, q.getLength(), "testing dumpList() length doesn't work");
    }
}

