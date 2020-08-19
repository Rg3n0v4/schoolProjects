import java.util.Iterator;

public class GLIterator<I> implements Iterator<I>
{
    GenericList<I>.Node<I> head; //for getting the head of the list

    //constructor
    GLIterator(GenericList<I>.Node<I> data)
    {
        head = data; //to establish the head of the list
    }

    //checks to see if there is another value in the list
    public boolean hasNext()
    {
        //if there isn't anything after the head then return false
        if(head == null)
        {
            return false;
        }
        else//there is something after head then return true
        {
            return true;
        }
    }

    //returns the current value
    public I next()
    {
        I currentVal = head.data;
        head = head.next; //moves onto the next
        return currentVal;
    }

}
