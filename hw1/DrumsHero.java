public class DrumsHero {

    public static void main(String[] args) {
        synthesizer.DrumsString[] dh = new synthesizer.DrumsString[37];
        for (int i = 0; i < 37; i += 1) {
            dh[i] = new synthesizer.DrumsString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if (key == keyboard.charAt(i)) {
                        dh[i].pluck();
                    }
                }
            }

            double sample = 0.0;
            for (int i = 0; i < dh.length; i += 1) {
                sample += dh[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < dh.length; i += 1) {
                dh[i].tic();
            }
        }
    }
}
