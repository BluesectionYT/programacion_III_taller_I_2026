package co.edu.uptc.structures;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SimpleList<E> implements Collection<E>, List<E> {
	private Node<E> head;

	public SimpleList() {
		head = null;
	}

	@Override
	public int size() {
		Node<E> current = head;
		int count = 0;
		while (current != null) {
			count += 1;
			current = current.getNext();
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(Object o) {
		Node<E> aux = head;
		while (aux != null) {
			if (o == null ? aux.getValue() == null : o.equals(aux.getValue())) {
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node<E> actual = head;
			@Override
			public boolean hasNext() {
				return actual != null;
			}
			@Override
			public E next() {
				if (actual == null) {
					throw new NoSuchElementException();
				}
				E value = actual.getValue();
				actual = actual.getNext();
				return value;
			}
		};
	}

	@Override
	public Object[] toArray() {
		int size = size();
		Object[] array = new Object[ size ];
		Node aux = head;
		for (int i=0; i< size; i++){
			array[i] = aux.getValue();
			aux = aux.getNext();
		}

		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		int size = size(); 
		if (a.length < size) {
			a = (T[]) Array.newInstance(
        	a.getClass().getComponentType(), size);
		}

		int i = 0;
		for (Object element : this) { 
			a[i++] = (T) element;
		}

		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<E>(e);
		if (head == null) {
			head = newNode;
		} else {
			Node<E> actual = head;
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(newNode);
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (isEmpty()) return false;
		if (o == null) throw new NullPointerException();
		if (o.getClass() != head.getValue().getClass()) throw new ClassCastException();
		
		if (head.getValue().equals(o)) {
			head = head.getNext();
			return true;
		}
		
		Node<E> prev = head;
		Node<E> next = head.getNext();

		while (next != null) {
			if (next.getValue().equals(o)) {
				prev.setNext(next.getNext());
				return true;
			}

			next = next.getNext();
			prev = prev.getNext();
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException{
		for (Object object : c) {
			if(!contains(object)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		for (E element : c) {
			if (add(element)) {
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean isAdded = false;

        if (index >= 0 && index <= this.size()) { 
            if (!c.isEmpty()) {
                Node<E> predecessor = null;
                Node<E> successor = this.head;
                for (int i = 0; i < index; i++) {
                    predecessor = successor;
                    successor = successor.getNext();
                }
                Node<E> current = predecessor;
                for (E element : c) {
                    Node<E> newNode = new Node<>(element);
                    if (current == null) {
                        this.head = newNode;
                    } else {
                        current.setNext(newNode);
                    }
                    current = newNode; 
                }
                if (current != null) {
                    current.setNext(successor);
                }
                isAdded = true;
            }
        }
        return isAdded;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		Node<E> actualNode = this.head;
		while (actualNode != null) {
			Node<E> nextNode = actualNode.getNext();
			Node<E> temporalNode = actualNode;
			if (c.stream().filter(e -> e.equals(temporalNode.getValue())).findFirst().orElse(null) != null) {
				this.remove(actualNode.getValue());
				result = true;
			}
			actualNode = nextNode;
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		boolean result = false;
		Node<E> current = head;
		Node<E> previous = null;

		while (current != null) {

			if (c.contains(current.getValue())) {

				previous = current;
				current = current.getNext();

			} else {
				result = true;
			}
			if (previous == null) {
				head = current.getNext();
			}else{ 
				previous.setNext(current.getNext());
			}

			current =current.getNext();

		}

		return result;
	}

	@Override
	public void clear() {
		head = null;
	}

	@Override
	public E get(int index) {
		if (index < 0 || head == null) {
			return null;
		}

		Node currentNode = head;
		int currentIndex = 0;

		while (currentNode != null) {
			if (currentIndex == index) {
				return (E) currentNode.getValue();
			}
			else {
				currentIndex ++;
				currentNode = currentNode.getNext();
			}
		}
		
		return null;
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public void add(int index, E element) {
		Node<E> newNode = new Node<>(element);

		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
			return;
		}
		
		Node<E> aux = head;
		for (int i = 0; i < index - 1; i++) {
			aux = aux.getNext();
		}

		newNode.setNext(aux.getNext());
		aux.setNext(newNode);
	}

	@Override
	public E remove(int index) {
		int count=0;
		Node<E> aux = head;

		while (aux != null) {
			count++;
			aux = aux.getNext();
		}

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("El indice esta fuera del rango: " + index);
		}

		if (index == 0) {
			E removed = head.getValue();
			head = head.getNext();
			return removed;
		}

		Node<E> previous = head;

		for (int i = 0; i < index - 1; i++) {
			previous = previous.getNext();
		}

		Node<E> current = previous.getNext();
		E removed = current.getValue();
		previous.setNext(current.getNext());
		return removed;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> aux = head;
		int counter = 0;
		while(aux != null){
			if(aux.getValue().equals(o)){
				return counter;
			}
			aux = aux.getNext();
			counter++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Node<E> actual = head;	
		int i = -1;
		int j = 0;
		while(actual != null){
			if(actual.getValue().equals(o)){
				i = j;
			}
			j++;
			actual = actual.getNext();
		}
		return i;
	}


	@Override
	public ListIterator<E> listIterator() {

	    ListIterator<E> iterator = new ListIterator<E>() {

	        Node<E> actual = head;

	        @Override
	        public boolean hasNext() {
	            return actual != null;
	        }

	        @Override
	        public E next() {
	            E element = actual.getValue();
	            actual = actual.getNext();
	            return element;
	        }

	        @Override
	        public boolean hasPrevious() {
	            return false;
	        }

	        @Override
	        public E previous() {
	            return null;
	        }

	        @Override
	        public int nextIndex() {
	            return 0;
	        }

	        @Override
	        public int previousIndex() {
	            return -1;
	        }

	        @Override
	        public void remove() {
	        }

	        @Override
	        public void set(E e) {
	        }

	        @Override
	        public void add(E e) {
	        }
	    };

	    return iterator;
	}



	@Override
	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		return new ListIterator<E>() {
			private int currentIndex = index;
			private Node<E> currentNode = (Node<E>) get(currentIndex);
			private Node<E> lastNode = null;

			@Override
			public boolean hasNext() {
				return currentIndex < size();
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				lastNode = currentNode;
				E data = currentNode.getValue();
				currentNode = currentNode.getNext();
				currentIndex++;
				return data;
			}

			@Override
			public boolean hasPrevious() {
				return currentIndex > 0;
			}

			@Override
			public E previous() {
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				currentIndex--;
				currentNode = (Node<E>) get(currentIndex);
				lastNode = currentNode;
				return currentNode.getValue();
			}

			@Override
			public int nextIndex() {
				return currentIndex;
			}

			@Override
			public int previousIndex() {
				return currentIndex - 1;
			}

			@Override
			public void remove() {
				if (lastNode == null) {
					throw new IllegalStateException();
				}
				Node<E> previous;
				if (lastNode == head) {
					previous = null;
				} else {
					int lastNodeIndex;
					if (currentNode == lastNode) {
						lastNodeIndex = currentIndex;
					} else {
						lastNodeIndex = currentIndex - 1;
					}
					previous = (Node<E>) get(lastNodeIndex - 1);
				}
				if (previous == null) {
					head = lastNode.getNext();
				} else {
					previous.setNext(lastNode.getNext());
				}
				if (currentNode == lastNode) {
					currentNode = lastNode.getNext();
				} else {
					currentIndex--;
				}
				lastNode = null;
			}

			@Override
			public void set(E e) {
				if (lastNode == null) {
					throw new IllegalArgumentException();
				}

				lastNode.setValue(e);

			}

			@Override
			public void add(E e) {
				Node<E> newNode = new Node<E>(e);
				if (currentIndex == 0) {
					newNode.setNext(head);
					head = newNode;
				} else {
					Node<E> previous = (Node<E>) get(currentIndex - 1);
					previous.setNext(newNode);
					newNode.setNext(currentNode);
				}
				currentIndex++;
				lastNode = null;

			}
		};
	}
	
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public String toString() {
		return head.toString() + "\n";
	}

}
