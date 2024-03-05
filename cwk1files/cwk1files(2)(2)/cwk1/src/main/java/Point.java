import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Manus McFadden
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  //attributes
  private ZonedDateTime time;
  private double longitude;
  private double latitude;
  private double elevation;
  // TODO: Create a stub for the constructor
  public Point(ZonedDateTime t, double lon, double lat, double elev) {
    this.time = t;

    if (lon >= MIN_LONGITUDE && lon <= MAX_LONGITUDE) {
      this.longitude = lon;
    }
    else {
      throw new GPSException("Invalid longitude");
    }
    if (lat >= MIN_LATITUDE && lat <= MAX_LATITUDE) {
      this.latitude = lat;
    }
    else {
      throw new GPSException("Invalid latitude");
    }
    this.elevation = elev;
  }
  // TODO: Create a stub for getTime()
  public ZonedDateTime getTime() {return this.time; }
  // TODO: Create a stub for getLatitude()
  public double getLatitude() {return this.latitude;}
  // TODO: Create a stub for getLongitude()
  public double getLongitude() {return this.longitude; }
  // TODO: Create a stub for getElevation()
  public double getElevation() {return this.elevation; }
  // TODO: Create a stub for toString()
  public String toString() {
    String strLat = String.format("%.5f", this.latitude);
    String strLong = String.format("%.5f", this.longitude);
    String strElev = String.format("%.1f", this.elevation);
    String strOut =  "(" + strLong + ", " + strLat + "), " + strElev + " m";
    return strOut;}
    
  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
