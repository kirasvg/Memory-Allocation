// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List  prev;  // Previous Node

    public A1List(int address, int size, int key){
        super(address, size, key);
    }

    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel

        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {   if(this.key==-1 && this.next==null){
        return null;
    }
        A1List temp = new A1List(address, size, key);
        temp.next = this.next;
        temp.prev = this;
        this.next.prev = temp;
        this.next = temp;
        return temp;

    }

    public boolean Delete(Dictionary d){
        if (this.key != -1) {
            A1List current = new A1List();
            current = this;
            while (current.next != null) {
                if (current.key == d.key) {
                    if (current.address == d.address && current.size == d.size) {
                        if (current.next!= null)
                            current.prev.next = current.next;
                        if (current.prev!= null)
                            current.next.prev = current.prev;
                        return true;
                    }
                }
                current=current.next;
            }
            while (current.prev != null) {
                if (current.key == d.key) {
                    if (current.address == d.address && current.size == d.size) {
                        if (current.next!= null)
                            current.prev.next = current.next;
                        if (current.prev!= null)
                            current.next.prev = current.prev;
                        return true;
                    }
                }
                current=current.prev;
            }
        }
        else{
            if(this.next==null){
                A1List current = new A1List();
                current = this;
                while (current.prev != null) {
                    if (current.key == d.key) {
                        if (current.address == d.address && current.size == d.size) {
                            if (current.next!= null)
                                current.prev.next = current.next;
                            if (current.prev!= null)
                                current.next.prev = current.prev;
                            return true;
                        }
                    }
                    current=current.prev;
                }
            }
            else if(this.prev==null){
                A1List current = new A1List();
                current = this;
                while (current.next != null) {
                    if (current.key == d.key) {
                        if (current.address == d.address && current.size == d.size) {
                            if (current.next!= null)
                                current.prev.next = current.next;
                            if (current.prev!= null)
                                current.next.prev = current.prev;
                            return true;
                        }
                    }
                    current=current.next;
                }
            }
        }
        return false;
    }
    public A1List Find(int k,boolean exact) {
        if (exact == true) {
            A1List current = new A1List();
            current = this;
            while (current.prev!=null){
                current=current.prev;
            }
            while (current.next != null) {
                if (current.key == k)
                    return current;
                else
                    current = current.next;
            }
        }
        else {
            A1List current = new A1List();
            current = this;
            while (current.prev!=null){
                current=current.prev;
            }
            while (current.next != null) {
                if (current.key >= k)
                    return current;
                else
                    current = current.next;
            }
        }
        return null;
    }

    public A1List getFirst()
    {if(this.key!=-1){
        A1List current = new A1List();
        current = this;
        while (current.prev.key!=-1) {
            current = current.prev;
        }
        return current;
    }
    else{
        if(this.next==null && this.prev.key!=-1){
            A1List current = new A1List();
            current = this;
            while (current.prev.key!=-1) {
                current = current.prev;
            }
            return current;
        }
        else if(this.next==null && this.prev.key==-1){
            return null;
        }
        if(this.prev==null && this.next.key!=-1){
            return this.next;
        }
        else if(this.prev==null && this.next.key==-1){
            return null;
        }
        }
        return null;
    }

    public A1List getNext() {
        if (this.key != -1) {
            if (this.next != null) {
                return this.next;
            } else
                return null;
        } else {
            if (this.prev == null && this.next.key != -1) {
                return this.next;
            } else if (this.prev == null && this.next.key == -1) {
                return null;
            }
            if (this.next == null && this.prev.key != -1) {
                return null;
            } else if (this.next == null && this.prev.key == -1) {
                return null;
            }
            return null;
        }
    }

    public boolean sanity(){
        if (this.key != -1) {
            A1List current = new A1List();
            current = this;
            while (current.next != null) {
                if (this.next.prev != this)
                    return false;
                current = current.next;
            }
            while (current.prev != null) {
                if (this.next.prev != this)
                    return false;
                current = current.prev;
            }
            if (this.next == this.prev) {
                return false;
            }
        } else {
            if (this.prev == null) {
                if (this.next.prev != this)
                    return false;
            }
            if (this.next == null) {
                if (this.next.prev != this)
                    return false;
            }
            if (this.prev != null || this.next != null) {
                return false;
            }
        }
        return true;
    }
}




