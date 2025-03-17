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
        if(head==null || head.next==null) return null;   // if no elements in LL or only one element 

        Node mover= head;

        while(mover.next.next!=null){                   // this required atleaset 2 nodes // it will stop at second last element where nodes in LL are atleast 2
            mover=mover.next;
        }

        mover.next=null;

        return head;
    }

/********************************************************************************************************************************/

    static Node deleteKthElement(Node head,int k){
        if(head==null) return null;

        if(k==1){
            head=head.next;
            return head;
        }

        int counter=0;
        Node prev=null;
        Node mover=head;
        while(mover!=null){
            counter++;

            if(counter==k){
                prev.next=prev.next.next;
                break;
            }

            prev=mover;
            mover=mover.next;
        }
        return head;
    }

/********************************************************************************************************************************/

    static Node deleteElementWithGivenValue(Node head,int val){
        if(head==null) return null;

        if(head.data==val){
            head=head.next;
            return head;
        }

        Node prev=null;
        Node mover=head;
        while(mover!=null){
            if(mover.data==val){
                prev.next=prev.next.next;
                break;
            }
            prev=mover;
            mover=mover.next;
        }
        return head;
    }

/********************************************************************************************************************************/

    static Node insertAtHead(Node head,int val){
        Node newNode = new Node(val);
        if(head==null){
            head=newNode;
            return head;
        }
        newNode.next=head;
        head=newNode;
        return head;
        /*
            above whole code gives same result as below three lines.

            Node newNode = new Node(val);
            newNode.next=head;
            return newNode;
         */
    }

/********************************************************************************************************************************/

// Time: O(n)
    static Node insertAtTail(Node head,int val){
        Node newNode = new Node(val);
        if(head==null){
            head=newNode;
            return head;
        }
        Node mover=head;
        while(mover.next!=null){
            mover=mover.next;
        }
        mover.next=newNode;
        return head;
    }
    /*
    For O(1) - time , maintain a tail pointer.

        void insertAtTail(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
     */
/********************************************************************************************************************************/

// k > n+1 (Out of Bounds)

    // Time: O(n)
    static Node insertAtKthPosition(Node head,int k,int val){
        Node newNode = new Node(val);
        if(head==null){
            if(k==1) head=newNode;
            return head;
        }
        int counter=0;
        Node mover=head;

        while(mover!=null){
            counter++;
            if(counter==k-1){
                newNode.next=mover.next;
                mover.next=newNode;
                 break;
            }
            mover=mover.next;
        }
        return head;
    }



   
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
