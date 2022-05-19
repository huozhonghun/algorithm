package LinkedList;

import java.util.Stack;

/**
 * 判断链表是否是回文链表
 *
 * @author weichenglin
 * @since 2022-05-19-上午 09:08:15
 */
public class IsPalindromeList {

	// 使用额外空间n
	public static boolean isPalindromeUseContainer(Node node) {
		Stack<Node> stack = new Stack<>();
		Node pre = node;
		while (pre != null) {
			stack.push(pre);
			pre = pre.next;
		}

		while (node != null) {
			if (node.value == stack.pop().value) {
				node = node.next;
				continue;
			}
			return false;
		}
		return true;
	}

	// 使用额外空间n/2
	public static boolean isPalindromeUseContainer2(Node node) {
		if (node == null || node.next == null) {
			return true;
		}

		Node right = node;
		Node pre = node;
		while (pre.next != null && pre.next.next != null) {
			pre = pre.next.next;
			right = right.next;
		}

		Stack<Node> stack = new Stack<>();
		while (right != null) {
			stack.push(right);
			right = right.next;
		}

		while (!stack.isEmpty()) {
			if (node.value != stack.pop().value) {
				return false;
			}
			node = node.next;
		}

		return true;
	}

	/**
	 * 本质就是先找到链表的中点，然后把后半部分进行翻转
	 */
	public static boolean isPalindrome(Node head) {
		if (head == null || head.next == null) {
			return true;
		}

		Node n1 = head;
		Node n2 = head;
		while (n2.next != null && n2.next.next != null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}

		// n1是中点或者偶数中点前个
		// n2是尾巴或者尾巴前一个
		n2 = n1.next;// n2 -> right part first node
		n1.next = null;// mid.next -> null
		Node n3 = null;
		while (n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}

		n2 = head;// n2 -> left first node
		while (n1 != null && n2 != null) {
			if (n1.value != n2.value) {
				return false;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		return true;
	}

	public static void main(String[] args) {
		Node test = null;
		test = new Node(1);
		test.next = new Node(1);
		test.next.next = new Node(2);
		test.next.next.next = new Node(3);
		test.next.next.next.next = new Node(3);
		test.next.next.next.next.next = new Node(2);
		test.next.next.next.next.next.next = new Node(1);
		test.next.next.next.next.next.next.next = new Node(1);

		System.out.println(isPalindromeUseContainer(test));
		System.out.println(isPalindromeUseContainer2(test));
		System.out.println(isPalindrome(test));
	}
}
