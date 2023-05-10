package laustrup.utilities.collections.sets;

import laustrup.utilities.Utility;
import laustrup.utilities.collections.Collection;

public abstract class SetUtility<E> extends Collection<E> {

    /**
     * Creates the Utility with empty data.
     * @param isLinked Decides if the map should be linked or hash type.
     */
    protected SetUtility(boolean isLinked) { super(isLinked); }


    public abstract boolean remove(int index);

    public abstract boolean Remove(int index);
    public abstract int indexOf(E element) throws ClassNotFoundException;
    public abstract E get(int index);
    public abstract E get(E element);
    public abstract E Get(int index);
    public abstract E get(String key);
    public abstract E set(int index, E element);
    public abstract E[] retain(E[] elements);
}
