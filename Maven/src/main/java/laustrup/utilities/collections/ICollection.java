package laustrup.utilities.collections;

//TODO update documentation
public interface ICollection<E> {

    /**
     * Ensures that this collection contains the specified element (optional operation).
     * Returns true if this collection changed as a result of the call.
     * (Returns false if this collection does not permit duplicates and already contains the specified element).
     * Collections that support this operation may place limitations on what elements may be added to this collection.
     * In particular, some collections will refuse to add null elements,
     * and others will impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
     * If a collection refuses to add a particular element for any reason other than that it already contains the element,
     * it must throw an exception (rather than returning false).
     * This preserves the invariant that a collection always contains the specified element after this call returns.
     * @param elements Elements whose presence in this collection is to be ensured.
     * @return true if this collection changed as a result of the call
     */
    boolean add(E[] elements);

    boolean contains(E[] elements);

    E getFirst();
    E getLast();
}
