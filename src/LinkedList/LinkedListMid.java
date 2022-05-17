package LinkedList;

import java.util.ArrayList;

/**
 * 快慢指针应用
 *
 * @author huozhonghun
 * @since 2022-05-17-上午 09:01:59
 */
public class LinkedListMid {

	public static class Node {
		public int value;
		public Node next;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
	 */
	public static Node midOrUpMidNode(Node node) {
		if (node == null || node.next == null || node.next.next == null) {
			return node;
		}
		Node fast = node.next.next;
		Node low = node.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			low = low.next;
		}
		return low;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 */
	public static Node midOrDownMidNode(Node node) {
		if (node == null || node.next == null || node.next.next == null) {
			return node;
		}
		Node fast = node.next;
		Node low = node.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			low = low.next;
		}
		return low;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 */
	public static Node midOrUpMidPreNode(Node node) {
		if (node == null || node.next == null || node.next.next == null) {
			return node;
		}
		Node fast = node.next.next;
		Node low = node;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			low = low.next;
		}
		return low;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 */
	public static Node midOrDownMidPreNode(Node node) {
		if (node == null || node.next == null) {
			return node;
		}
		if(node.next.next == null){
			return node;
		}

		Node fast = node.next;
		Node low = node;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			low = low.next;
		}
		return low;
	}

	/**
	 * 使用容器，用于比较
	 */
	public static Node midOrUpMidNodeByContainer(Node node) {
		if (node == null) {
			return node;
		}
		ArrayList<Node> list = new ArrayList<>();
		Node head = node;
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		return list.get((list.size() - 1) / 2);
	}


	/**
	 * 使用容器，用于比较
	 */
	public static Node midOrDownMidNodeByContainer(Node node) {
		if (node == null) {
			return node;
		}
		ArrayList<Node> list = new ArrayList<>();
		Node head = node;
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		return list.get(list.size() / 2);
	}

	/**
	 * 使用容器，用于比较
	 */
	public static Node midOrUpMidPreNodeByContainer(Node node) {
		if (node == null) {
			return node;
		}
		ArrayList<Node> list = new ArrayList<>();
		Node head = node;
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		return list.get((list.size() - 3) / 2);
	}

	/**
	 * 使用容器，用于比较
	 */
	public static Node midOrDownMidPreNodeByContainer(Node node) {
		if (node == null) {
			return node;
		}
		ArrayList<Node> list = new ArrayList<>();
		Node head = node;
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		return list.get((list.size() - 2) / 2);
	}

	public static void main(String[] args) {
		Node test = new Node(0);
		test.next = new Node(1);
		test.next.next = new Node(2);
		test.next.next.next = new Node(3);
		test.next.next.next.next = new Node(4);
		test.next.next.next.next.next = new Node(5);
		test.next.next.next.next.next.next = new Node(6);
		test.next.next.next.next.next.next.next = new Node(7);
		test.next.next.next.next.next.next.next.next = new Node(8);

		System.out.println(midOrUpMidNode(test).value);
		System.out.println(midOrUpMidNodeByContainer(test).value);

		System.out.println(midOrDownMidNode(test).value);
		System.out.println(midOrDownMidNodeByContainer(test).value);

		System.out.println(midOrUpMidPreNode(test).value);
		System.out.println(midOrUpMidPreNodeByContainer(test).value);

		System.out.println(midOrDownMidPreNode(test).value);
		System.out.println(midOrDownMidPreNodeByContainer(test).value);
	}
}
