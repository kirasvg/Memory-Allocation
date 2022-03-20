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
            if (d.key==blockSize) {
                freeBlk.Delete(d);
                allocBlk.Insert(d.address,blockSize,d.address);
                return d.address;
            }
            else {
                freeBlk.Delete(d);
                freeBlk.Insert(d.address+blockSize,d.size-blockSize,d.size-blockSize);
                allocBlk.Insert(d.address,blockSize,d.address);
                return d.address;
            }
        }
        else {
            return -1;
        }
    }
    public void Defragment() {
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
                t.Delete(s);
                freeBlk.Delete(u);
                t.Delete(y);
                freeBlk.Delete(i);
                t.Insert(s.address, s.size + y.size, s.address);
                freeBlk.Insert(s.address, s.size + y.size, s.size + y.size);
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



