public class LinkedListDeque<T> {
    public class TNode{
        public T item;
        public TNode next;
        public TNode prev;

        public TNode(T i, TNode p, TNode n){
            item = i;
            next = n;
            prev = p;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public boolean isEmpty(){
//        if(size == 0){
//            return true;
//        }
//        return false;
        return (size == 0)?true:false;
    }

    public int size(){
        return size;
    }

    public void addFirst(T item){
        TNode ptr = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = ptr;
        sentinel.next = ptr;
        ++size;
    }

    public void addLast(T item){
        TNode ptr = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = ptr;
        sentinel.prev =ptr;
        ++size;
    }

    public void printDeque(){
        TNode ptr = sentinel.next;
        while(ptr != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        --size;
        TNode ptr = sentinel.next;
        sentinel.next = ptr.next;
        sentinel.next.prev = sentinel;
        T i = ptr.item;
        ptr = null;
        return i;
    }

    public  T removeLast(){
        if(isEmpty()){
            return null;
        }
        --size;
        TNode ptr = sentinel.prev;
        sentinel.prev = ptr.prev;
        sentinel.prev.next = sentinel;
        T i = ptr.item;
        ptr = null;
        return i;
    }

    public T get(int index){
        if(index <0 | index >= size){
            return null;
        }
        TNode ptr = sentinel.next;
        for(int i = 0; i < index; ++i){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(TNode p, int index){
        if(index == 0){
            return p.item;
        }
       return getRecursive(p.next, index-1);
    }

    public T getRecursive(int index){
        if(index <0 | index >= size){
            return null;
        }
        return getRecursive(sentinel.next, index);
    }
}