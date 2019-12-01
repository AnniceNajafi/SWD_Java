/**
 * @author Annice Najafi
 * Description: Our linkedList class allowed insertions and deletions at only the front and the back of the linked list.
 * These capabilities were convenient for us when we used inheritance or composition to produce a stack class and a
 * queue class with minimal code simply by reusing the list class. Linked lists are normally more general than those
 * we provided. Modify the linked-listclass we developed in this chapter to handle insertions and deletions anywhere
 * in the list.
 */
public class LinkedList{
    /**
     * Instance variables: int numOfElements and the head Node which points to the first Node in the LinkedList
     */
    private static int numOfElements;
    private Node head;

    /**
     * Constructor sets the numOfElements to zero when a LinkedList is constructed
     * sets the head Node of the LinkedList to null when nothing is added to the LinkedList and it is just made
     */
    public LinkedList(){
        numOfElements=0;
        head=null;
    }

    /**
     * checks if the LinkedList is empty or has Nodes in it
     * No input
     * @return a boolean true if find no elements false if the LinkedList has elements
     */
    public boolean isEmpty(){
        ///**Note: we only have to check the hea Node
        return head==null;
    }

    /**
     * returns the number of Nodes in the LinkedList
     * @return int, how many Nodes are in the LinkedList
     */
    public int size(){
        return numOfElements;
    }

    /**
     * adds a Node at an index i with Element O
     * @param i is the index we want the Node to be added at
     * @param O is the element of the Node
     * No output changes the LinkedList
     */
    public void add(int i,Object O){
        ///if the index is 0 we want the Node to be added to the beginning of the LinkedList and that will change the head
        if(i==0){
            ///case I. LinkedList is empty
            if(isEmpty()) {
                ///simply add new Node
                head = new Node(O);
            }
            ///case II. LinkedList is not empty change head to new Node
            else{
                Node keep = head;
                head = new Node(O);
                head.next=keep;
            }
        }///if we want to add the Node to the end of the LinkedList
        else if(i==size()){
            ///loop through all the Nodes until we reach the last Node and set the pointer of that Node to new Node
            Node walk = head;
            for (int j = 0; j < size()-1; j++) {
                walk = walk.next;
            }
            Node nodeToAdd = new Node(O);
            walk.next=nodeToAdd;

        }///if we want to add the new Node to somewhere in the middle of the list
        else {
            try {
                ///Loop through all Nodes in the LinkedList until we reach that index, we don't want to do anything
                ///to the Nodes before that index
                ///add the new Node at that index and move all nodes after that 1 position forward
                Node walk = head;
                for (int j = 0; j < i - 1; j++) {
                    walk = walk.next;
                }
                Node nodeAfter = walk.next;
                Node nodeToAdd = new Node(O);
                walk.next = nodeToAdd;
                nodeToAdd.next = nodeAfter;
            }catch(IndexOutOfBoundsException E){
                System.out.println("Error! Cannot add Object at that index");
            }
        }
        numOfElements++;

    }

    /**
     * Removes a Node at an index i with Element O
     * @param i is the index of the Node we want to remove
     * @return the Object placed at the removed Node
     */
    public Object remove(int i){
        Object old=null;
        ///case I. we want to remove the first element of the LinkedList
        if(i==0){
            ///simply store the Object at head and make the next Node head
            old = head.element;
            head=head.next;
        }
        ///case II. we want to remove the last Node in the LinkedList
        else if(i==size()){
            ///Go through all the Nodes and set the pointer of the second to last Node to null
            Node walk = head;
            for (int j = 0; j < size()-1; j++) {
                walk = walk.next;
            }
            old = walk.next;
            walk.next=null;

        }
        ///case III. remove a Node somewhere in the middle of the LinkedList
        else {
            try {
                ///go through all the Nodes in the linkedList and remove the Node at the index then move the pointer of
                ///the node at that index to the Node after that
                Node walk = head;
                for (int j = 0; j < i - 1; j++) {
                    walk = walk.next;
                }
                old = walk.next.element;
                walk.next = walk.next.next;
            }catch(IndexOutOfBoundsException E){
                System.out.println("Error! Cannot add Object at that index");
            }

        }
        numOfElements--;
        return old;

    }
    /**
     * Inner class Node
     */
    private static class Node{
        /**
         * contains two instance variables, an Object element and a Node next, each Node consists of
         * an elements and a pointer to the next Node
         */
        Object element;
        Node next;

        /**
         * Constructor sets the element in the Node equal to the Object given in the input
         * @param element, element of the Node
         */
        public Node(Object element){
            this.element = element;
            next=null;
        }
    }
    public static void main(String[] args){
        LinkedList Annice = new LinkedList();
        Annice.add(0,1);
        Annice.add(1,2);
        Annice.add(2,66);
        Annice.add(1,13);
        Annice.add(1, 73);
        Annice.add(2, 45);
        System.out.println(Annice.remove(1));
        System.out.println(Annice.remove(2));
    }
}
