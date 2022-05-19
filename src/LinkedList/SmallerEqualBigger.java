package LinkedList;

/**
 * 将单向链表按某值划分成布边小、中间相等、右边大的形式
 *
 * @author weichenglin
 * @since 2022-05-19-上午 10:18:10
 */
public class SmallerEqualBigger {

	private static Node listPartition(Node head, int num) {
		if (head == null) {
			return head;
		}

		Node cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}

		Node[] nodes = new Node[i];
		i = 0;
		cur = head;
		while (cur != null) {
			nodes[i++] = cur;
			cur = cur.next;
		}
		// 数据分区
		arrPartition(nodes, num);

		// 把后面的链表全部拼接在第一个后面
		for (int j = 1; j < nodes.length; j++) {
			nodes[j - 1].next = nodes[j];
		}
		nodes[nodes.length - 1].next = null;
		return nodes[0];
	}

	private static Node listPartition2(Node head, int num) {
		if (head == null) {
			return head;
		}

		Node smallH = null;
		Node smallT = null;
		Node equalH = null;
		Node equalT = null;
		Node bigH = null;
		Node bigT = null;
		Node next = null;

		// 设置三条链表的头尾：小，等，大
		while (head != null) {
			next = head.next;

			/**
			 * 注意：如果不加，会导致什么问题？
			 * 比如，Node{value=7, next=Node{value=9, next=Node{value=1, next=Node{value=8, next=Node{value=5, next=Node{value=2, next=Node{value=5, next=null}}}}}}}
			 * 输出正确结果之后，后面一直循坏遍历5,5,7,9,8,下面每个区间头尾只需要当前节点！！！！
			 */
			head.next = null;
			if (head.value < num) {
				// 小于
				if (smallH == null) {
					smallH = head;
					smallT = head;
				} else {
					smallT.next = head;
					// 下移到下个节点
					smallT = head;
				}
			} else if (head.value == num) {
				// 等于
				if (equalH == null) {
					equalH = head;
					equalT = head;
				} else {
					equalT.next = head;
					equalT = head;
				}
			} else {
				// 大于
				if (bigH == null) {
					bigH = head;
					bigT = head;
				} else {
					bigT.next = head;
					bigT = head;
				}
			}
			head = next;
		}

		/**
		 * 这里需要判断ssmallH、equalH、bigH进行拼接
		 */
		// if (smallT != null) {
		// 	if (equalT != null && bigT != null) {
		// 		smallT.next = equalH;
		// 		equalT.next = bigH;
		// 	} else if (equalT != null) {
		// 		smallT.next = equalH;
		// 	} else {
		// 		smallT.next = bigH;
		// 	}
		// } else if (equalT != null) {
		// 	equalT.next = bigH == null ? null : bigH;
		// }

		// 比较优，简单的写法
		if (smallT != null) {
			smallT.next = equalH;
			equalT = equalT == null ? smallT : equalT;
		}
		if (equalT != null) {
			equalT.next = bigH;
		}

		return smallH != null ? smallH : (equalH != null ? equalH : bigH);
	}


	private static void arrPartition(Node[] nodes, int num) {
		int start = 0;
		int end = nodes.length - 1;
		int index = 0;
		while (index != end) {
			if (nodes[index].value < num) {
				swap(nodes, start++, index++);
			} else if (nodes[index].value == num) {
				index++;
			} else {
				swap(nodes, end--, index);
			}
		}
	}

	private static void swap(Node[] nodes, int a, int b) {
		Node temp = nodes[a];
		nodes[a] = nodes[b];
		nodes[b] = temp;
	}

	public static void main(String[] args) {
		Node head = new Node(7);
		head.next = new Node(9);
		head.next.next = new Node(1);
		head.next.next.next = new Node(8);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next = new Node(5);

		// Node next = head.next;
		// System.out.println(next);
		// // head.next = null;
		// System.out.println(head);
		// head = next;
		// System.out.println(head);


		printLinkedList(head);
		// head = listPartition(head, 4);
		head = listPartition2(head, 5); // 原来的head变成了7,9,8
		printLinkedList(head);
	}

	private static void printLinkedList(Node head) {
		while (head != null) {
			System.out.println(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
}
