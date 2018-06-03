package entities;


import java.util.List;
import java.util.stream.Collectors;

public class Walker {

  private final String device_id;
  private final String name;
  private final String picture;
  private final String phone_number;

  public Walker(String device_id, String name, String picture, String phone_number) {
    this.device_id = device_id;
    this.name = name;
    this.picture = picture;
    this.phone_number = phone_number;
  }

  public String getDevice_id() {
    return device_id;
  }

  public String getName() {
    return name;
  }

  public String getPicture() {
    return picture;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public static List<Walker> fromString(List<List<String>> results) {
    return results.stream().map(row -> new Walker(row.get(0), row.get(1), row.get(2), row.get(3))).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.name;
  }
}
