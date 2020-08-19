
/*
	Name: Raphael Genova
	NetId: rgenov2
	University Email: rgenov2@uic.edu
	Description:
*/

public class GLProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello generic lists");

		//run test cases here
		//need to test out each of the functions I currently have

		//for testing queue
//		GenericQueue<Integer> testQ = new GenericQueue<Integer>(1);
//		testQ.enqueue(2);
//		testQ.enqueue(3);
//		System.out.println("In queue before dequeue");
//		testQ.print();
//		testQ.dequeue();
//		testQ.dequeue();
//		testQ.dequeue();
//		System.out.println("In queue after dequeue");
//		testQ.print();
//		testQ.enqueue(4);
//		System.out.println("In queue after popping everything off the queue:");
//		testQ.print();
//
//		GenericStack<Integer> testS = new GenericStack<Integer>(1);
//		testS.push(2);
//		testS.push(3);
//		System.out.println("In stack before pop");
//		testS.print();
//		testS.pop();
//		testS.pop();
//		testS.pop();
//		System.out.println("In stack after dequeue");
//		testS.print();
//		testS.push(4);
//		System.out.println("In stack after popping everything off the stack:");
//		testS.print();
//
		//enqueue and dequeue test
//		GenericQueue<Integer> testQ = new GenericQueue<Integer>(1);
//		testQ.enqueue(2);
//		testQ.enqueue(3);
//		GLIterator<Integer> testIteratorOne = new GLIterator<Integer>(testQ.getHead());
//		System.out.println("testQ before dumpList():");
//		for(int i = 0; i < testQ.getLength(); i++)
//		{
//			System.out.println(testIteratorOne.next());
//		}
//		testQ.dequeue();
//		testQ.dequeue();
//		testQ.enqueue(4);
//		testQ.enqueue(5);
//		GLIterator<Integer> testIterator = new GLIterator<Integer>(testQ.getHead());
//		System.out.println("testQ after dumpList()");
//		for(int i = 0; i < testQ.getLength(); i++)
//		{
//			System.out.println(testIterator.next());
//		}

		//popping and then pushing back into the stack
//		GenericStack<Integer> testS = new GenericStack<Integer>(1);
//		testS.push(2);
//		testS.push(3);
//		GLIterator<Integer> testIteratorOne = new GLIterator<Integer>(testS.getHead());
//		System.out.println("testS before dumpList():");
//		for(int i = 0; i < testS.getLength(); i++)
//		{
//			System.out.println(testIteratorOne.next());
//		}
//		testS.pop();
//		testS.pop();
////		testS.dumpList();
//		testS.push(4);
//		testS.push(5);
////		System.out.println("testQ after dumpList()");
//		GLIterator<Integer> testIterator = new GLIterator<Integer>(testS.getHead());
//		System.out.println("testS after popping and then adding new values in");
//		for(int i = 0; i < testS.getLength(); i++)
//		{
//			System.out.println(testIterator.next());
//		}

//		GenericQueue<Integer> testQ = new GenericQueue<Integer>(null);
//		GLIterator<Integer> testIteratorOne = new GLIterator<Integer>(testQ.getHead());
//		System.out.println("testQ empty:");
//		for(int i = 0; i < testQ.getLength(); i++)
//		{
//			System.out.println(testIteratorOne.next());
//		}
//		testQ.enqueue(4);
//		testQ.enqueue(5);
//		GLIterator<Integer> testIterator = new GLIterator<Integer>(testQ.getHead());
//		System.out.println("testQ with 2 values");
//		for(int i = 0; i < testQ.getLength(); i++)
//		{
//			System.out.println(testIterator.next());
//		}
//		testQ.dequeue();
//		testQ.dequeue();
//		testQ.dequeue(); //shouldn't work bc there's nothing there
//		GLIterator<Integer> testIteratorthr = new GLIterator<Integer>(testQ.getHead());
//		System.out.println("testQ with 2 values");
//		for(int i = 0; i < testQ.getLength(); i++)
//		{
//			System.out.println(testIteratorthr.next());
//		}
	}

}
