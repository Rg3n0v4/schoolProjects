public class GenericStack<I> extends GenericList<I>
{
    //constructor for the class
    GenericStack(I data)
    {
        super(data);
        //because of the constructor it inherited
        //also to create initialize the list
    }

    @Override
    void add(I data)
    {
        //create a new node for the queue
        GenericList<I>.Node<I> newNode = new GenericList<I>.Node<I>(data);

        if(getLength() == 0)
        {
            setHead(newNode);
        }
        //adds on to the stack
        else if(getLength() > 0)
        {
            newNode.next = getHead(); //moves the original head to be after the new node in place
            setHead(newNode); //creates the new head of the list
        }
        setLength(getLength() + 1); //increments the length of the stack
    }

    //pushes data onto the stack
    public void push(I data)
    {
        add(data);
    }

    //pops data off the stack
    public I pop()
    {
        return delete();
    }

}
