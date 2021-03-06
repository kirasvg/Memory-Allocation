
// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).
public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize){
        if(blockSize<=0){
            return -1;
        }
        Dictionary d = freeBlk.Find(blockSize,false);
        if (d!=null){
           if (d.key==blockSize) {
               allocBlk.Insert(d.address,blockSize,d.address);
               freeBlk.Delete(d);
               return d.address;
           }
           else {
               freeBlk.Insert(d.address+blockSize,d.size-blockSize,d.size-blockSize);
               allocBlk.Insert(d.address,blockSize,d.address);
               freeBlk.Delete(d);
               return d.address;
           }
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        if(startAddr<0){
            return -1;
        }
        Dictionary d =allocBlk.Find(startAddr,true);
        if(d!=null){
            freeBlk.Insert(d.address,d.size,d.size);
            allocBlk.Delete(d);
            return 0;
        }
        return -1;
    }
}
