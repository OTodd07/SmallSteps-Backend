package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

  @JsonProperty
  private String latitude;

  @JsonProperty
  private String longitude;

  @JsonProperty
  private String sender;

  @JsonCreator
  public Location(@JsonProperty("lat") String latitude, @JsonProperty("long") String longitude, @JsonProperty("sender") String sender) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.sender = sender;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }
}
