package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

  @JsonProperty
  private String latitude;

  @JsonProperty
  private String longitude;

  @JsonCreator
  public Location(@JsonProperty("lat") String latitude, @JsonProperty("long") String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

}
