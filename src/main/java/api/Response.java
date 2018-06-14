package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

  @JsonProperty
  private boolean response;

  @JsonProperty
  private Location location;

  @JsonCreator
  public Response(@JsonProperty("response") boolean response, @JsonProperty("location") Location location) {
    this.response = response;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public boolean isResponse() {
    return response;
  }

  public void setResponse(boolean response) {
    this.response = response;
  }
}
