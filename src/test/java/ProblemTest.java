import Problem.ListNode;
import Problem.SortList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProblemTest {

    public static class SortListTestCase {
        ListNode head;
        int[] expect;

        public SortListTestCase(ListNode head, int[] expect) {
            this.head = head;
            this.expect = expect;
        }
    }

    public static class MergeListTestCase {
        ListNode list1;
        ListNode list2;
        int[] expect;

        public MergeListTestCase(int[] list1, int[] list2, int[] expect) {
            this.list1 = TestUtils.arrayToListWithoutDummy(list1);
            this.list2 = TestUtils.arrayToListWithoutDummy(list2);
            this.expect = expect;
        }
    }

    @Test
    public void testMergeList() {
        List<MergeListTestCase> testCases = getMergeListTestCases();

        for (MergeListTestCase testCase : testCases) {
            assertArrayEquals(testCase.expect,
                    TestUtils.listWithoutDummyToArray(SortList.mergeLists(
                            testCase.list1,
                            testCase.list2)));
        }
    }

    private List<MergeListTestCase> getMergeListTestCases() {
        List<MergeListTestCase> testCases = new ArrayList<>();
        testCases.add(new MergeListTestCase(new int[]{1}, new int[]{}, new int[]{1}));
        testCases.add(new MergeListTestCase(new int[]{}, new int[]{1}, new int[]{1}));

        testCases.add(new MergeListTestCase(new int[]{1}, new int[]{2}, new int[]{1, 2}));
        testCases.add(new MergeListTestCase(new int[]{2}, new int[]{1}, new int[]{1, 2}));

        testCases.add(new MergeListTestCase(new int[]{1, 2}, new int[]{}, new int[]{1, 2}));
        testCases.add(new MergeListTestCase(new int[]{2}, new int[]{1, 3}, new int[]{1, 2, 3}));
        testCases.add(new MergeListTestCase(new int[]{2, 4}, new int[]{1}, new int[]{1, 2, 4}));
        testCases.add(new MergeListTestCase(new int[]{2, 4}, new int[]{1, 3}, new int[]{1, 2, 3, 4}));
        testCases.add(new MergeListTestCase(new int[]{1, 2, 4}, new int[]{3, 5}, new int[]{1, 2, 3, 4, 5}));
        testCases.add(new MergeListTestCase(
                new int[]{1, 2, 4, 8},
                new int[]{3, 5, 9, 10},
                new int[]{1, 2, 3, 4, 5, 8, 9, 10}));
        return testCases;
    }

    @Test
    public void testSortList() {
        int[][] inputs = {
                {},
                {1},
                {4, 5},
                {4, 4},
                {1, 2, 1},
                {1, 2, 3},
                {1, 1, 1},
                {1, 2, 3, 1},
                {1, 2, 2, 1},
                {-1, 5, 3, 4, 0},
        };
        int[][] expects = {
                {},
                {1},
                {4, 5},
                {4, 4},
                {1, 1, 2},
                {1, 2, 3},
                {1, 1, 1},
                {1, 1, 2, 3},
                {1, 1, 2, 2},
                {-1, 0, 3, 4, 5},
        };

        List<SortListTestCase> testCases = getSortListTestCases(inputs, expects);

        for (SortListTestCase testCase : testCases) {
            int[] actual = TestUtils.listWithoutDummyToArray(SortList.sortList(testCase.head));
            assertArrayEquals(testCase.expect, actual);
        }
    }

    @Test
    public void testFindMidAndBreak() {
        int[][] inputs = {
                {1},
                {4, 5},
                {1, 2, 1},
                {1, 2, 3, 1},
                {1, 2, 3, 4, 5},
        };
        for (int i = 0; i < inputs.length; i++) {
            int[] input = inputs[i];
            int inputLength = input.length;
            ListNode list = TestUtils.arrayToListWithoutDummy(input);

            // verify both halves have correct values and length
            String caseId = String.format("case %d", i);
            ListNode halfList = SortList.findMidAndBreak(list);
            if (inputLength == 1) {
                assertNull(caseId, halfList);
                assertEquals(caseId, input[0], list.val);
                assertNull(caseId, list.next);
                continue;
            }

            for (int j = 0; j < inputLength / 2; j++) {
                assertEquals(caseId, input[j], list.val);
                list = list.next;
            }
            assertNull(caseId, list);

            assertNotNull(halfList);
            for (int j = inputLength / 2; j < inputLength; j++) {
                assertEquals(caseId, input[j], halfList.val);
                halfList = halfList.next;
            }
            assertNull(caseId, halfList);
        }

        int[] emptyInput = new int[]{};
        assertNull(SortList.findMidAndBreak(TestUtils.arrayToListWithoutDummy(emptyInput)));
    }

    private List<SortListTestCase> getSortListTestCases(int[][] inputs, int[][] expects) {
        List<SortListTestCase> testCases = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            SortListTestCase testCase = new SortListTestCase(TestUtils.arrayToListWithoutDummy(inputs[i]), expects[i]);
            testCases.add(testCase);
        }
        return testCases;
    }
}