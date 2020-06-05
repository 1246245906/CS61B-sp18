public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	double mass;
	String imgFileName;

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

	public Planet(){
		xxPos = 0;
		yyPos = 0;
		xxVel = 0;
		yyVel = 0;
		mass = 0;
		imgFileName = "null";
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double G = 6.67e-11;
		double force = (G*mass*p.mass)/(distance*distance);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dx = p.xxPos - xxPos;

		double forceX = (force*dx)/distance;
		return forceX;
	}

	public double calcForceExertedByY(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double dy = p.yyPos - yyPos;

		double forceY = (force*dy)/distance;
		return forceY;
	}

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

	public void update(double dtime, double xForce, double yForce){
		double xAcceleration = xForce/mass;
		double yAcceleration = yForce/mass;
		xxVel += xAcceleration*dtime;
		yyVel += yAcceleration*dtime;
		xxPos = xxPos + xxVel*dtime;
		yyPos = yyPos + yyVel*dtime;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
	}
}