public class SortedList<T extends Comparable<? super T>> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;
    private int size;

    public SortedList() {
        clear();
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T data) {
        Node newNode = new Node(data);

        if (head == null || data.compareTo(head.data) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null &&
                   data.compareTo(current.next.data) >= 0) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    public T get(int index) {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public T removeAt(int index) {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removedData = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removedData;
    }

    public boolean contains(T data) {
        return find(data) != -1;
    }

    public int find(T data) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data.compareTo(data) == 0) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public int count(T data) {
        int total = 0;
        Node current = head;

        while (current != null) {
            if (current.data.compareTo(data) == 0) {
                total++;
            }

            current = current.next;
        }

        return total;
    }

    public void removeAll(SortedList<T> otherList) {
        if (otherList == null || otherList.isEmpty() || isEmpty()) {
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (otherList.contains(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
            } else {
                previous = current;
            }

            current = current.next;
        }
    }

    public String toString() {
        String result = "[";
        Node current = head;

        while (current != null) {
            result += current.data;

            if (current.next != null) {
                result += ", ";
            }

            current = current.next;
        }

        result += "]";
        return result;
    }
}