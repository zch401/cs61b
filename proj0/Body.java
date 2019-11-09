public class Body {
  public double xxPos; //current x position
  public double yyPos; //current y position
  public double xxVel; //current velocity in the x direction
  public double yyVel; //current velocity in the y direction
  public double mass;  //mass
  public String imgFileName; //name of the file that corresponds to the image
  public static double G = 6.67*Math.pow(10,-11); //set gravitational constant

  public Body (double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body (Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  /** calculate distance*/
  public double calcDistance (Body b) {
    double dx = this.xxPos-b.xxPos; //x direction distance
    double dy = this.yyPos-b.yyPos; //y direction distance
    double r = Math.sqrt(dx*dx+dy*dy);
    return r;
  }

  /** calculate force*/
  public double calcForceExertedBy (Body b) {
    double r = calcDistance(b);
    double F = G*this.mass*b.mass/(r*r);
    return F;
  }

  /** calculate x direction force*/
  public double calcForceExertedByX (Body b) {
    double r = calcDistance(b);
    double F = calcForceExertedBy(b);
    double dx = b.xxPos-this.xxPos; //x direction distance
    double Fx = dx*F/r;
    return Fx;
  }

  /** calculate y direction force*/
  public double calcForceExertedByY (Body b) {
    double r = calcDistance(b);
    double F = calcForceExertedBy(b);
    double dy = b.yyPos-this.yyPos; //y direction distance
    double Fy = dy*F/r;
    return Fy;
  }

  /** calculate x direction net force*/
  public double calcNetForceExertedByX (Body[] b) {
    int num = b.length; //number of bodies
    double sum = 0;
    for (int i=0;i<num ;i++ ) {

      if (this.equals(b[i])) {
        continue; //no gravitational force on self
      }

      sum += calcForceExertedByX(b[i]);

    }
    return sum;
  }

  /** calculate y direction net force*/
  public double calcNetForceExertedByY (Body[] b) {
    int num = b.length; //number of bodies
    double sum = 0;
    for (int i=0;i<num ;i++ ) {

      if (this.equals(b[i])) {
        continue; //no gravitational force on self
      }

      sum += calcForceExertedByY(b[i]);

    }
    return sum;
  }

  /** body movement after force*/
  public void update (double dt, double fX, double fY) {
    double a_net_x = fX/this.mass;
    double a_net_y = fY/this.mass;

    double v_new_x = this.xxVel+dt*a_net_x;
    double v_new_y = this.yyVel+dt*a_net_y;

    double p_new_x = this.xxPos+dt*v_new_x;
    double p_new_y = this.yyPos+dt*v_new_y;

    /** update velocity*/
    this.xxVel = v_new_x;
    this.yyVel = v_new_y;

    /** update position*/
    this.xxPos = p_new_x;
    this.yyPos = p_new_y;
  }

  /** draw body*/
  public void draw() {
    String imageToDraw = "./images/"+this.imgFileName;
    StdDraw.picture(xxPos,yyPos,imageToDraw);
  }

}
