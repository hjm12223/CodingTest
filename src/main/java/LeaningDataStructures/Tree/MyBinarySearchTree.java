package LeaningDataStructures.Tree;

import java.util.Comparator;

public class MyBinarySearchTree<E> {
	private final Comparator<? super E> comparator;
	private Node<E> root;
	private int size;

	public MyBinarySearchTree() {
		this(null);
	}

	public MyBinarySearchTree(Comparator<? super E> comparator) {
		this.comparator = comparator;
		this.root = null;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Object value) {
		if (comparator == null) {
			return containsUsingComparable(value);
		} else {
			return containsUsingComparable(value, comparator);
		}
	}

	/*
	전위 순회
	부모 -> 왼쪽 -> 오른쪽
	 */

	public void preOrder() {
		preOrder(this.root);
	}

	public void inOrder() {
		inOrder(this.root);
	}

	public void postOrder() {
		postOrder(this.root);
	}

	private void postOrder(Node<E> o) {
		if (o != null) {
			postOrder(o.left);
			postOrder(o.right);
			System.out.print(o.value + " ");
		}
	}

	private void inOrder(Node<E> o) {
		if (o != null) {
			inOrder(o.left);
			System.out.print(o.value + " ");
			inOrder(o.right);
		}
	}

	private void preOrder(Node<E> o) {
		if (o != null) {
			System.out.print(o.value + " ");
			preOrder(o.left);
			preOrder(o.right);
		}
	}

	public void clear() {
		size = 0;
		root = null;
	}

