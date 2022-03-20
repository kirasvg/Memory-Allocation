// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.
public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees.
    @Override
    public int Allocate(int blockSize){
        if(blockSize<=0){
            return -1;
        }
        Dictionary d = freeBlk.Find(blockSize,false);
        if (d!=null){
            int address= d.address;
            int size =d.size;
            if (d.key==blockSize) {
                freeBlk.Delete(d);
                allocBlk.Insert(address,blockSize,address);
                return address;
            }
            else {
                freeBlk.Delete(d);
                freeBlk.Insert(address+blockSize,size-blockSize,size-blockSize);
                allocBlk.Insert(address,blockSize,address);
                return address;
            }
        }
        else {
            return -1;
        }
    }
    public void Defragment() {
        if(type==2) {
            BSTree t = new BSTree();
            Dictionary d = freeBlk.getFirst();
            if (d == null) {
                return;
            }
            while (d != null) {
                t.Insert(d.address, d.size, d.address);
                d = d.getNext();
            }
            Dictionary s = t.getFirst();
            Dictionary y = s.getNext();
            while (y != null) {
                if ((s.address + s.size) == y.address) {
                    Dictionary u=new BSTree(s.address,s.size,s.size);
                    Dictionary i =new BSTree(y.address,y.size,y.size);
                    int sa= s.address;
                    int ss= s.size;
                    int ya=y.address;
                    int ys=y.size;
                    t.Delete(s);
                    freeBlk.Delete(u);
                    t.Delete(y);
                    freeBlk.Delete(i);
                    t.Insert(sa, ss+ ys, sa);
                    freeBlk.Insert(sa, ss + ys, ss + ys);
                    s = t.getFirst();
                    y = s.getNext();
                } else {
                    s = s.getNext();
                    y = y.getNext();
                }
                t= null;
                return;
            }
        }
        else if(type==3){
            AVLTree t = new AVLTree();
            Dictionary d = freeBlk.getFirst();
            if (d == null) {
                return;
            }
            while (d != null) {
                t.Insert(d.address, d.size, d.address);
                d = d.getNext();
            }
            Dictionary s = t.getFirst();
            Dictionary y = s.getNext();
            while (y != null) {
                if ((s.address + s.size) == y.address) {
                    Dictionary u=new BSTree(s.address,s.size,s.size);
                    Dictionary i =new BSTree(y.address,y.size,y.size);
                    int sa= s.address;
                    int ss= s.size;
                    int ya=y.address;
                    int ys=y.size;
                    t.Delete(s);
                    freeBlk.Delete(u);
                    t.Delete(y);
                    freeBlk.Delete(i);
                    t.Insert(sa, ss+ ys, sa);
                    freeBlk.Insert(sa, ss + ys, ss + ys);
                    s = t.getFirst();
                    y = s.getNext();
                } else {
                    s = s.getNext();
                    y = y.getNext();
                }
                t= null;
                return;
            }
        }
    }
}


