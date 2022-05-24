package BinaryTree;

import java.util.LinkedList;

/**
 * 二叉树层序遍历
 *
 * @author weichenglin
 * @since 2022-05-24-上午 09:47:51
 */
public class LevelTraversalBT {

	public static void level(Node head) {
		if (head == null) {
			return;
		}
		LinkedList<Node> list = new LinkedList<>();
		list.add(head);
		while (!list.isEmpty()) {
			Node poll = list.poll();
			System.out.println(poll.value);
			if (poll.left != null) {
				list.add(poll.left);
			}
			if (poll.right != null) {
				list.add(poll.right);
			}
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		level(head);
	}
}
