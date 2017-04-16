package model;

public class LinkedList<T> implements ListInterface<T> {
	private Node<T> firstNode;
	private int numberOfEntries;

	public LinkedList() {
		clear();
	}

	private Node getNodeAt(int givenPosition) {
		// assert (head != null) && (1 <= givenPosition)
		// && (givenPosition <= size);
		// Node<T> current = head;
		// /** Traverse node to locate desired node */
		// for (int i = 1; i < givenPosition; i++)
		// current = current.getNext();
		// assert current != null;
		//
		// return current;
		// assert
		Node currentNode = firstNode;
		if ((firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries)) {

			// traverse the chain to locate the desired node
			for (int counter = 1; counter < givenPosition; counter++)
				currentNode = currentNode.getNext();
			// assert currentNode != null;
			return currentNode;
		}
		return currentNode;
	}

	@Override
	public void add(T newEntry) {
		// Node<T> insert = new Node(newEntry);
		//
		// if (isEmpty())
		// head = insert;
		// else {
		// Node<T> tail = getNodeAt(size);
		// tail.setNext(insert);
		// }
		// size++;
		Node newNode = new Node(newEntry);
		if (isEmpty())
			firstNode = newNode;
		else // add to end of nonempty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNext(newNode); // make last node reference new node
		} // end if
		numberOfEntries++;
	}

	@Override
	public boolean add(int newPosition, T newEntry) {
		// boolean isSuccessful = true;
		//
		// if ((newPosition >= 1) && (newPosition <= size + 1)) {
		// Node<T> insert = new Node(newEntry);
		//
		// if (newPosition == 1) {
		// insert.setNext(head);
		// head = insert;
		// } else {
		// Node nodeBefore = getNodeAt(newPosition - 1);
		// Node nodeAfter = nodeBefore.getNext();
		// insert.setNext(nodeAfter);
		// nodeBefore.setNext(insert);
		// }
		// size++;
		// } else
		// isSuccessful = false;
		//
		// return isSuccessful;
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);
			if (newPosition == 1) // case 1
			{
				newNode.setNext(firstNode);
				firstNode = newNode;
			} else // case 2: list is not empty
			{ // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNext();
				newNode.setNext(nodeAfter);
				nodeBefore.setNext(newNode);
			} // end if
			numberOfEntries++;
		} else
			isSuccessful = false;
		return isSuccessful;
	}

	@Override
	public T remove(int givenPosition) {
		T result = null;

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries) && isEmpty()) {
			// assert !isEmpty();
			if (givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNext();
			} else {
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeToRemove = nodeBefore.getNext();
				Node<T> nodeAfter = nodeToRemove.getNext();
				nodeBefore.setNext(nodeAfter);
				result = nodeToRemove.getData();
			}
			numberOfEntries--;
		}
		return result;
	}

	@Override
	public final void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public boolean replace(int givenPosition, T newEntry) {
		boolean isSucessful = true;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries) && !isEmpty()) {
			// assert !isEmpty();
			Node<T> desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
		} else
			isSucessful = false;

		return isSucessful;
	}

	@Override
	public T getEntry(int givenPosition) {
		T result = null;

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries) && !isEmpty()) {
			// assert !isEmpty();
			result = (T) getNodeAt(givenPosition).getData();
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node current = firstNode;

		while (!found && (current != null)) {
			if (anEntry.equals(current.getData()))
				found = true;
			else
				current = current.getNext();
		}
		return found;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		// boolean result;
		// if (size == 0) {
		// assert head == null;
		// result = true;
		// } else {
		// assert head != null;
		// result = false;
		// }
		//
		// return result;
		boolean result;
		if (numberOfEntries == 0 && firstNode == null) // or getLength() == 0
		{
			// assert firstNode == null;
			result = true;
		} else {
			// assert firstNode != null;
			result = false;
		} // end if
		return result;
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[numberOfEntries];

		int i = 0;
		Node<T> current = firstNode;
		while ((i < numberOfEntries) && (current != null)) {
			result[i] = current.getData();
			current = current.getNext();
			i++;
		}
		return result;
	}
}
