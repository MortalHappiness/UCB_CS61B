public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  static final double G = 6.67e-11;

  public Body(double xP, double yP, double xV, double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  public Body(Body b) {
    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }

  public double calcDistance(Body other) {
    return Math.sqrt(Math.pow(this.xxPos - other.xxPos, 2) + Math.pow(this.yyPos - other.yyPos, 2));
  }

  public double calcForceExertedBy(Body other) {
    return (G * this.mass * other.mass) / (Math.pow(this.calcDistance(other), 2));
  }

  public double calcForceExertedByX(Body other) {
    return (this.calcForceExertedBy(other)
        * (other.xxPos - this.xxPos)
        / (this.calcDistance(other)));
  }

  public double calcForceExertedByY(Body other) {
    return (this.calcForceExertedBy(other)
        * (other.yyPos - this.yyPos)
        / (this.calcDistance(other)));
  }

  public double calcNetForceExertedByX(Body[] others) {
    double ans = 0.0;
    for (Body other : others) {
      if (other.equals(this)) continue;
      ans += this.calcForceExertedByX(other);
    }
    return ans;
  }

  public double calcNetForceExertedByY(Body[] others) {
    double ans = 0.0;
    for (Body other : others) {
      if (other.equals(this)) continue;
      ans += this.calcForceExertedByY(other);
    }
    return ans;
  }

  public void update(double dt, double fX, double fY) {
    double aX = fX / this.mass;
    double aY = fY / this.mass;
    this.xxVel += dt * aX;
    this.yyVel += dt * aY;
    this.xxPos += dt * this.xxVel;
    this.yyPos += dt * this.yyVel;
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
  }
}
