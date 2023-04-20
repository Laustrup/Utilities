package laustrup.utilities.utilities.collections.sets;

import laustrup.bandwichpersistence.utilities.collections.CollectionUtility;

public abstract class SetUtility<E> extends CollectionUtility<E> {

    /**
     * Creates the Utility with empty data and a hash type of map.
     * @param year The year of the Utility.
     * @param version The middle index of version.
     * @param update The update of the version.
     */
    protected SetUtility(int year, int version, int update) {
        super(year, version, update);
    }

    /**
     * Creates the Utility with empty data.
     * @param isLinked Decides if the map should be linked or hash type.
     * @param year The year of the Utility.
     * @param version The middle index of version.
     * @param update The update of the version.
     */
    protected SetUtility(boolean isLinked, int year, int version, int update) {
        super(isLinked, year, version, update);
    }

    public abstract boolean containsAll(CollectionUtility<?> c);

    public abstract boolean addAll(CollectionUtility<? extends E> c);

    public abstract boolean removeAll(CollectionUtility<?> collection);
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
