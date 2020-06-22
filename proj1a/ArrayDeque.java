public class ArrayDeque<T>{
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public ArrayDeque(){
        capacity = 8;
        items = (T []) new Object[capacity];
        head = 0;
        tail = 0;
    }

    public boolean isFull(){
        return size == capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isTooSmall(){
        float a = size;
        float b = capacity;
        return a/b <= 0.25;
    }

    private void increaseCapacity(int oldCapacity){
        capacity = oldCapacity * 2;
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, tail, a, 0, size-tail);
        System.arraycopy(items, 0, a,size-tail, head);
        items = a;
        head = size;
        tail = 0;
    }

    private void decreaseCapacity(int oldCapacity){
        capacity = oldCapacity / 2;
        T[] a = (T []) new Object[capacity];
        if(head > tail){
            System.arraycopy(items, tail, a, 0, head - tail);
        }else{
            System.arraycopy(items, tail, a, 0, oldCapacity-tail);
            System.arraycopy(items, 0, a,oldCapacity-tail, head);
        }
        items = a;
        head = size;
        tail = 0;
    }

    public int size(){
        return size;
    }

    public void addLast(T e){
        if(isFull()){
            increaseCapacity(capacity);
        }
        items[head] = e;
        head = (head+1)%capacity;
        ++size;
    }

    public void addFirst(T e){
        if(isFull()){
            increaseCapacity(capacity);
        }
        tail = (tail - 1 +capacity)%capacity;
        items[tail] = e;                        // TODO boundry decision
        ++size;
    }

    public T removeFirst(){
        if(isTooSmall()){
            decreaseCapacity(capacity);
        }
        T t = items[tail];
        tail = (tail+1) % capacity;
        --size;
        return t;
    }

    public T removeLast(){
        if(isTooSmall()){
            decreaseCapacity(capacity);
        }
        head = (head-1 + capacity) % capacity;
        T t = items[head];
        --size;
        return t;
    }

    public T get(int index){
        if(index >= size | index < 0){
            return null;
        }
        return items[(tail+index) % capacity];
    }

    public void printDeque(){
        for(int i = 0; i < size; ++i){
            System.out.print(items[(tail+i)%capacity] + " ");
        }
    }
}