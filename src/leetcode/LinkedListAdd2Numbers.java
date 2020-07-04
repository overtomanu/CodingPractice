package leetcode;

public class LinkedListAdd2Numbers
{

}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class SolLinkedListAdd2Numbersution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode res = new ListNode(0);
        ListNode rc = res;
        while(l1!=null || l2!=null){
            int result=carry;
            if(l1!=null){
                result+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                result+=l2.val;
                l2=l2.next;
            }
            carry=result>9?1:0;
            rc.next=new ListNode(result%10);
            rc=rc.next;
        }
        return res.next;
    }
}
