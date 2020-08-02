package synthesizer;

public class HarpString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .985; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public HarpString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency) * 2);
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
        buffer.enqueue(-((buffer.dequeue() + buffer.peek()) / 2) * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
