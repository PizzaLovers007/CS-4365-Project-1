import java.util.Collection;

/**
 * Created by Terrence Park and Skye Pekerti
 */
public abstract class SearchQueue {

    Collection data;

    public abstract Node getNext();

    public abstract boolean goalTest();
}
