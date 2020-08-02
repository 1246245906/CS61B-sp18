package synthesizer;

import java.util.Map;

public class DrumsString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public DrumsString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int)Math.round(SR / frequency));
        while (!buffer.isFull()) {
            buffer.enqueue(.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double a = Math.random();
        if (a > 0.5) {
            buffer.enqueue(-((buffer.dequeue() + buffer.peek()) / 2) * DECAY);
        }else {
            buffer.enqueue(((buffer.dequeue() + buffer.peek()) / 2) * DECAY);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}
