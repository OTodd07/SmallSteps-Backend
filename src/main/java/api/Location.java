package api;

public class Location {
  private String latitude;
  private String longitude;
  private String radius;

  public Location(String latitude, String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.radius = "1.5";
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getRadius() {
    return radius;
  }
}
