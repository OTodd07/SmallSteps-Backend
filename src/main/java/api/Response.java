package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

  @JsonProperty
  private boolean response;

  @JsonProperty
  private String latitude;

  @JsonProperty
  private String longitude;

  @JsonProperty
  private String confluenceLong;

  @JsonProperty
  private  String confluenceLat;

  @JsonCreator
  public Response(@JsonProperty("response") boolean response, @JsonProperty("latitude") String latitude,
                  @JsonProperty("longitude") String longitude, @JsonProperty("confluenceLong") String confluenceLong,
                  @JsonProperty("confluenceLat") String confluenceLat) {
    this.response = response;
    this.latitude = latitude;
    this.longitude = longitude;
    this.confluenceLat = confluenceLat;
    this.confluenceLong = confluenceLong;
  }

  public boolean isResponse() {
    return response;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public void setResponse(boolean response) {
    this.response = response;
  }

  public String getConfluenceLong() {
    return confluenceLong;
  }

  public void setConfluenceLong(String confluenceLong) {
    this.confluenceLong = confluenceLong;
  }

  public String getConfluenceLat() {
    return confluenceLat;
  }

  public void setConfluenceLat(String confluenceLat) {
    this.confluenceLat = confluenceLat;
  }
}
