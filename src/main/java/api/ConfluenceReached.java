package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfluenceReached {

  @JsonProperty
  private String senderID;

  @JsonProperty
  private boolean isReached;

  @JsonCreator
  public ConfluenceReached(@JsonProperty("senderID") String senderID, @JsonProperty("isReached") boolean isReached) {
    this.senderID = senderID;
    this.isReached = isReached;
  }

  public String getSenderID() {
    return senderID;
  }

  public void setSenderID(String senderID) {
    this.senderID = senderID;
  }

  public boolean isReached() {
    return isReached;
  }

  public void setReached(boolean reached) {
    isReached = reached;
  }
}
