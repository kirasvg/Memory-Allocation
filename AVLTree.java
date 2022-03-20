// Class: Height balanced AVL Tree
// Binary Search Tree
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children.
    private AVLTree parent;          // Parent pointer.
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    {   if (this.parent == null) {
        if (this.right == null) {
            AVLTree temp = new AVLTree(address, size, key);
            this.right = temp;
            temp.parent = this;
            temp.left = temp.right = null;
            return temp;
        } else {
            AVLTree temp = new AVLTree(address, size, key);
            AVLTree current = this.right;
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
                update(current);
                Balance(current);
            }
            else if (temp.key == current.key) {
                if (temp.address < current.address) {
                    current.left = temp;
                    temp.parent = current;
                    update(current);
                    Balance(current);
                } else {
                    current.right = temp;
                    temp.parent = current;
                    update(current);
                    Balance(current);
                }
            }
            else if (temp.key > current.key) {
                current.right = temp;
                temp.parent = current;
                update(current);
                Balance(current);
            }
            return temp;
        }
    }
    else {
        AVLTree temp = new AVLTree(address, size, key);
        AVLTree current = this;
        AVLTree pointer= current.parent;
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
            update(current);
            Balance(current);
        }
        else if(temp.key==current.key){
            if(temp.address<current.address){
                current.left = temp;
                temp.parent = current;
                update(current);
                Balance(current);
            }
            else{
                current.right = temp;
                temp.parent = current;
                update(current);
                Balance(current);
            }
        }
        else if (temp.key > current.key) {
            current.right = temp;
            temp.parent = current;
            update(current);
            Balance(current);
        }
        return temp;
        }
    }

    public boolean Delete(Dictionary e)
    {
        if (this.parent == null) {
            if (this.right == null) {
                return false;
            }
            else{
                AVLTree current = this.right;
                AVLTree found= look1(e,current);
                if(found!=null) {
                    AVLTree p=found.parent;
                    if (found.left == null && found.right == null) {
                        if(found==found.parent.left){
                            found.parent.left=null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                        else {
                            found.parent.right=null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                    } else if (found.left != null && found.right == null) {
                        if(found==found.parent.left){
                            found.parent.left=found.left;
                            found.left.parent=found.parent;
                            found.left =null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                        else {
                            found.parent.right=found.left;
                            found.left.parent=found.parent;
                            found.left=null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                    } else if (found.left == null && found.right != null) {
                        if(found==found.parent.left){
                            found.parent.left=found.right;
                            found.right.parent=found.parent;
                            found.right =null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                        else {
                            found.parent.right=found.right;
                            found.right.parent=found.parent;
                            found.right=null;
                            found.parent=null;
                            update(p);
                            Balance(p);
                        }
                    } else {
                        AVLTree next = min(found.right);
                        found.key = next.key;
                        found.address=next.address;
                        found.size=next.size;
                        AVLTree h=next.parent;
                        if (next.left == null && next.right == null) {
                            if(next==next.parent.left){
                                next.parent.left=null;
                                next.parent=null;
                                update(h);
                                Balance(h);
                            }
                            else {
                                next.parent.right=null;
                                next.parent=null;
                                update(h);
                                Balance(h);

                            }
                        } else if (next.left != null && next.right == null) {
                            if(next==next.parent.left){
                                next.parent.left=next.left;
                                next.left.parent=next.parent;
                                next.left =null;
                                next.parent=null;
                                update(h);
                                Balance(h);
                            }
                            else{
                                next.parent.right=next.left;
                                next.left.parent=next.parent;
                                next.left=null;
                                next.parent=null;
                                update(h);
                                Balance(h);
                            }
                        } else if (next.left == null && next.right != null) {
                            if(next==next.parent.left){
                                next.parent.left=next.right;
                                next.right.parent=next.parent;
                                next.right =null;
                                next.parent=null;
                                update(h);
                                Balance(h);
                            }
                            else {
                                next.parent.right=next.right;
                                next.right.parent=next.parent;
                                next.right=null;
                                next.parent=null;
                                update(h);
                                Balance(h);
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
            AVLTree current = this;
            AVLTree pointer = current.parent;
            while (pointer.parent != null) {
                current = pointer;
                if (pointer.parent != null) {
                    pointer = pointer.parent;
                }
            }
            AVLTree found = look1(e, current);
            if (found != null) {
                AVLTree p=found.parent;
                if (found.left == null && found.right == null) {
                    if (found == found.parent.left) {
                        found.parent.left = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    } else {
                        found.parent.right = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    }
                }else if (found.left != null && found.right == null) {
                    if (found == found.parent.left) {
                        found.parent.left = found.left;
                        found.left.parent = found.parent;
                        found.left = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    } else {
                        found.parent.right = found.left;
                        found.left.parent = found.parent;
                        found.left = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    }
                } else if (found.left == null && found.right != null) {
                    if (found == found.parent.left) {
                        found.parent.left = found.right;
                        found.right.parent = found.parent;
                        found.right = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    } else {
                        found.parent.right = found.right;
                        found.right.parent = found.parent;
                        found.right = null;
                        found.parent = null;
                        update(p);
                        Balance(p);
                    }
                } else {
                    AVLTree next = min(found.right);
                    found.key = next.key;
                    found.address=next.address;
                    found.size=next.size;
                    AVLTree h= next.parent;
                    if (next.left == null && next.right == null) {
                        if (next == next.parent.left) {
                            next.parent.left = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                        else {
                            next.parent.right = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                    }
                    else if (next.left != null && next.right == null) {
                        if (next == next.parent.left) {
                            next.parent.left = next.left;
                            next.left.parent = next.parent;
                            next.left = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                        else {
                            next.parent.right = next.left;
                            next.left.parent = next.parent;
                            next.left = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                    }
                    else if (next.left == null && next.right != null) {
                        if (next == next.parent.left) {
                            next.parent.left = next.right;
                            next.right.parent = next.parent;
                            next.right = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                        else {
                            next.parent.right = next.right;
                            next.right.parent = next.parent;
                            next.right = null;
                            next.parent = null;
                            update(h);
                            Balance(h);
                        }
                    }
                }
            }
            return true;
        }
    }
        
    public AVLTree Find(int k, boolean exact)
    {
        if(exact==true) {
            if (this.parent == null) {
                if (this.right == null) {
                    return null;
                } else {
                    AVLTree current=this.right;
                    AVLTree found= look(current,k);
                    if(found!=null){
                        return found;
                    }
                    else {
                        return null;
                    }
                }
            } else {
                AVLTree current = this;
                AVLTree pointer= current.parent;
                while (pointer.parent!=null){

                    if(pointer.parent!=null){
                        current=pointer;
                        pointer = pointer.parent;
                    }
                }
                AVLTree found= look(current,k);
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
                    AVLTree current=this.right;
                    AVLTree node= null;
                    while(current!=null) {
                        if(current.key < k) {
                            current = current.right;
                        }
                        else if(current.key == k){
                            node=current;
                            while(current!=null && current.left != null) {
                                if(current.left.key==k) {
                                    current = current.left;
                                    node=current;
                                }
                                else if(current.left.right!=null && current.left.right.key==k ){
                                    current=current.left.right;
                                    node=current;
                                }
                                else {
                                    current=current.left;
                                }
                            }
                            break;
                        }
                        else{
                            node=current;
                            current=current.left;

                        }
                    }
                    return node;
                }
            }
            else {
                AVLTree current = this;
                AVLTree pointer= current.parent;
                while (pointer.parent!=null){

                    if(pointer.parent!=null){
                        current=pointer;
                        pointer = pointer.parent;
                    }
                }
                AVLTree node= null;
                while(current!=null) {
                    if(current.key < k) {
                        current = current.right;
                    }
                    else if(current.key == k){
                        node=current;
                        while(current!=null && current.left != null) {
                            if(current.left.key==k) {
                                current = current.left;
                                node=current;
                            }
                            else if(current.left.right!=null && current.left.right.key==k ){
                                current=current.left.right;
                                node=current;
                            }
                            else {
                                current=current.left;
                            }
                        }
                        break;
                    }
                    else{
                        node=current;
                        current=current.left;

                    }
                }
                return node;
            }
        }
    }

    public AVLTree getFirst()
    { if (this.parent == null) {
        if (this.right == null) {
            return null;
        } else {
            AVLTree current = this.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
    } else {
        AVLTree current = this;
        AVLTree pointer= current.parent;
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

    public AVLTree getNext()
    { if(this.parent==null){
            return null;
        }
        else {
            AVLTree current = this;
            if(current.right!=null){
                return min(current.right);
            }
            else {
                AVLTree pointer= current.parent;
                AVLTree value= current;
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
    private AVLTree min( AVLTree node) {
        if (node == null) {
            return null;
        } else {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }
    private AVLTree look(AVLTree node,int val){
        AVLTree current=null;
        if(node==null){
            return null;
        }
        else if(node.key>val){
            if(node.left!=null) {
                return look(node.left, val);
            }
            else
                return null;
        }
        else if(node.key==val){
            current= node;
            while(node!=null && node.left != null) {
                if(node.left.key==val) {
                    node =node.left;
                    current=node;
                }
                else if(node.left.right!=null && node.left.right.key==val ){
                    node=node.left.right;
                    current=node;
                }
                else {
                    node=node.left;
                }
            }
            return current;
        }
        else{
            if(node.right!=null) {
                return look(node.right, val);
            }
            else
                return null;
        }
    }
    private int check(AVLTree node){
        if(node==null){
            return 0;
        }
        else {
            return (height(node.left) - height(node.right));
        }
    }
    private int maxm(int h,int k){
        if(h>k){
            return h;
        }
        else {
            return k;
        }
    }
    private AVLTree leftrot(AVLTree node){
        AVLTree temp1= node.right;
        AVLTree temp2=temp1.left;
        temp1.left=node;
        temp1.parent=node.parent;
        if (node==node.parent.right){
            node.parent.right=temp1;
        }else{
            node.parent.left=temp1;
        }
        node.parent=temp1;
        node.right=temp2;
        if(temp2!=null) {
            temp2.parent = node;
        }
        node.height= maxm(height(node.left),height(node.right))+1;
        temp1.height= maxm(height(temp1.left),height(temp1.right))+1;
        return temp1;
    }
    private AVLTree rightrot(AVLTree node){
        AVLTree temp1= node.left;
        AVLTree temp2= temp1.right;
        temp1.right=node;
        temp1.parent=node.parent;
        if (node==node.parent.right){
            node.parent.right=temp1;
        }else{
            node.parent.left=temp1;
        }
        node.parent=temp1;
        node.left=temp2;
        if(temp2!=null) {
            temp2.parent = node;
        }
        node.height= maxm(height(node.left),height(node.right))+1;
        temp1.height= maxm(height(temp1.left),height(temp1.right))+1;
        return temp1;
    }
    private AVLTree look1(Dictionary d,AVLTree node){
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
    private void Balance(AVLTree current){
        while (current.key!=-1) {
            update(current);
            int diff = check(current);
            if (diff > 1 &&  check(current.left)>=0)
                rightrot(current);
            if (diff < -1 && check(current.right)<=0 )
                leftrot(current);
            if (diff > 1 && check(current.left)<0 ) {
                current.left = leftrot(current.left);
                rightrot(current);
            }
            if (diff < -1 && check(current.right)>0) {
                current.right = rightrot(current.right);
                leftrot(current);
            }
            current = current.parent;
        }
    }
    private int height(AVLTree node){
        if(node==null){
            return -1;
        }
        else {
            return node.height;
        }
    }
    private void update(AVLTree current){
        current.height= maxm(height(current.left),height(current.right))+1;
    }

    private boolean check2(AVLTree node){
        int min=0;
        if (node==null){
            return true;
        }
        else {
            if(!check2(node.left)){
                return false;
            }
            if(node.key<=min){
                return false;
            }
            min=node.key;
            return check2(node.right);
        }
    }
    private boolean heightbal(AVLTree node){
        if(node==null){
            return true;
        }
        else {
            int l=height(node.left);
            int r=height(node.right);
            if(((l-r)<=1 || (r-l)<=1) && heightbal(node.left) && heightbal(node.right)){
                return true;
            }
        }
        return false;
    }
    public boolean sanity()
    {   if (this.parent==null){
        if(this.right==null){
            return true;
        }
        else {
            AVLTree current = this.right;
            return (check2(current) && heightbal(current));
        }
    }
    else {
        AVLTree current= this;
        AVLTree pointer= current.parent;
        while (pointer.parent!=null){
            current=pointer;
            if(pointer.parent!=null){
                pointer = pointer.parent;
            }
        }
        return (check2(current) && heightbal(current));
    }
    }
}


