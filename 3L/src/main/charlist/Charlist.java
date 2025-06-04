package charlist;
/**
 * CS515 Assignment 3L

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/04/2025

Collaboration Declaration: 

Collaboration: None
*/
/**
 * LinkedList used to represent jumbled words.
 */
public class Charlist {
    
    private Elem head; // Elem that is list head

    /**
     * Default constructor for Charlist.
     */
    public Charlist() {
        head = null;
    }

    /**
     * Copy constructor for Charlist.
     * @param cl the Charlist to make a copy of
     */
    public Charlist(Charlist cl) {

        if (cl.head == null) {
            this.head = null;
        } else {
            this.head = new Elem();
            this.head.info = cl.head.info;
            Elem e1 = this.head;
            Elem e2 = cl.head.next;

            while (e2 != null) {
                e1.next = new Elem();
                e1.next.info = e2.info;
                e1 = e1.next;
                e2 = e2.next;
            }
            
        }
    }

    /**
     * toString method override for printing Charlist contents.
     * @return a string represetning the Charlist
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Elem e = this.head;
        while (e != null) {
            sb.append(e.info);
            e = e.next;
        }
        return sb.toString();
    }
    
    /**
     * Inserts a char node at list front.
     * @param c the char to insert 
     */
    public void insertFront(char value) {
        Elem e = new Elem();
        e.info = value;
        e.next = this.head;
        this.head = e;
    }

    /**
     * Inserts a char node at list rear.
     * @param c the char to insert
     */
    public void insertRear(char value) {
        Elem newElem = new Elem();
        newElem.info = value;
        newElem.next = null;

        
        if (head == null) { // Empty list
            this.head = newElem;
        } else {
            Elem cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
        cur.next = newElem;
        }
    }
    
    /**
     * Inner class representing linked list node.
     */
    public class Elem {
        private char info;
        private Elem next;

        /**
         * Default constructor for Elem object that creates blank Elem.
         */
        public Elem() {
            this.info = 0;
            this.next = null;
        }
        
        /**
         * Constructor for Elem objects.
         * @param info the char for this Elem
         * @param next the next Elem in linked list
         */
        public Elem(char info, Elem next) {
            this.info = info;
            this.next = next;
        }
    }

}