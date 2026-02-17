import java.util.Iterator;
import java.util.NoSuchElementException;

class SLinkedList<T> implements Iterable<T> {
    
    private class Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    public void addFirst(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    public void reverse() {
        if (head == null || head.next == null) return;
        Node previous = null;
        Node current = head;
        Node nextNode = null;
        tail = head; 
        while (current != null) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        head = previous;
    }

    public void deleteConsecutiveDuplicates() {
        if (head == null) return;
        Node current = head;
        while (current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
                if (current.next == null) tail = current;
            } else {
                current = current.next;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (!(obj instanceof SLinkedList)) return false;
        SLinkedList<T> other = (SLinkedList<T>) obj;
        if (this.size != other.size) return false;

        Node curr1 = this.head;
        Node curr2 = (Node) other.head;
        while (curr1 != null) {
            if (!curr1.data.equals(curr2.data)) return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return true;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;
            public boolean hasNext() { return current != null; }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
        list.addFirst(10);
        list.addFirst(20);
        list.addLast(5);

        System.out.println("List 1 Items:");
        for (Integer i : list) {
            System.out.println(i);
        }

        SLinkedList<Integer> l2 = new SLinkedList<>();
        l2.addFirst(10);
        l2.addFirst(20);
        l2.addLast(5);

        System.out.print("Are they equal? ");
        if (list.equals(l2)) {
            System.out.println("Hoorah");
        } else {
            System.out.println("Not equal");
        }
    }
}
