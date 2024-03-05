import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.io.File;
import java.time.temporal.ChronoUnit;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Manus McFadden
 */
public class Track {
  //attributes
  private Point[] points;
  // TODO: Create a stub for the constructor
  public Track() {this.points = new Point[0];}
  public Track(String filename) {
    try {
      readFile(filename);
    }
    catch (IOException e) {
      System.out.println("Could not read file");
    }
  }
  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException {
    File file = new File(filename);
    Scanner input = new Scanner(file);
    points = new Point[0];
    while (input.hasNextLine()){
      String line = input.nextLine();
      String[] record = line.split(",");
      if (record.length < 4){
        throw new GPSException("Less than 4 values");
      }
      if (!Objects.equals(record[0], "Time")){
        Point object = new Point(ZonedDateTime.parse(record[0]), Double.parseDouble(record[1]), Double.parseDouble(record[2]), Double.parseDouble(record[3]));
        add (object);
      }
    }
    input.close();
  }
  // TODO: Create a stub for add()
  public void add(Point point) {
    this.points = Arrays.copyOf(this.points, this.points.length + 1);
    this.points[this.points.length - 1] = point;
  }
  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index > size() - 1 || index < 0 || size() == 0){throw new GPSException("Could not get point out of range");}
    else{return this.points[index];}
  }
  // TODO: Create a stub for size()
  public int size() {return this.points.length;}
  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {
    if (size() <= 0) {throw new GPSException("No values in track");}
    else {
      double comp = get(0).getElevation();
      int index = 0;
      for (int i = 0; i < size() - 1; i++) {
        if (comp > get(i).getElevation()) {
        comp = get(i).getElevation();
        index = i;
        }
      }
      return get(index);
    }
  }
  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    if (size() <= 0) {throw new GPSException("No values in track");}
    else {
      double comp = get(0).getElevation();
      int index = 0;
      for (int i = 0; i < size() - 1; i++) {
        if (comp < get(i).getElevation()) {
        comp = get(i).getElevation();
        index = i;
        }
      }
      return get(index);
    }
  }
  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    if(size() < 2) {
      throw new GPSException("Less than 2 points in track");
    }
    else {
      double total = 0;
      for (int i = 0; i < size() - 1; i++) {
        total += Point.greatCircleDistance(get(i),get(i + 1));
      }
      return total;
    }
  }
  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    long totalTime = ChronoUnit.SECONDS.between(get(0).getTime(), get(size()-1).getTime());
    double speed = this.totalDistance() / totalTime;
    return speed;
  }
}
