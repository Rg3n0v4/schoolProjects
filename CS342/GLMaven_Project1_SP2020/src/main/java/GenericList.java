import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<I> implements CreateIterator, Iterable<I>
{
    private Node<I> head; //head of the list
    private int length; //the size of the list

    //constructor from CreateIterator
    public GLIterator<I> createIterator()
    {
        return new GLIterator(head);
    }

    public Iterator<I> iterator()
    {
        return new GLIterator(head);
    }

    //constructor
    GenericList(I data)
    {
        head = new Node(data); //creates the head of the list
        length++;
    }

    //checks if the list is empty and if it is it will print out a message
    //else it will just print out the list
    public void print()
    {
        if(length == 0) //if the list is empty then it should just print out "Empty List"
        {
            System.out.println("Empty List");
        }
        else
        {
            Node<I> temp = head;//temporary variable so that the head of the list isn't lost
            while(temp != null) //while the list isn't at the end
            {
                System.out.println(temp.data); //prints out the list
                temp = temp.next; //so that the pointer goes to the next Node on the list
            }
        }
    }

    //adds value to the list
    abstract void add(I data); //this is needed across the GenericQueue and GenericStack

    //returns first value in the list and then deletes the node
    //if the list is empty then return null
    public I delete()
    {
        //if the list is length isn't empty
        if(length > 0)
        {
            I val = getHead().data; //for returning later
            Node<I> newHead = getHead().next; //preps for new head
            setLength(getLength()-1); //decrements length
            setHead(newHead); //sets the new head of the list
            return val;
        }
        //the list is empty, then return null
        return null;
    }

    //stores and returns all values currently in the list into ArrayList and returns it
    //at the end of this method, list should be empty
    public ArrayList<I> dumpList()
    {
        ArrayList<I> newList = new ArrayList<I>();
        Node<I> temp = getHead();//makes sure that the head of the list isn't lost

        //so that it goes all the way to the end of the list
        while(temp != null)
        {
            newList.add(delete()); //adds the data into the new ArrayList
            temp = temp.next; //so that the iteration goes through
        }

        return newList; //returns a new list
    }

    //getters and setters for length and head
    public int getLength()
    {
        return length;
    }
    public void setLength(int lengthSet)
    {
        length = lengthSet;
    }
    public Node<I> getHead()
    {
        return head;
    }
    public void setHead(Node<I> headSet)
    {
        head = headSet;
    }

    //Node Object class
    class Node<T>
    {
        T data;
        Node<T> next;

        Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }
}