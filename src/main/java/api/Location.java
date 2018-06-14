package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

  @JsonProperty
  private String latitude;

  @JsonProperty
  private String longitude;

  @JsonProperty
  private String senderID;

  @JsonCreator
  public Location(@JsonProperty("lat") String latitude, @JsonProperty("long") String longitude, @JsonProperty("senderID") String sender) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.senderID = sender;
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
    return senderID;
  }

  public void setSender(String sender) {
    this.senderID = sender;
  }
}
