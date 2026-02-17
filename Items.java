import java.util.Iterator;

class SLinkedList<T> implements Iterable<T> {
    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) { head = newNode; return; }
        Node<T> temp = head;
        while (temp.next != null) { temp = temp.next; }
        temp.next = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            public boolean hasNext() { return current != null; }
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SLinkedList)) return false;
        SLinkedList<?> other = (SLinkedList<?>) obj;
        Node<T> curr1 = this.head;
        Node<?> curr2 = other.head;
        while (curr1 != null && curr2 != null) {
            if (!curr1.data.equals(curr2.data)) return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1 == null && curr2 == null;
    }
}

public class Main {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
        list.addFirst(10);
        list.addFirst(20);
        list.addLast(5);

        System.out.println("Items in list:");
        for (Integer i : list) {
            System.out.println(i);
        }

        SLinkedList<Integer> l2 = new SLinkedList<>();
        l2.addFirst(10);
        l2.addFirst(20);
        l2.addLast(5);

        if (list.equals(l2)) {
            System.out.println("Hoorah");
        } else {
            System.out.println("Not equal");
        }
    }
}
