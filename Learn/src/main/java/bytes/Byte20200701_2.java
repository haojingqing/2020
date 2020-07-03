package bytes;

/**
 * @author jingqing
 */
public class Byte20200701_2 {


//    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//    示例：
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807


    public static void main(String[] args) {
        // 构造 2->4->3
        ListNode node1 = new ListNode(2);
        ListNode node11 = new ListNode(4);
        ListNode node111 = new ListNode(3);
        node1.next = node11;
        node11.next = node111;

        // 构造5->6->5
        ListNode node2 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node222 = new ListNode(4);
        node2.next = node22;
        node22.next = node222;

        ListNode result = addTwoNumbersSelfV2(node1, node2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 好麻烦啊,不清晰,还不对
     * NONONO！！！！ 不摇动原来传进来的链表参数，可能外面还要用呢
     */
    public static ListNode addTwoNumbersSelf(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;

        int carry = 0;
        while (node1 != null && node2 != null) {
            int val = node1.val + node2.val + carry;
            carry = val / 10;
            val = val % 10;

            curNode.next = new ListNode(val);
            curNode = curNode.next;

            node1 = node1.next;
            node2 = node2.next;
        }
        if (node1 != null) {
            node1.val = node1.val + carry;
            curNode.next = node1;
        }
        if (node2 != null) {
            node2.val = node2.val + carry;
            curNode.next = node2;
        }
        return head;
    }

    public static ListNode addTwoNumbersSelfV2(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(0);
        ListNode p1 = node1;
        ListNode p2 = node2;
        ListNode curNode = head;
        // 进位
        int carry = 0;
        while (p1 != null || p2 != null) {
            int x = (p1 != null) ? p1.val : 0;
            int y = (p2 != null) ? p2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curNode.next = new ListNode(sum % 10);
            curNode = curNode.next;

            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        if (carry > 0) {
            curNode.next = new ListNode(carry);
        }

        return head;
    }


    public ListNode addTwoNumbersRight(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }

        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
