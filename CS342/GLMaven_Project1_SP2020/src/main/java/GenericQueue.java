public class GenericQueue<I> extends GenericList<I>
{
    //constructor for the class
    GenericQueue(I data)
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

        //if the queue is empty
        if(getLength() == 0)
        {
            setHead(newNode);
        }
        //if there are more than one node on the list
        else if(getLength() > 0)
        {
            GenericList<I>.Node<I> temp = getHead();
            while(temp.next != null) //keep iterating through the list until it's almost at the end
            {
                temp = temp.next;
            }
            temp.next = newNode;//appends Node to the end of the list
        }

        //increment the length of the list once it's all said and done
        setLength(getLength()+1);
    }

    //pushes Nodes onto the queue
    public void enqueue(I data)
    {
        add(data);
    }

    //pops Nodes off of the queue
    public I dequeue()
    {
        return delete();
    }
}
