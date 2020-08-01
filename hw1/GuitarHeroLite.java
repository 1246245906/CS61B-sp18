/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_A_sharp = CONCERT_A * Math.pow(2, 1.0 / 12.0);
    private static final double CONCERT_B = CONCERT_A * Math.pow(2, 2.0 / 12.0);
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final double CONCERT_C_sharp = CONCERT_A * Math.pow(2, 4.0 / 12.0);
    private static final double CONCERT_D = CONCERT_A * Math.pow(2, 5.0 / 12.0);
    private static final double CONCERT_D_sharp = CONCERT_A * Math.pow(2, 6.0 / 12.0);
    private static final double CONCERT_E = CONCERT_A * Math.pow(2, 7.0 / 12.0);
    private static final double CONCERT_F = CONCERT_A * Math.pow(2, 8.0 / 12.0);
    private static final double CONCERT_F_sharp = CONCERT_A * Math.pow(2, 9.0 / 12.0);
    private static final double CONCERT_G = CONCERT_A * Math.pow(2, 10.0 / 12.0);
    private static final double CONCERT_G_sharp = CONCERT_A * Math.pow(2, 11.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringB = new synthesizer.GuitarString(CONCERT_B);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
        synthesizer.GuitarString stringD = new synthesizer.GuitarString(CONCERT_D);
        synthesizer.GuitarString stringE = new synthesizer.GuitarString(CONCERT_E);
        synthesizer.GuitarString stringF = new synthesizer.GuitarString(CONCERT_F);
        synthesizer.GuitarString stringG = new synthesizer.GuitarString(CONCERT_G);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                } else if (key == 'd') {
                    stringD.pluck();
                } else if (key == 'e') {
                    stringE.pluck();
                } else if (key == 'f') {
                    stringF.pluck();
                } else if (key == 'g') {
                    stringG.pluck();
                } else if (key == 'b') {
                    stringB.pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample() + stringB.sample() + stringD.sample()
                    + stringE.sample() + stringF.sample() + stringG.sample();

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringB.tic();
            stringC.tic();
            stringD.tic();
            stringE.tic();
            stringF.tic();
            stringG.tic();
        }
    }
}

