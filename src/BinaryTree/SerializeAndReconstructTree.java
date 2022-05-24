package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化，还原树
 *
 * @author weichenglin
 * @since 2022-05-24-上午 10:12:43
 */
public class SerializeAndReconstructTree {

	/**
	 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
	 * 以下代码全部实现了。
	 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
	 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
	 * 比如如下两棵树
	 * __2
	 * /
	 * 1
	 * 和
	 * 1__
	 * \
	 * 2
	 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
	 */

	private static Queue<Integer> preSerial(Node head) {
		Queue<Integer> queue = new LinkedList<>();
		if (head == null) {
			return queue;
		}
		preSerialProcess(head, queue);
		return queue;
	}

	private static void preSerialProcess(Node head, Queue<Integer> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			queue.add(head.value);
			preSerialProcess(head.left, queue);
			preSerialProcess(head.right, queue);
		}
	}

	private static Node buildPreTreeByQueue(Queue<Integer> queue) {
		if (queue.isEmpty()) {
			return null;
		}
		return buildPreTreeProcess(queue);
	}

	private static Node buildPreTreeProcess(Queue<Integer> queue) {
		Integer poll = queue.poll();
		if (poll == null) {
			return null;
		}
		Node node = new Node(poll);
		node.left = buildPreTreeProcess(queue);
		node.right = buildPreTreeProcess(queue);
		return node;
	}

	private static Queue<Integer> posSerial(Node head) {
		Queue<Integer> queue = new LinkedList<>();
		if (head == null) {
			return queue;
		}
		posSerialProcess(head, queue);
		return queue;
	}

	private static void posSerialProcess(Node head, Queue<Integer> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			posSerialProcess(head.left, queue);
			posSerialProcess(head.right, queue);
			queue.add(head.value);
		}
	}

	private static Node buildPosTreeByQueue(Queue<Integer> queue) {
		if (queue.isEmpty()) {
			return null;
		}
		// 左右中-》中右左
		Stack<Integer> stack = new Stack<>();
		while (!queue.isEmpty()) {
			stack.push(queue.poll());
		}
		return buildPosTreeProcess(stack);
	}

	private static Node buildPosTreeProcess(Stack<Integer> stack) {
		Integer poll = stack.pop();
		if (poll == null) {
			return null;
		}
		Node node = new Node(poll);
		node.right = buildPosTreeProcess(stack);
		node.left = buildPosTreeProcess(stack);
		return node;
	}

	private static Queue<Integer> levelSerial(Node head) {
		Queue<Integer> result = new LinkedList<>();
		if (head == null) {
			return result;
		} else {
			result.add(head.value);
			Queue<Node> temp = new LinkedList<>();
			temp.add(head);
			Node pollNode = null;
			while (!temp.isEmpty()) {
				pollNode = temp.poll();
				if (pollNode.left != null) {
					temp.add(pollNode.left);
					result.add(pollNode.left.value);
				} else {
					result.add(null);
				}
				if (pollNode.right != null) {
					temp.add(pollNode.right);
					result.add(pollNode.right.value);
				} else {
					result.add(null);
				}
			}
		}
		return result;
	}

	private static Node buildLevelTreeByQueue(Queue<Integer> queue) {
		if (queue.isEmpty()) {
			return null;
		}
		Node head = generateNode(queue.poll());
		Queue<Node> temp = new LinkedList<>();
		if (head != null) {
			temp.add(head);
		}
		Node node = null;
		while (!temp.isEmpty()) {
			node = temp.poll();
			node.left = generateNode(queue.poll());
			node.right = generateNode(queue.poll());
			if (node.left != null) {
				temp.add(node.left);
			}
			if (node.right != null) {
				temp.add(node.right);
			}
		}
		return head;
	}

	private static Node generateNode(Integer value) {
		if (value == null) {
			return null;
		}
		return new Node(value);
	}

	public static void main(String[] args) {
		Integer maxLevel = 5;
		Integer maxNum = 20;
		Integer testTime = 10;
		for (int i = 0; i < testTime; i++) {
			Integer randomLevel = (int) (Math.random() * maxLevel);
			Node head = generateBinaryTree(1, randomLevel, maxNum);

			Queue<Integer> preQueue = preSerial(head);
			Node node1 = buildPreTreeByQueue(preQueue);

			Queue<Integer> posQueue = posSerial(head);
			Node node2 = buildPosTreeByQueue(posQueue);

			Queue<Integer> levelQueue = levelSerial(head);
			Node node3 = buildLevelTreeByQueue(levelQueue);

			if (!isEqulsNode(node1, node2) || !isEqulsNode(node1, node3)) {
				System.out.println("失败");
			}
		}
	}

	private static boolean isEqulsNode(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null && node2 != null) {
			return false;
		}
		if (node1 != null && node2 == null) {
			return false;
		}
		if (node1.value != node2.value) {
			return false;
		}
		return isEqulsNode(node1.left, node2.left) && isEqulsNode(node1.right, node2.right);
	}

	private static Node generateBinaryTree(Integer curLevel, Integer maxLevel, Integer maxNum) {
		if (curLevel > maxLevel || Math.random() < 0.2) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxNum));
		head.left = generateBinaryTree(curLevel + 1, maxLevel, maxNum);
		head.right = generateBinaryTree(curLevel + 1, maxLevel, maxNum);
		return head;
	}


}
