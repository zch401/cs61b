public class NBody {

  /** read the radius of the universe*/
  public static double readRadius (String inF) {
    In in = new In(inF);
    int num = in.readInt(); //number of planets
    double radius = in.readDouble(); //universe radius
    return radius;
  }

  /** read body info from file*/
  public static  Body[] readBodies (String inF) {
    In in = new In(inF);
    int num = in.readInt(); //number of planets
    Body[] Bodies = new Body[num];
    double rd = in.readDouble(); //universe radius

    int i;
    for (i=0;i<num ;i++ ) {
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();
      Bodies[i] = new Body(xPos,yPos,xVel,yVel,m,img);
    }

    return Bodies;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Body[] Bodies = readBodies(filename);

    String imageToDraw = "./images/starfield.jpg";

    double R = readRadius(filename); //universe radius
    StdDraw.setScale(-R,R);
    StdDraw.clear();
    StdDraw.picture(0,0,imageToDraw); //draw background

    for (int i = 0;i<Bodies.length ;i++ ) {
      Bodies[i].draw(); //draw each body
    }

    StdDraw.enableDoubleBuffering();

    double time;
    double[] xForces = new double[Bodies.length];
    double[] yForces = new double[Bodies.length];
    for (time = 0;time<=T ;time=time+dt ) {
      for (int j=0;j<Bodies.length ;j++ ) {
        xForces[j] = Bodies[j].calcNetForceExertedByX(Bodies);
        yForces[j] = Bodies[j].calcNetForceExertedByY(Bodies);
      }

      StdDraw.picture(0,0,imageToDraw); //draw background

      for (int j=0;j<Bodies.length ;j++ ) {
        Bodies[j].update(dt,xForces[j],yForces[j]);
        Bodies[j].draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

    }

    StdOut.printf("%d\n", Bodies.length);
    StdOut.printf("%.2e\n", R);
    for (int i = 0; i < Bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
      Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
}



  }

}
