class Solution {

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
            return true;

        // Step 1 : Find Middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2 : Reverse Second Half
        ListNode prev = null;
        ListNode current = slow;

        while (current != null) {

            ListNode next = current.next;

            current.next = prev;

            prev = current;

            current = next;
        }

        // Step 3 : Compare
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {

            if (first.val != second.val)
                return false;

            first = first.next;
            second = second.next;
        }

        return true;
    }
}