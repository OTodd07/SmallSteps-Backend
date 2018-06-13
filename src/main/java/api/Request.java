package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  @JsonProperty
  private String sender;

  @JsonCreator
  public Request(@JsonProperty("sender") String sender) {
    this.sender = sender;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }
}
