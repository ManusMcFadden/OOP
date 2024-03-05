/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Manus McFadden
 */
public class TrackInfo {
  public static void main(String[] args) {
    // TODO: Implementation TrackInfo application here
    if (args.length != 1) {
      System.out.println("Usage: java TrackInfo <filename>");
      System.exit(0);
    }
    try {
      Track track1 = new Track(args[0]);
      System.out.printf("%d points in track\n", track1.size());
      System.out.printf("Lowest point is %s\n", track1.lowestPoint().toString());
      System.out.printf("Highest point is %s\n", track1.highestPoint().toString());
      System.out.printf("Total distance = %.3f km\n", track1.totalDistance()/1000);
      System.out.printf("Average speed = %.3f m/s\n", track1.averageSpeed());
    }
    catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
      System.exit(1);
    }
  }
}
