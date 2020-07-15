import java.lang.Math;

public class Body{
	double xxPos, yyPos, xxVel, yyVel, mass;
	String imgFileName;
	public static double G = 6.67 * Math.pow(10,-11);
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
			xxPos = xP;
			yyPos = yP;
			xxVel = xV;
			yyVel = yV;
			mass = m;
			imgFileName = img;


	}
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body that){
		double x = xxPos - that.xxPos;
		double y = yyPos - that.yyPos;
		return Math.sqrt((x*x)+(y*y));
	}
	public double calcForceExertedBy(Body that){
		return ((G*mass*that.mass)/(Math.pow(calcDistance(that),2)));
	}
	public double calcForceExertedByX(Body that){
		return (calcForceExertedBy(that)*(that.xxPos-xxPos)/ calcDistance(that));
	}
	public double calcForceExertedByY(Body that){
		return (calcForceExertedBy(that)*(that.yyPos-yyPos)/ calcDistance(that));
	}
	public double calcNetForceExertedByX(Body[] arrX){
		double res = 0;
		for(int i = 0; i < arrX.length;i++){
			if(this.equals(arrX[i])) continue;
			res += calcForceExertedByX(arrX[i]);
		}
		return res;
	}
	public double calcNetForceExertedByY(Body[] arrY){
		double res = 0;
		for(int i = 0; i < arrY.length;i++){
			if(this.equals(arrY[i])) continue;
			res += calcForceExertedByY(arrY[i]);
		}
		return res;
	}
	public void update(double t,double fx,double fy){  //time, x force, y force
		//a = f_net/m
		//velocity = vx+time*ax , vy +time*ay
		xxVel =xxVel+ t*(fx/mass);
		yyVel =yyVel +t*(fy/mass);
		//position = px+t*vc , py+t* vy
		xxPos = xxPos+t*xxVel;
		yyPos = yyPos+t*yyVel;

	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
		StdDraw.show();
		//StdDraw.pause(2000);
	}

}

