package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

  @JsonProperty
  private boolean response;

  @JsonCreator
  public Response(@JsonProperty("response") boolean response) {
    this.response = response;
  }

  public boolean isResponse() {
    return response;
  }

  public void setResponse(boolean response) {
    this.response = response;
  }
}
