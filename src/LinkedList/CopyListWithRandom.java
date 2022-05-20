package LinkedList;

import java.util.HashMap;
import java.util.Objects;

/**
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * [要求] 时间复杂度O(N)，额外空间复杂度0(1)
 *
 * @author weichenglin
 * @since 2022-05-19-下午 06:16:09
 */
public class CopyListWithRandom {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}

		@Override
		public String toString() {
			return "Node{" +
					"val=" + val +
					", next=" + next +
					", random=" + random +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return val == node.val && Objects.equals(next, node.next) && Objects.equals(random, node.random);
		}

		@Override
		public int hashCode() {
			return Objects.hash(val, next, random);
		}
	}

	public static Node copyRandomListUseContainer(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}

		cur = head;
		while (cur != null) {
			// 旧.next = 新.next
			map.get(cur).next = map.get(cur.next);
			// 旧.random = 新.random
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyRandomList(Node head) {
		while (head == null) {
			return head;
		}

		/**
		 * 把copy节点拼接在节点后
		 * 1->2->3
		 * 1->1`->2->2`->3->3`
		 */
		Node cur = head;
		while (cur != null) {
			Node next = cur.next;
			Node nodeCopy = new Node(cur.val);
			cur.next = nodeCopy;
			nodeCopy.next = next;
			cur = next;
		}

		// 设置copy节点的random
		cur = head;
		while (cur != null) {
			Node nodeCopy = cur.next;
			nodeCopy.random = cur.random != null ? cur.random.next : null;
			cur = cur.next.next;
		}

		// 拆分新老链表
		cur = head;
		// 第二个就是复制链表的头
		Node res = head.next;
		while (cur != null) {
			Node next = cur.next.next;
			Node nodeCopy = cur.next;
			nodeCopy.next = next != null ? next.next : null;
			cur.next = next;
			cur = next;
		}
		return res;
	}

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);

		node1.next = node2;
		node1.random = node3;

		node1.next.next = node3;
		node1.next.random = node4;

		node1.next.next.next = node4;
		node1.next.next.random = null;

		node1.next.next.next.next = null;
		node1.next.next.next.random = null;

		Node nodeCopy1 = copyRandomListUseContainer(node1);
		System.out.println(nodeCopy1.equals(node1));


		Node nodeCopy2 = copyRandomList(node1);
		System.out.println(nodeCopy2.equals(node1));

	}
}
