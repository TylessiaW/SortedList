public class SortedList<T extends Comparable<T>> {

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
		this.head = null;
		this.size = 0;
	}

	public void add(T element) {
		Node newNode = new Node(element);

		if (head == null || element.compareTo(head.data) < 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node current = head;

			while (current.next !=null &&
				element.compareTo(current.next.data) > 0) {
					current = current.next;
				}

			newNode.next = current.next;
			current.next = newNode;
		}

		size++;
	}

	public boolean remove(T element) {
		if (head == null) {
			return false;
		}

		if (head.data.equals(element)) {
			head = head.next;
			size--;
			return true;
		}

		Node current = head;

		while (current.next != null &&
			!current.next.data.equals(element)) {
				current = current.next;
	}

	if (current.next == null) {
		return false;
	}

	current.next = current.next.next;
	size--;
	return true;
	}

	public T get(int index) {
		if (index <0 || index >= size) {
			throw new IndexOutBoundsException();
		}

		Node current = head;

		for (int i = 0;  i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void printlist() {
		Node current = head;

		while(current != null) {
			System.out.print(current.data + " ->");
			current = current.next;
		}

		System.out.println("null");
	}

}