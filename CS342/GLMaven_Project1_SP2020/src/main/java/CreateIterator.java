import java.util.Iterator;

//for creating an iterator interface
public interface CreateIterator<I>{
    public abstract Iterator<I> createIterator();
}
