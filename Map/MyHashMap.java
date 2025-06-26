package Map;

/*
  HashMap Implementation:
    A HashMap uses an array of linked lists (buckets), where each node contains a key, value, and a reference to the next node.
    The array has an initial capacity of 16.

    When inserting a key-value pair:

    It computes the bucket index using a hash function (hashCode() of the key and bit manipulation).
    If the index is empty, it creates a new node and stores it there.
    If the index is occupied (collision), it traverses the linked list at that index:
    If the key is found (using equals()), it updates the value.
    If not found, it creates a new node and adds it at the head of the list (Java 7 and earlier) or appends it to the chain (Java 8+ may convert to a balanced tree if chain length exceeds 8).
    Load factor is monitored, and when the threshold is breached, resize() doubles the capacity and rehashes all existing entries.

 */

public class MyHashMap<K,V>{


    public static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
        }
    }

    private static int INITIAL_CAPACITY = 16;
    private int size;
    private Node<K,V> [] buckets;


    public MyHashMap(){
        buckets = new Node[INITIAL_CAPACITY];
        size=0;
    }

    private int getBucketIndex(K key){
        return (key==null) ? 0 : (key.hashCode() % buckets.length);
    }

    public void put(K key,V value){
        int index = getBucketIndex(key);

        Node<K,V> head = buckets[index];

        Node<K,V> current=head;

        while(current!=null){
            if((key==null && current.key==null) || (key!=null && key.equals(current.key))){
                current.value=value;
                return;
            }
            current=current.next;
        }

        Node<K,V> newNode = new Node<>(key, value);

        newNode.next=head;
        buckets[index]=newNode;
        size++;
    }

    public V get(K key){
        int index = getBucketIndex(key);

        Node<K,V> current=buckets[index];

        while(current!=null){
            if((key==null && current.key==null) || (key!=null && key.equals(current.key))){
                return current.value;
            }
            current=current.next;
        }

        return null;
    }

    public boolean containsKey(K key){
        return get(key)!=null;
    }


    public V remove(K key){
        int index=getBucketIndex(key);

        Node<K,V> head=buckets[index];
        Node<K,V> current=head;
        Node<K,V> previous=null;

        while(current!=null){
            if((key==null && current.key==null) || (key!=null && key.equals(current.key))){
                if(previous==null){
                    buckets[index]=current.next;
                }else{
                    previous.next=current.next;
                }
                size--;
                return current.value;
            }
            previous=current;
            current=current.next;
        }

        return null;
    }

    public int size(){
        return size;
    }

}


