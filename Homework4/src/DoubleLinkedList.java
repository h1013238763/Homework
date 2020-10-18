import java.util.*;

public class DoubleLinkedList<E> implements List<E> {

    private int size;       // size of the list
    private Node<E> head;   // head node of the list
    private Node<E> tail;   // tail node of the list

    public DoubleLinkedList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @Override
     * @return output
     * */
    public Iterator iterator() {
        return new InnerListIterator(0);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     *
     * @Override
     * */
    public ListIterator listIterator() {
        return new InnerListIterator(0);
    }

    @Override
    public ListIterator listIterator(int i) {
        return new InnerListIterator(i);
    }

    /**
     * Appends the specified element to the end of this list (optional operation).
     *
     * @Override
     * */
    public boolean add(E element) {
        InnerListIterator iterator = new InnerListIterator(size);
        iterator.add(element);
        return true;
    }

    @Override
    public void add(int i, E e) {
        InnerListIterator iterator = new InnerListIterator(i);
        iterator.add(e);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean equals(DoubleLinkedList o){
        if(this.size != o.size)
            return false;
        for(int i = 0; i < size; i ++) {
            if(this.get(i) != o.get(i))
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        InnerListIterator iterator = new InnerListIterator();
        while(iterator.hasNext()){
            if(iterator.currentNode.data == o)
                return true;
            iterator.next();
        }
        return false;
    }

    @Override
    public E get(int i) {
        InnerListIterator iterator = new InnerListIterator(i);
        return iterator.currentNode.data;
    }

    @Override
    public int indexOf(Object o) {
        InnerListIterator iterator = new InnerListIterator();
        while(iterator.hasNext()){
            if(iterator.currentNode.data == o)
                return iterator.index;
            iterator.next();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        InnerListIterator iterator = new InnerListIterator();
        while(iterator.hasNext()){
            if(iterator.currentNode.data == o)
                result = iterator.index;
            iterator.next();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        if(head == null && tail == null && size == 0)
            return true;
        else
            return false;
    }

    @Override
    public E remove(int i) {
        E result;
        if(size == 1) {
            result = head.data;
            clear();
        }
        else if(i == size) {
            result = tail.data;
            tail.prev.next = null;
        }
        else{
            InnerListIterator iterator = new InnerListIterator(i+1);
            result = iterator.lastReturn.data;
            iterator.remove();
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public E set(int i, E e) {
        if(size == 1) {
            head.data = e;
        }
        else if(i == size) {
            tail.data = e;
        }
        else{
            InnerListIterator iterator = new InnerListIterator(i+1);
            iterator.set(e);
        }
        return e;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Output the list as String in order
     *
     * @return output - elements in each node
     * */
    public String toString() {
        InnerListIterator iterator = new InnerListIterator();
        String output = "[ ";
        while(iterator.hasNext()){
            output += iterator.next() +", ";
        }
        output += "]";
        return output;
    }

    /** Inner Classes*/
    private static class Node<E>{
        E data;
        Node<E> prev;
        Node<E> next;

        /**
         * Set a new node with null data and no connect with other node
         * */
        public Node(){
            this.data = null;
            this.prev = null;
            this.next = null;
        }

        /**
         * Set a new node with data as data and no connect with other node
         *
         * @param data - set as the data of the node
         * */
        public Node(E data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        /**
         * Set a new node with data as data and next as next node, prev as previous node
         *
         * @param data - set as the data of the node
         * @param prev - set as the previous node of the node
         * @param next - set as the next node of the node
         * */
        public Node(E data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public class InnerListIterator implements ListIterator<E>{

        private Node<E> currentNode;
        private Node<E> lastReturn;
        private int index = 0;

        public InnerListIterator(){
            lastReturn = null;
            currentNode = head;
            index = 0;
        }

        public InnerListIterator(int index){
            if(index < 0 || index > size)
                throw new IndexOutOfBoundsException();
            lastReturn = null;
            this.index = index;
            currentNode = head;

            for(int i = 0; i < index; i ++){
                next();
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            lastReturn = currentNode;
            currentNode = currentNode.next;
            index ++;
            return lastReturn.data;
        }

        @Override
        public boolean hasPrevious() {
            return currentNode.prev != null;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            lastReturn = currentNode;
            currentNode = currentNode.prev;
            index --;
            return lastReturn.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index-1;
        }

        @Override
        public void remove() {
            if(size == 1){
                head = null;
                tail = null;
            }
            else if(lastReturn.prev == null){
                head = lastReturn.next;
                lastReturn.next.prev = null;
                lastReturn = null;
            }
            else if(lastReturn.next == null){
                tail = lastReturn.prev;
                lastReturn.prev.next = null;
                lastReturn = null;
            }
            else{
                lastReturn.next.prev = lastReturn.prev;
                lastReturn.prev.next = lastReturn.next;
                lastReturn = null;
            }
            size --;
        }

        @Override
        public void set(E e) {
            if(lastReturn == null)
                throw new NoSuchElementException();
            lastReturn.data = e;
        }

        @Override
        public void add(E e) {
            if(head == null) {
                head = new Node<>(e);
                tail = head;
            }
            else if(currentNode == null){
                tail.next = new Node<>(e,tail,null);
                tail = tail.next;
            }
            else{
                Node<E> newNode = new Node<>(e,currentNode.prev,currentNode);
                currentNode.prev.next = newNode;
                currentNode.prev = newNode;
            }
            size ++;
            index ++;
        }
    }

    /** Don't need */
    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    @Override
    public List subList(int i, int i1) {
        return null;
    }
}
