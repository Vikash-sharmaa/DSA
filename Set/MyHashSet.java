package Set;
/*
  HashSet Implementation:
    A HashSet uses an array of linked lists (buckets), where each node contains a key and a reference to the next node.
    The array has an initial capacity of 16.

    When adding a value:
      - It computes the bucket index using a hash function (hashCode() of the key and bit manipulation).
      - If the key is already present in the linked list at that index (using equals()), do nothing.
      - If not found, create a new node and add it at the head of the linked list.

    contains(), remove(), and size() work similarly by traversing the linked list at the computed index.
 */

public class MyHashSet<E> {

    // Node class for each entry in the bucket
    public static class Node<E> {
        E key;
        Node<E> next;

        public Node(E key) {
            this.key = key;
        }
    }

    private static int INITIAL_CAPACITY = 16;
    private int size;
    private Node<E>[] buckets;

    public MyHashSet() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    private int getBucketIndex(E key) {
        return (key == null) ? 0 : (Math.abs(key.hashCode()) % buckets.length);
    }

    // Add element to set
    public void add(E key) {
        int index = getBucketIndex(key);
        Node<E> head = buckets[index];

        // Check for duplicates
        Node<E> current = head;
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return; // key already present
            }
            current = current.next;
        }

        // If not found, create new node and insert at head
        Node<E> newNode = new Node<>(key);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
    }

    // Check if set contains a value
    public boolean contains(E key) {
        int index = getBucketIndex(key);
        Node<E> current = buckets[index];

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return true; 
            }
            current = current.next;
        }
        return false;
    }

    // Remove a value from set
    public boolean remove(E key) {
        int index = getBucketIndex(key);
        Node<E> current = buckets[index];
        Node<E> previous = null;

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // Get current size of set
    public int size() {
        return size;
    }
}

