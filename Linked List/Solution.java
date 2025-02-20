class Node {

    int data;
    Node next;

    Node(int data,Node next){
        this.data = data;
        this.next = next;
    }

    Node(int data){
        this.data = data;
        this.next = null;
    }
    
}


/********************************************************************************************************************************/


class Main{

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    static Node convertArrayToLinkedList(int[] nums){
        Node head = new Node(nums[0]);
        Node mover = head;
        
        for(int i=1;i<nums.length;i++){
            Node node = new Node(nums[i]);
            mover.next = node;
            mover=mover.next;
        }
        return head;
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    static void traverse(Node head){
        Node mover = head;
        
        while(mover!=null){
            System.out.print(mover.data+" ");
            mover=mover.next;
        }
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    static int lengthOfLinkedList(Node head){
        Node mover = head;
        
        int length=0;
        while(mover!=null){
            length++;
            mover=mover.next;
        }
        return length;
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    static boolean searchInLinkedList(Node head,int val){
        Node mover = head;

        while(mover!=null){
            if(mover.data==val) return true;
            mover=mover.next;
        }
        return false;
    }



/********************************************************************************************************************************/

    static Node deleteFromHead(Node head){
        if(head==null) return null;

        head=head.next;
        return head;
    }

/********************************************************************************************************************************/

    static Node deleteFromTail(Node head){
        if(head==null || head.next==null) return null;

        Node mover= head;

        while(mover.next.next!=null){
            mover=mover.next;
        }

        mover.next=null;

        return head;
    }

/********************************************************************************************************************************/
   
    public static void main(String[] args){
        int[] nums = new int[]{3,5,2,6,7,4};

        Node head = convertArrayToLinkedList(nums);

        traverse(head);
        
        System.out.println();
        System.out.println(lengthOfLinkedList(head));
        System.out.println(searchInLinkedList(head,6));
        System.out.println(searchInLinkedList(head,0));
        

    }
}
