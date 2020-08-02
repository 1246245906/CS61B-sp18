public class HarpHero {
    public static void main(String[] args) {
        synthesizer.HarpString[] hh = new synthesizer.HarpString[37];
        for (int i = 0; i < 37; i += 1) {
            hh[i] = new synthesizer.HarpString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if (key == keyboard.charAt(i)) {
                        hh[i].pluck();
                    }
                }
            }

            double sample = 0.0;
            for (int i = 0; i < hh.length; i += 1) {
                sample += hh[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < hh.length; i += 1) {
                hh[i].tic();
            }
        }
    }
}
