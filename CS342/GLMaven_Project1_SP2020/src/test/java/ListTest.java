import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ListTest {

	GenericStack<Integer> s;

	@BeforeEach
	//this is created before every test case
	void init() {
		s = new GenericStack<Integer>(300);
	}

	@Test
	//tests if the stack was created
	void testInitGS() {
		assertEquals("GenericStack", s.getClass().getName(),"init failed on GS");
	}

	@Test
	//tests if the node was initialized in the stack
	void testInitNode() {
		assertEquals("GenericList$Node", s.getHead().getClass().getName(), "node not init");
	}

	@Test
	//tests if you're able to pop off the stack
	void testNodeVal() {
		assertEquals(300, s.pop(), "value wrong in Node: not 200");
	}

	@Test
	//tests if the head is null after pop
	void testEmptyList() {
		s.pop();
		assertNull(s.getHead());
	}

	@Test
	//tests if pushing onto the stack works
	void testPushNode()
	{
		s.push(200);
		assertEquals(200, s.getHead().data, "push() is implemented wrong");
	}

	@Test
	//tests if pushing 2 onto the stack and then popping 4 off the stack
	void testPushTwoAndPopFour()
	{
		s.push(100);
		s.push(200);
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		assertNull(s.getHead());
	}

	@Test
	//tests if it's possible for the length of the stack to be negative
	void testGetLengthIsNegative()
	{
		s.pop();
		s.pop();
		assertEquals(0, s.getLength(), "length of Stack is negative");
	}

	@Test
	//tests if the length of the stack is incremented
	void testGetLengthIncremented()
	{
		s.push(100);
		s.push(200);
		assertEquals(3, s.getLength(), "length of Stack doesn't increment");
	}

	@Test
	//tests if the data in the head of the stack exists
	void testHeadOfStack()
	{
		s.push(100);
		assertNotNull(s.getHead());
	}

	@Test
	void testGetLengthDecremented()
	{
		s.push(100);
		s.push(200);
		s.pop();
		assertEquals(2, s.getLength(), "length of Stack isn't decremented");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void testFirstElement(int input)
	{
		s.push(input);
		assertEquals(input, s.getHead().data, "Element wasn't pushed properly");
	}

	@Test
	//tests the stack
	void testInitHead()
	{
		assertEquals(300, s.getHead().data, "Stack wasn't initialized");
	}

	@Test
	//tests if getLength works and returns properly
	void testGetLength()
	{
		assertEquals(1, s.getLength(), "getLength() didn't return properly");
	}

	@Test
	//tests dumpList() length
	void testDumpListLength()
	{
		s.push(200);
		s.push(400);

		s.dumpList();
		assertEquals( 0, s.getLength(), "testing dumpList() length doesn't work");
	}

}
