package Problem;

public class SortList {
    private static final int SENTRY = Integer.MAX_VALUE;

    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode mid = findMidAndBreak(head);

        return mergeLists(sortList(head), sortList(mid));
    }

    public static ListNode findMidAndBreak(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slowPtr = head;
        ListNode fastPtr = head;
        ListNode lastSlowPtr = null;
        while (fastPtr != null) {
            fastPtr = fastPtr.next;
            if (fastPtr == null) {
                break;
            }
            fastPtr = fastPtr.next;
            lastSlowPtr = slowPtr;
            slowPtr = slowPtr.next;
        }
        if (lastSlowPtr != null) {
            lastSlowPtr.next = null;
        }
        return slowPtr;
    }

    public static ListNode mergeLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return (list1 == null) ? list2 : list1;
        }

        int v1 = list1.val;
        int v2 = list2.val;
        ListNode newHead = (v1 <= v2) ? list1 : list2;
        ListNode p = newHead;
        if (v1 <= v2) {
            list1 = list1.next;
        } else {
            list2 = list2.next;
        }

        while (list1 != null || list2 != null) {
            v1 = (list1 == null) ? SENTRY : list1.val;
            v2 = (list2 == null) ? SENTRY : list2.val;

            if (v1 <= v2) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        return newHead;
    }
}
