package synthesizer;

//import edu.princeton.cs.algs4.In;

import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(rb, first, temp, 0, capacity/2 - first);
        System.arraycopy(rb, 0, temp, capacity/2 - first, last);
        rb = temp;
        first = 0;
        last = fillCount;
        this.capacity = capacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        private int count;

        public ArrayRingBufferIterator() {
            wizPos = first;
            count = 0;
        }

        public boolean hasNext() {
            return count < fillCount;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos = (wizPos + 1) % capacity;
            count += 1;
            return returnItem;
        }
    }

    public void enqueue(T x) {
        if (isFull()) {
//            resize(capacity * 2);
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T item = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Integer> aset = new ArrayRingBuffer<Integer>(4);
        aset.enqueue(5);
        aset.enqueue(23);
        aset.enqueue(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
