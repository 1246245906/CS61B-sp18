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
}