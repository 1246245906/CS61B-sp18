public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path){
		In in = new In(path);

		int n = in.readInt();
		double readius = in.readDouble();
		Planet[] planets = new Planet[n];

		/**
		 * Can we only instantiate like this???
		 * Look like so stupid!!!
		 */
		for(int i = 0; i < n; i++){
			planets[i] = new Planet();
		}

		for(int i = 0; i < n; i++){
			planets[i].xxPos = in.readDouble();
			planets[i].yyPos = in.readDouble();
			planets[i].xxVel = in.readDouble();
			planets[i].yyVel = in.readDouble();
			planets[i].mass = in.readDouble();
			planets[i].imgFileName = in.readString();
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();

		String starfieldLoc = "images/starfield.jpg";
		StdDraw.picture(0, 0, starfieldLoc);
		StdDraw.show();

		for(Planet p : planets){
			p.draw();
		}

		StdDraw.enableDoubleBuffering();

		double currenttime = 0;
		while(currenttime <= T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for(int i = 0; i < planets.length; ++i){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; ++i){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, starfieldLoc);

			for(Planet p : planets){
				p.draw();
			}

			StdDraw.show();

			StdDraw.pause(10);

			currenttime += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
           	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}