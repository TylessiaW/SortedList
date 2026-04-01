public class SortedList<T extends Comparable<? super T>> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
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

            while (current.next != null && data.compareTo(current.next.data) >= 0) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
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

    public int count(T data) {
        int total = 0;
        Node current = head;

        while (current != null) {
            int compare = current.data.compareTo(data);

            if (compare == 0) {
                total++;
            } else if (compare > 0) {
                return total;
            }

            current = current.next;
        }

        return total;
    }

    public int find(T data) {
        Node current = head;
        int index = 0;

        while (current != null) {
            int compare = current.data.compareTo(data);

            if (compare == 0) {
                return index;
            } else if (compare > 0) {
                return -1;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public boolean contains(T data) {
        return find(data) != -1;
    }

    public void removeAll(SortedList<T> otherList) {
        if (otherList == null || otherList.isEmpty() || isEmpty()) {
            return;
        }

        if (this == otherList) {
            clear();
            return;
        }

        Node currentThis = head;
        Node previousThis = null;
        Node currentOther = otherList.head;

        while (currentThis != null && currentOther != null) {
            int compare = currentThis.data.compareTo(currentOther.data);

            if (compare < 0) {
                previousThis = currentThis;
                currentThis = currentThis.next;
            } else if (compare > 0) {
                currentOther = currentOther.next;
            } else {
                T valueToRemove = currentThis.data;

                while (currentThis != null && currentThis.data.compareTo(valueToRemove) == 0) {
                    currentThis = currentThis.next;
                    size--;
                }

                if (previousThis == null) {
                    head = currentThis;
                } else {
                    previousThis.next = currentThis;
                }

                while (currentOther != null && currentOther.data.compareTo(valueToRemove) == 0) {
                    currentOther = currentOther.next;
                }
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        Node current = head;

        while (current != null) {
            result.append(current.data);

            if (current.next != null) {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");
        return result.toString();
    }
}