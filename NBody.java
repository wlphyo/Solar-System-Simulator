import java.io.*;
import javax.sound.sampled.*;
public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int skip =  in.readInt();
		return in.readDouble();
	}
	public static Body[] readBodies(String fileName){
		Body[] myBodyArray = new Body[5];
		In in = new In(fileName);
		int skip1 = in.readInt();
		double skip2 = in.readDouble();
		double xpos,ypos,xVel,yVel,m;
		String imgName;
		for(int i = 0; i<5;++i){
			xpos = in.readDouble();
			ypos =in.readDouble();
			xVel =in.readDouble();
			yVel =in.readDouble();
			m =in.readDouble();
			imgName = in.readString();
			myBodyArray[i] = new Body(xpos,ypos,xVel,yVel,m,imgName);
		}
		return myBodyArray;
	}
	public static void main(String[] args){
		StdAudio.play("audio/2001.mid");
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies;
		bodies = readBodies(filename);
		String imageToDraw = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);
		double[] xForces = new double[5];
		double[] yForces = new double[5];
		for(int time=0; time < T; time+=dt) {
			for(int i =0;i<bodies.length;++i)	{
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int j= 0; j<bodies.length;++j)	bodies[j].update(time,xForces[j],yForces[j]);
			StdDraw.clear();
			StdDraw.picture(0,0,imageToDraw);
			for(int k = 0 ; k < bodies.length; ++k) {
				bodies[k].draw();
				StdDraw.show();
				StdDraw.pause(10);
			}
			
			
			StdOut.printf("%d\n", bodies.length);
			StdOut.printf("%.2e\n", radius);
			
			for (int i = 0; i < bodies.length; i++) {
   				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
			}
		}
	}
}