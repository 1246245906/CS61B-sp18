public class HarpHero {
    public static void main(String[] args) {
        synthesizer.HarpString[] GS = new synthesizer.HarpString[37];
        for (int i = 0; i < 37; i += 1) {
            GS[i] = new synthesizer.HarpString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if(key == keyboard.charAt(i)) {
                        GS[i].pluck();
                    }
                }
            }

            double sample = 0.0;
            for (int i = 0; i < GS.length; i += 1) {
                sample += GS[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < GS.length; i += 1) {
                GS[i].tic();
            }
        }
    }
}
