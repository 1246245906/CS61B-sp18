/*
 * xxPos: Planet's current x position
 * yyPos: Planet's current y position
 * xxVel: Planet's current velocity in the x direction
 * yyVel: Planet's current velocity in the y direction
 * mass: Planet's mass
 * imgFileName: The name of the file that corresponds to the image that depicts the planet
 */
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
				 double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/*
	 * @param p 	planet p
	 * @return 		the distance between this planet and planet p
	 */
	public double calcDistance(Planet p){
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}

	/*
	 * @param p		planet p
	 * @return 		the force between this planet and planet p
	 */
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double G = 6.67e-11;
		double force = (G*mass*p.mass)/(distance*distance);
		return force;
	}

	/*
	 * @param p		planet p
	 * @return 		the force in the x direction which is exerted by planet p
	 */
	public double calcForceExertedByX(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dx = p.xxPos - xxPos;

		double forceX = (force*dx)/distance;
		return forceX;
	}

	/*
	 * @param p		planet p
	 * @return 		the force in the y direction which is exerted by planet p
	 */
	public double calcForceExertedByY(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dy = p.yyPos - yyPos;

		double forceY = (force*dy)/distance;
		return forceY;
	}

	/*
	 * @param p		whole planets in university
	 * @return 		the force in the x direction which is exerted by all planet
	 */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netForceX = 0;
		for(Planet s : allPlanets){
			if(this.equals(s)){
				continue;
			}
			netForceX += this.calcForceExertedByX(s);
		}
		return netForceX;
	}

	/*
	 * @param p		whole planets in university
	 * @return 		the force in the y direction which is exerted by all planets
	 */
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netForceY = 0;
		for(Planet s : allPlanets){
			if(this.equals(s)){
				continue;
			}
			netForceY += this.calcForceExertedByY(s);
		}
		return netForceY;
	}

	/*
	 * @param dtime 	update planet status every dtime
	 * @param xForce 	whole force in the x direction
	 * @param yForce 	whole force in the x direction
	 */
	public void update(double dtime, double xForce, double yForce){
		double xAcceleration = xForce/mass;
		double yAcceleration = yForce/mass;
		xxVel += xAcceleration*dtime;
		yyVel += yAcceleration*dtime;
		xxPos = xxPos + xxVel*dtime;
		yyPos = yyPos + yyVel*dtime;
	}

	/*
	 * draw current position in universi
	 */
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
	}
}