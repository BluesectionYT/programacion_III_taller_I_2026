package co.edu.uptc.structures;

public class DoubleNode<E> {
	private E value;
	private DoubleNode<E> previous;
	private DoubleNode<E> next;

	public DoubleNode(E value) {
		this.value = value;
		this.previous = null;
		this.next = null;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public DoubleNode<E> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleNode<E> previous) {
		this.previous = previous;
	}

	public DoubleNode<E> getNext() {
		return next;
	}

	public void setNext(DoubleNode<E> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(value.toString());
		if (next != null) {
			result.append(next+"\n");
		}
		return result.toString();
	}

}
