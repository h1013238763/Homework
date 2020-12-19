import java.util.*;

public class HashTableChain<K, V> extends HashMap<K, V> {

    private LinkedList<Entry<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;

    public HashTableChain(){
        table = new LinkedList[CAPACITY];
        numKeys = 0;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        return (numKeys == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        if(get(key) == null)
            return false;
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < table.length; i ++){
            for(Entry<K, V> nextItem: table[i]){
                if(nextItem.value.equals(value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index += table.length;
        if(table[index] == null)
            return null;
        for(Entry<K, V> nextItem: table[index]){
            if(nextItem.key.equals(key))
                return nextItem.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if(table[index] == null)
            table[index] = new LinkedList<Entry<K, V>>();

        for(Entry<K, V> nextItem : table[index]) {
            if(nextItem.key.equals(key)){
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }

        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys ++;
        if(numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0){ index += table.length; }
        if(table[index] == null){ return null; }
        for( Entry<K, V> nextItem : table[index]){
            if (nextItem.key.equals(key)) {
                V value = nextItem.getValue();
                nextItem.key = null;
                return value;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        table = new LinkedList[CAPACITY];
        numKeys = 0;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    private void rehash(){
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2 + 1];

        numKeys = 0;
        for(int i = 0; i < oldTable.length; i ++){
            if(oldTable[i] != null){
                for( Entry<K, V> nextItem : oldTable[i]){
                    put(nextItem.key, nextItem.value);
                }
            }
        }
    }

    /** Inner Class */
    public class Entry<K, V>{

        private K key;
        private V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        /** Getter */
        public K getKey(){ return key;}

        public V getValue(){ return value;}

        /** Setter*/
        public void setValue(V value){ this.value = value;}
    }

    private class SetIterator implements Iterator<Map.Entry<K, V>> {

        int index = 0;
        Iterator<Entry<K, V>> iterator = null;

        @Override
        public boolean hasNext() {
            if (iterator != null) {
                if (iterator.hasNext()) {
                    return true;
                } else {
                    iterator = null;
                    index++;
                }
            }
            while (index < table.length
                    && table[index] == null) {
                index++;
            }
            if (index == table.length) {
                return false;
            }
            iterator = table[index].iterator();
            return iterator.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (Map.Entry<K, V>) iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
            if (table[index].size() == 0) {
                table[index] = null;
            }
            numKeys--;
        }
    }


    /** don't need implement*/
    @Override
    public void putAll(Map map) {

    }
    @Override
    public Collection values() {
        return null;
    }
}
