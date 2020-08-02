/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {

    public static void main(String[] args) {
        synthesizer.GuitarString[] gs = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i += 1) {
            gs[i] = new synthesizer.GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if (key == keyboard.charAt(i)) {
                        gs[i].pluck();
                    }
                }
            }

            double sample = 0.0;
            for (int i = 0; i < gs.length; i += 1) {
                sample += gs[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < gs.length; i += 1) {
                gs[i].tic();
            }
        }
    }
}

