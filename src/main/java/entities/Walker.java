package entities;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Walker {

  private String device_id;
  private String name;
  private String picture;
  private String phone_number;

  public Walker() {

  }

  public Walker(String device_id, String name, String picture, String phone_number) {
    this.device_id = device_id;
    this.name = name;
    this.picture = picture;
    this.phone_number = phone_number;
  }

  public Walker(ResultSet rs) throws SQLException {

    this.device_id    = rs.getString(1);
    this.name         = rs.getString(2);
    this.picture      = rs.getString(3);
    this.phone_number = rs.getString(4);
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

  public void setDevice_id(String device_id) {
    this.device_id = device_id.trim();
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public static List<Walker> fromString(List<List<String>> results) {
    return results.stream().map(row -> new Walker(row.get(0), row.get(1), row.get(2), row.get(3))).collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Walker)) return false;

    Walker otherWalker = (Walker) obj;
    return device_id.equals(otherWalker.device_id) &&
            name.equals(otherWalker.name) &&
            picture.equals(otherWalker.picture) &&
            phone_number.equals(otherWalker.phone_number);
  }

  public boolean isValid() {
    // Null checks
    Field[] fields = getClass().getDeclaredFields();
    for (Field field : fields) {
      try {
        if (field.get(this) == null) return false;
      } catch (IllegalAccessException e) {
        return false;
      }
    }

    // Device ID check
    if (device_id.length() != 36) return false;

    // TODO validate photo?

    // Phone number check
    if (phone_number.length() != 11) return false;
    if (!phone_number.matches("\\d+")) return false;

    return true;
  }

  @Override
  public String toString() {
    return String.format("id:%s\t name:%s\t number:%s\n", device_id, name, phone_number);
  }

}
