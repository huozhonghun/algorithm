package LinkedList;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
 * 如果不相交，返回null [要求] 如果两个链表长度之和为N,时间复杂度请达到O(N)，额外空间复 杂度请达到O(1)。
 *
 * @author weichenglin
 * @since 2022-05-20-下午 04:39:13
 */
public class FindFirstIntersectNode {

	private static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}

		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head1);

		/**
		 * 有两种相交情况：都是有环和都是无环
		 * 备注：不可能一个有环，一个无环
		 */
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	private static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}

		// 找出比较长的链表
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while (cur1 != null) {
			cur1 = cur1.next;
			n++;
		}
		while (cur2 != null) {
			cur2 = cur2.next;
			n--;
		}
		// cur1 = head1;
		// cur2 = head2;
		// if(n>0){
		// 	while(n-- != 0){
		// 		cur1 = cur1.next;
		// 	}
		// }else if(n<0){
		// 	while(n++ != 0){
		// 		cur2 = cur2.next;
		// 	}
		// }

		/**
		 * 谁长谁设为cur1，并先走差异长度
		 */
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		n = Math.abs(n);
		while (n-- != 0) {
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {

		Node cur1 = null;
		Node cur2 = null;
		// 入环节点相同，可能相交于环外，也可能相较于入环点
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop1) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n-- != 0) {
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			cur1 = loop1.next;
			// 判断两个环是否会走到一起
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	private static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;
		Node slow = head.next;
		while (fast != slow) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}

		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow;
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);


		// 0->9->8->6(相交)->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);


		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2(相交)...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);
	}
}
