/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
//    private static final double CONCERT_A = 440.0;
//    private static final double CONCERT_A_sharp = CONCERT_A * Math.pow(2, 1.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
//        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
//        synthesizer.GuitarString stringB = new synthesizer.GuitarString(CONCERT_B);
        synthesizer.GuitarString[] GS = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i += 1) {
            GS[i] = new synthesizer.GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if(key == keyboard.charAt(i)) {
                        GS[i].pluck();
                    }
                }
            }

//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                if (key == 'a') {
//                    stringA.pluck();
//                } else if (key == 'c') {
//                    stringC.pluck();
//            }

        /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample() + stringB.sample() + stringD.sample()
//                    + stringE.sample() + stringF.sample() + stringG.sample();
            double sample = 0.0;
            for (int i = 0; i < GS.length; i += 1) {
                sample += GS[i].sample();
            }

                    /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringB.tic();
            for (int i = 0; i < GS.length; i += 1) {
                GS[i].tic();
            }
        }
    }
}

