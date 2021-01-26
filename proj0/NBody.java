public class NBody {
  public static double readRadius(String filename) {
    In in = new In(filename);
    in.readInt();
    return in.readDouble();
  }

  public static Body[] readBodies(String filename) {
    In in = new In(filename);
    int n = in.readInt();
    in.readDouble();

    Body[] bodies = new Body[n];
    for (int i = 0; i < n; i++) {
      bodies[i] =
          new Body(
              in.readDouble(),
              in.readDouble(),
              in.readDouble(),
              in.readDouble(),
              in.readDouble(),
              in.readString());
    }
    return bodies;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radius = NBody.readRadius(filename);
    Body[] bodies = NBody.readBodies(filename);
    int n = bodies.length;

    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-radius, radius);

    double t = 0;
    double xForces[] = new double[n];
    double yForces[] = new double[n];
    while (t < T) {
      for (int i = 0; i < n; ++i) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }

      for (int i = 0; i < n; ++i) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.clear();
      StdDraw.picture(0, 0, "./images/starfield.jpg");
      for (Body body : bodies) {
        body.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);

      t += dt;
    }

    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
      StdOut.printf(
          "%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          bodies[i].xxPos,
          bodies[i].yyPos,
          bodies[i].xxVel,
          bodies[i].yyVel,
          bodies[i].mass,
          bodies[i].imgFileName);
    }
  }
}
