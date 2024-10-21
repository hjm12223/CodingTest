package LeaningDataStructures.Tree;

public class MyBinarySearchTreeTest {
	public static void main(String[] args) {
		MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
		tree.add(23);
		tree.add(12);
		tree.add(40);
		tree.add(7);
		tree.add(16);
		tree.add(1);
		tree.add(14);
		tree.add(17);
		tree.add(29);
		tree.add(55);
		tree.add(61);

		System.out.println("전위");
		tree.preOrder();
		System.out.println();

		System.out.println("중위");
		tree.inOrder();
		System.out.println();

		System.out.println(Integer.toString(1024, 2));
		System.out.println("후위");
		tree.postOrder();
		System.out.println();

	}
}
