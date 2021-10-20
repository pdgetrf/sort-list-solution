import Problem.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestUtils {
    public static int[] listWithoutDummyToArray(ListNode list) {
        List<Integer> result = new ArrayList<>();
        while (list != null) {
            result.add(list.val);
            list = list.next;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void listWithoutDummyToArray() {
        // TODO
    }

    public static ListNode arrayToListWithoutDummy(int[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        ListNode head = null;
        ListNode ptr = null;
        for (int val : input) {
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                ptr = head;
                continue;
            }
            ptr.next = newNode;
            ptr = newNode;
        }
        return head;
    }

    @Test
    public void testArrayToListWithoutDummy() {
        int[][] inputs = {
                null,
                {},
                {1},
                {1, 2},
                {3, 3},
                {1, 4, 2},
                {3, 4, 5, 6, 7},
        };

        for (int[] input : inputs) {
            String caseId = String.format("case %s failed", Arrays.toString(input));
            ListNode list = arrayToListWithoutDummy(input);
            if (input == null) {
                assertNull(caseId, list);
                continue;
            }

            ListNode ptr = list;
            for (int i = 0; i < input.length; i++) {
                assertEquals(caseId, input[i], ptr.val);
                ptr = ptr.next;
            }
            assertNull(caseId, ptr);
        }
    }
}
