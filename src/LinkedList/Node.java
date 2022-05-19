package LinkedList;

/**
 * 链表对象
 *
 * @author weichenglin
 * @since 2022-05-19-上午 10:16:42
 */
public class Node {
	public int value;
	public Node next;

	public Node(int v) {
		value = v;
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				", next=" + next +
				'}';
	}
}
