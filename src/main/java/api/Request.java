package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  @JsonProperty
  private String sender;

  @JsonProperty
  private String senderLat;

  @JsonProperty
  private String senderLong;

  @JsonCreator
  public Request(@JsonProperty("sender") String sender, @JsonProperty("senderLat") String senderLat,
                 @JsonProperty("senderLong") String senderLong) {
    this.sender = sender;
    this.senderLat = senderLat;
    this.senderLong = senderLong;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender)
  {
    this.sender = sender;
  }

  public String getSenderLat() {
    return senderLat;
  }

  public void setSenderLat(String senderLat) {
    this.senderLat = senderLat;
  }

  public String getSenderLong() {
    return senderLong;
  }

  public void setSenderLong(String senderLong) {
    this.senderLong = senderLong;
  }
}
