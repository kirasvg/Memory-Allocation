// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
import javax.lang.model.util.ElementScanner6;
import java.util.EventListener;
public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.

    public BSTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }

    public BSTree(int address, int size, int key) {
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key) {
        if (this.parent == null) {
            if (this.right == null) {
                BSTree temp = new BSTree(address, size, key);
                this.right = temp;
                temp.parent = this;
                temp.left = temp.right = null;
                return temp;
            } else {
                BSTree temp = new BSTree(address, size, key);
                BSTree current = this.right;
                while (current.left != null || current.right != null) {
                    if (temp.key < current.key) {
                        if (current.left != null) {
                            current = current.left;
                        } else
                            break;
                    } else if (temp.key == current.key) {
                        if (temp.address < current.address) {
                            if (current.left != null) {
                                current = current.left;
                            } else
                                break;
                        } else {
                            if (current.right != null) {
                                current = current.right;
                            } else
                                break;
                        }
                    } else {
                        if (current.right != null) {
                            current = current.right;
                        } else {
                            break;
                        }
                    }
                }
                if (temp.key < current.key) {
                    current.left = temp;
                    temp.parent = current;
                } else if (temp.key == current.key) {
                    if (temp.address < current.address) {
                        current.left = temp;
                        temp.parent = current;
                    } else {
                        current.right = temp;
                        temp.parent = current;
                    }
                } else if (temp.key > current.key) {
                    current.right = temp;
                    temp.parent = current;
                }
                return temp;
            }
        }
        else {
                BSTree temp = new BSTree(address, size, key);
                BSTree current = this;
                BSTree pointer= current.parent;
                while (pointer.parent!=null){
                    current=pointer;
                    if(pointer.parent!=null){
                        pointer = pointer.parent;
                    }
                }
                while (current.left!= null || current.right!=null) {
                    if (temp.key < current.key) {
                        if(current.left!=null) {
                            current = current.left;
                        }
                        else
                            break;
                    }
                    else if(temp.key==current.key) {
                        if (temp.address < current.address) {
                            if (current.left != null) {
                                current = current.left;
                            } else
                                break;
                        } else {
                            if (current.right != null) {
                                current = current.right;
                            } else
                                break;
                        }
                    }
                    else {
                            if(current.right!=null) {
                                current = current.right;
                            }
                            else {
                                break;
                            }
                        }
                    }
                    if (temp.key < current.key) {
                        current.left = temp;
                        temp.parent = current;
                    }
                    else if(temp.key==current.key){
                        if(temp.address<current.address){
                            current.left = temp;
                            temp.parent = current;
                        }
                        else{
                            current.right = temp;
                            temp.parent = current;
                        }
                    }
                    else if (temp.key > current.key) {
                        current.right = temp;
                        temp.parent = current;
                    }
                    return temp;
        }
    }

    public boolean Delete(Dictionary e) {
        if (this.parent == null) {
            if (this.right == null) {
                return false;
            }
            else{
                BSTree current = this.right;
                BSTree found= look1(e,current);
                if(found!=null) {
                    if (found.left == null && found.right == null) {
                        if(found==found.parent.left){
                            found.parent.left=null;
                            found.parent=null;
                        }
                        else {
                            found.parent.right=null;
                            found.parent=null;
                        }
                    } else if (found.left != null && found.right == null) {
                        if(found==found.parent.left){
                            found.parent.left=found.left;
                            found.left.parent=found.parent;
                            found.left =null;
                            found.parent=null;
                        }
                        else {
                            found.parent.right=found.left;
                            found.left.parent=found.parent;
                            found.left=null;
                            found.parent=null;
                        }
                    } else if (found.left == null && found.right != null) {
                        if(found==found.parent.left){
                            found.parent.left=found.right;
                            found.right.parent=found.parent;
                            found.right =null;
                            found.parent=null;
                        }
                        else {
                            found.parent.right=found.right;
                            found.right.parent=found.parent;
                            found.right=null;
                            found.parent=null;
                        }
                    } else {
                        BSTree next = min(found.right);
                        found.key = next.key;
                        found.address=next.address;
                        found.size=next.size;
                        if (next.left == null && next.right == null) {
                            if(next==next.parent.left){
                                next.parent.left=null;
                                next.parent=null;
                            }
                            else {
                                next.parent.right=null;
                                next.parent=null;

                            }
                        } else if (next.left != null && next.right == null) {
                            if(next==next.parent.left){
                                next.parent.left=next.left;
                                next.left.parent=next.parent;
                                next.left =null;
                                next.parent=null;
                            }
                            else{
                                next.parent.right=next.left;
                                next.left.parent=next.parent;
                                next.left=null;
                                next.parent=null;
                            }
                        } else if (next.left == null && next.right != null) {
                            if(next==next.parent.left){
                                next.parent.left=next.right;
                                next.right.parent=next.parent;
                                next.right =null;
                                next.parent=null;
                            }
                            else {
                                next.parent.right=next.right;
                                next.right.parent=next.parent;
                                next.right=null;
                                next.parent=null;
                            }

                        }
                    }
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            BSTree current = this;
            BSTree pointer = current.parent;
            while (pointer.parent != null) {
                current = pointer;
                if (pointer.parent != null) {
                    pointer = pointer.parent;
                }
            }
            BSTree found = look1(e, current);
            if (found != null) {
                if (found.left == null && found.right == null) {
                    if (found == found.parent.left) {
                        found.parent.left = null;
                        found.parent = null;
                    } else {
                        found.parent.right = null;
                        found.parent = null;
                    }
                }else if (found.left != null && found.right == null) {
                    if (found == found.parent.left) {
                        found.parent.left = found.left;
                        found.left.parent = found.parent;
                        found.left = null;
                        found.parent = null;
                    } else {
                        found.parent.right = found.left;
                        found.left.parent = found.parent;
                        found.left = null;
                        found.parent = null;
                    }
                } else if (found.left == null && found.right != null) {
                    if (found == found.parent.left) {
                        found.parent.left = found.right;
                        found.right.parent = found.parent;
                        found.right = null;
                        found.parent = null;
                    } else {
                        found.parent.right = found.right;
                        found.right.parent = found.parent;
                        found.right = null;
                        found.parent = null;
                    }
                } else {
                    BSTree next = min(found.right);
                    found.key = next.key;
                    found.address=next.address;
                    found.size=next.size;
                    if (next.left == null && next.right == null) {
                        if (next == next.parent.left) {
                            next.parent.left = null;
                            next.parent = null;
                        }
                        else {
                            next.parent.right = null;
                            next.parent = null;
                            }
                        }
                    else if (next.left != null && next.right == null) {
                        if (next == next.parent.left) {
                            next.parent.left = next.left;
                            next.left.parent = next.parent;
                            next.left = null;
                            next.parent = null;
                            }
                        else {
                            next.parent.right = next.left;
                            next.left.parent = next.parent;
                            next.left = null;
                            next.parent = null;
                            }
                        }
                    else if (next.left == null && next.right != null) {
                        if (next == next.parent.left) {
                            next.parent.left = next.right;
                            next.right.parent = next.parent;
                            next.right = null;
                            next.parent = null;
                            }
                        else {
                            next.parent.right = next.right;
                            next.right.parent = next.parent;
                            next.right = null;
                            next.parent = null;
                            }
                        }
                    }
                }
                return true;
            }
        }


    public BSTree Find(int key, boolean exact) {
        if(exact==true) {
            if (this.parent == null) {
                if (this.right == null) {
                    return null;
                } else {
                    BSTree current=this.right;
                    BSTree found= look(current,key);
                    if(found!=null){
                        return found;
                    }
                    else {
                        return null;
                    }
                }
            } else {
                BSTree current = this;
                BSTree pointer= current.parent;
                while (pointer.parent!=null){
                    current=pointer;
                    if(pointer.parent!=null){
                        pointer = pointer.parent;
                    }
                }
                BSTree found= look(current,key);
                if(found!=null){
                    return found;
                }
                else {
                    return null;
                }

            }
        }
        else {
            if (this.parent == null) {
                if (this.right == null) {
                    return null;
                } else {
                    BSTree current=this.right;
                    while(current!=null) {
                        if(current.key < key) {
                            current= current.right;
                        }
                        else if(current.key == key){
                            if(current.left != null && current.key == current.left.key ) {
                                current= current.left;
                            }else {
                                return current;
                            }
                        }
                        else{
                            if(current.left != null) {
                                if(current.left.key > key) {
                                    current = current.left;
                                }
                                else {
                                    if (current.left.right!=null){
                                        if(current.left.right.key>=key){
                                            current=current.left.right;
                                        }
                                        else {
                                            return current;
                                        }
                                    }
                                    else {
                                        return current;
                                    }
                                }
                            }
                            else {
                                return current;
                            }
                        }
                    }
                    return current;
                }
            }
            else {
                BSTree current = this;
                BSTree pointer= current.parent;
                while (pointer.parent!=null){
                    current=pointer;
                    if(pointer.parent!=null){
                        pointer = pointer.parent;
                    }
                }
                while(current!=null) {
                    if(current.key < key) {
                        current= current.right;
                    }
                    else if(current.key == key){
                        if(current.left != null && current.key == current.left.key ) {
                            current= current.left;
                        }else {
                            return current;
                        }
                    }
                    else{
                        if(current.left != null) {
                            if(current.left.key > key) {
                                current = current.left;
                            }
                            else {
                                if (current.left.right!=null){
                                    if(current.left.right.key>=key){
                                        current=current.left.right;
                                    }
                                    else {
                                        return current;
                                    }
                                }
                                else {
                                    return current;
                                }
                            }
                        }
                        else {
                            return current;
                        }
                    }
                }
                return current;
            }
        }
    }

    public BSTree getFirst() {
        if (this.parent == null) {
            if (this.right == null) {
                return null;
            } else {
                BSTree current = this.right;
                while (current.left != null) {
                    current = current.left;
                }
                return current;
            }
        } else {
            BSTree current = this;
            BSTree pointer= current.parent;
            while (pointer.parent!=null){
                current=pointer;
                if(pointer.parent!=null){
                    pointer = pointer.parent;
                }
            }
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
    }

    public BSTree getNext()
    {   if(this.parent==null){
            return null;
    }
    else {
        BSTree current = this;
        if(current.right!=null){
            return min(current.right);
        }
        else {
            BSTree pointer= current.parent;
            BSTree value= current;
            while (pointer!=null && current==pointer.right){
                if(pointer.parent==null) {
                    return null;
                }
                current=pointer;
                pointer = pointer.parent;
            }
            return pointer;
        }
    }
    }
    private BSTree look(BSTree node,int val){
        if(node.key>val){
            if(node.left!=null) {
                return look(node.left, val);
            }
            else
                return null;
        }
        if(node==null || node.key==val){
            while (node.left!=null) {
                if (node.left.key == val) {
                    node=node.left;
                }
            }
            return node;
        }
        else{
            if(node.right!=null) {
                return look(node.right, val);
            }
            else
                return null;
        }
    }
    private BSTree look1(Dictionary d,BSTree node){
        if(node==null){
            return null;
        }
        if( d.key==node.key){
            if(d.address==node.address){
                return node;
            }
            else if(d.address<node.address){
                return look1(d,node.left);
            }
            else {
                return look1(d,node.right);
            }
        }
        if(node.key>d.key){
            if(node.left!=null) {
                return look1(d, node.left);
            }
            else
                return null;
        }
        if(node.key<d.key){
            if(node.right!=null) {
                return look1(d, node.right);
            }
            else
                return null;
        }
        return null;
    }
    private BSTree min(BSTree node) {
        if (node == null) {
            return null;
        } else {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }
    private boolean check(BSTree node){
        int min=0;
        if (node==null){
            return true;
        }
        else {
            if(check(node.left)){
                return false;
            }
            if(node.key<=min){
                return false;
            }
            min=node.key;
            return check(node.left);
        }
    }
    public boolean sanity()
    {   if (this.parent==null){
            if(this.right==null){
                return true;
            }
            else {
                BSTree current = this.right;
                return check(current);
            }
    }
    else {
        BSTree current= this;
        BSTree pointer= current.parent;
        while (pointer.parent!=null){
            current=pointer;
            if(pointer.parent!=null){
                pointer = pointer.parent;
            }
        }
        return check(current);
    }
    }
}


 