	@SuppressWarnings("unchecked")
	private boolean containsUsingComparable(Object o, Comparator<? super E> comp) {
		E value = (E)o;
		Node<E> node = root;

		while (node != null) {
			int compValue = comp.compare(value, node.value);
			if (compValue < 0) {
				node = node.left;
			} else if (compValue > 0) {
				node = node.right;
			} else {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean containsUsingComparable(Object o) {
		Comparable<? super E> value = (Comparable<? super E>)o;
		Node<E> node = root;
		while (node != null) {
			int res = value.compareTo(node.value);
			if (res < 0) {
				node = node.left;
			} else if (res > 0) {
				node = node.right;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean add(E value) {
		Node<E> newNode = new Node<>(value);

		if (comparator == null) {
			return addUsingComparable(value) == null;
		}
		return addUsingComparable(value, comparator) == null;
	}

	/**
	 * 삭제되는 노드의 자리를 대체할 노드(후계자)를 찾는 메서드
	 * (오른쪽 자식 노드중에서 가장 작은 노드를 찾음)
	 *
	 * @param - node 삭제되는 노드
	 * @return - 대체할 노드
	 */
	private Node<E> getSuccessorAndUnlink(Node<E> node) {
		Node<E> currentParent = node; // 대체 할 노드의 부모 노드를 가리키는 노드
		Node<E> current = node.right; // 초기에 오른쪽 자식 노드를 가리키도록 함

		if (current.left == null) {
			currentParent.right = current.right;
			if (currentParent.right != null) {
				currentParent.right.parent = currentParent;
			}
			current.right = null;
			return current;
		}
		while (current.left != null) {
			currentParent = current;
			current = current.left;
		}

		currentParent.left = current.right;
		if (currentParent.left != null) {
			currentParent.left.parent = currentParent;
		}

		current.right = null;
		return current;
	}

	/**
	 * 삭제할 노드에 대해 삭제를 수행하는 메서드
	 *
	 * @param node- 삭제할 노드
	 * @return - 삭제 후 대체 되고 난 뒤에 해당 위치의 노드를 반환
	 */
	private Node<E> deleteNode(Node<E> node) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				if (node == root) {
					root = null;
				} else {
					node = null;
				}
				return null;
			}
			if (node.left != null && node.right != null) {
				Node<E> replaceNode = getSuccessorAndUnlink(node);
				node.value = replaceNode.value;
			} else if (node.left != null) {
				/*
				 * 삭제할 노드가 root 일 경우 왼쪽자식 노드를
				 * 삭제할 노드로 옮긴 다음 root 를 대체노드로 가리키도록 변경한다
				 * */
				if (node == root) {
					node = node.left;
					root = node;
					root.parent = null;
				} else {
					node = node.left;
				}
				// 오른쪽만 존재할 경우
			} else {
				/*
				 * 삭제할 노드가 root 일 경우 오른쪽 자식 노드를 삭제할 노드로 옮긴 다음
				 *  root 를 대체노드를 가리키도록 변경한다
				 * */
				if (node == root) {
					node = node.right;
					root = node;
					root.parent = null;
				} else {
					node = node.right;
				}
			}
		}
		return null;
	}

	public E remove(Object o) {
		if (root == null) {
			return null;
		}
		if (comparator == null) {
			return removeUsingComparable(o);
		} else {
			return removeUsingComparable(o, comparator);
		}
	}

	@SuppressWarnings("unchecked")
	private E removeUsingComparable(Object value) {
		E oldVal = (E)value;
		Node<E> parent = null, current = root;
		// 삭제하고자 하는 노드가 부모 노드로부터 왼쪽 자식 노드인지 오른쪽 자식노드인지 체크하는 플래그
		boolean hasLeft = false;

		if (root == null) {
			return null;
		}
		Comparable<? super E> compValue = (Comparable<? super E>)value;

		//삭제할 노드를 찾는다
		do {
			int resComp = compValue.compareTo(current.value);
			if (resComp == 0) {
				break;
			}

			parent = current;
			if (resComp < 0) {
				hasLeft = true;
				current = current.left;
			} else {
				hasLeft = false;
				current = current.right;
			}
		} while (current != null);

		// 만약 탐색 끝에 삭제해야할 노드를 찾지 못했다면 null 반환
		if (current == null) {
			return null;
		}
		// 부모 노드가 없을 경우 == 삭제하는 노드가 root 일 경우
		if (parent == null) {
			deleteNode(current);
			size--;
			return oldVal;
		}

		// 삭제 노드가 부모노드의 왼쪽 자식일 경우
		if (hasLeft) {
			parent.left = deleteNode(current);
			if (parent.left != null) {
				parent.left.parent = parent;
			}
		} else {
			parent.right = deleteNode(current);
			if (parent.right != null) {
				parent.right.parent = parent;
			}
		}
		size--;
		return oldVal;
	}

	@SuppressWarnings("unchecked")
	private E removeUsingComparable(Object value, Comparator<? super E> comp) {
		E oldVal = (E)value;
		Node<E> parent = null, current = root;
		boolean hasLeft = false;

		if (root == null) {
			return null;
		}
		E compValue = (E)value;
		do {
			int resComp = comp.compare(current.value, compValue);
			if (resComp == 0) {
				break;
			}
			parent = current;
			if (resComp < 0) {
				hasLeft = true;
				current = current.left;
			} else {
				hasLeft = false;
				current = current.right;
			}
		} while (current != null);

		if (current == null) {
			return null;
		}
		if (parent == null) {
			deleteNode(current);
			size--;
			return oldVal;
		}

		if (hasLeft) {
			parent.left = deleteNode(current);
			if (parent.left != null) {
				parent.left.parent = parent;
			} else if (parent.right != null) {
				parent.right.parent = parent;
			}
		}
		size--;
		return oldVal;
	}

	@SuppressWarnings("unchecked")
	private E addUsingComparable(E value, Comparator<? super E> comp) {
		Node<E> current = root;
		if (root == null) {
			root = new Node<>(value);
			size++;
			return null;
		}
		Node<E> currentParent;
		int compResult;
		do {
			currentParent = current;
			compResult = comp.compare(value, current.value);
			if (compResult < 0) {
				current = current.left;
			} else if (compResult > 0) {
				current = current.right;
			} else {
				return value;
			}
		} while (current != null);

		Node<E> newNode = new Node<E>(value, currentParent);
		if (compResult < 0) {
			currentParent.left = newNode;
		} else {
			currentParent.right = newNode;
		}

		size++;
		return null;
	}

	@SuppressWarnings("unchecked")
	private E addUsingComparable(E value) {
		Node<E> current = root;

		if (current == null) {
			root = new Node<>(value);
			size++;
			return null;
		}
		Node<E> currentParent;

		Comparable<? super E> compValue = (Comparable<? super E>)value;

		int compResult;

		do {
			currentParent = current;
			compResult = compValue.compareTo(current.value);

			if (compResult < 0) {
				current = current.left;
			} else if (compResult > 0) {
				current = current.right;
			} else {
				return value;
			}
		} while (current != null);

		Node<E> newNode = new Node<>(value, currentParent);
		if (compResult < 0) {
			currentParent.left = newNode;
		} else {
			currentParent.right = newNode;
		}
		size++;
		return null;
	}

	public static class Node<E> {
		E value;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E value) {
			this(value, null);
		}

		public Node(E value, Node<E> parent) {
			this.value = value;
			this.parent = parent;
			this.left = null;
			this.right = null;
		}
	}
}
