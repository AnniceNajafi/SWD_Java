import java.util.ArrayList;
import java.util.List;

public class LinkedList{
    /**
     * Instance variables:
     */
    private static int numOfElements;
    private Node head;

    /**
     *
     */
    public LinkedList(){
        numOfElements=0;
        head=null;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     *
     * @return
     */
    public int size(){
        return numOfElements;
    }

    /**
     *
     * @param i
     * @param O
     */
    public void add(int i,Object O){
        if(i==0){
            if(isEmpty()) {
                head = new Node(O);
            }
            else{
                Node keep = head;
                head = new Node(O);
                head.next=keep;
            }
        }
        else if(i==size()){
            Node walk = head;
            for (int j = 0; j < size()-1; j++) {
                walk = walk.next;
            }
            Node nodeToAdd = new Node(O);
            walk.next=nodeToAdd;

        }
        else {
            try {
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
     *
     * @param i
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Object remove(int i) throws IndexOutOfBoundsException{
        Object old=null;
        if(i==0){
            old = head.element;
            head=head.next;
        }
        else if(i==size()){
            Node walk = head;
            for (int j = 0; j < size()-1; j++) {
                walk = walk.next;
            }
            old = walk.next;
            walk.next=null;

        }
        else {
            try {
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
        Object element;
        Node next;

        /**
         *
         * @param element
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
        System.out.println(Annice.remove(2));
    }
}
