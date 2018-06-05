package entities;

import java.util.List;
import java.util.stream.Collectors;

public class Group {

  private String id;
  private String name;
  private String time;
  private String admin_id;
  private String location_latitude;
  private String location_longitude;
  private String duration;
  private boolean has_dogs;
  private boolean has_kids;

  public Group() {

  }


  public Group(String id, String name, String time, String admin_id, String location_latitude, String location_longitutde, String duration, boolean has_dogs, boolean has_kids) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.admin_id = admin_id;
    this.location_latitude = location_latitude;
    this.location_longitude = location_longitutde;
    this.duration = duration;
    this.has_dogs = has_dogs;
    this.has_kids = has_kids;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTime() {
    return time;
  }

  public String getAdmin_id() {
    return admin_id;
  }

  public String getLocation_latitude() {
    return location_latitude;
  }

  public String getLocation_longitude() {
    return location_longitude;
  }

  public String getDuration() {
    return duration;
  }

  public boolean isHas_dogs() {
    return has_dogs;
  }

  public boolean isHas_kids() {
    return has_kids;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setAdmin_id(String admin_id) {
    this.admin_id = admin_id;
  }

  public void setLocation_latitude(String location_latitude) {
    this.location_latitude = location_latitude;
  }

  public void setLocation_longitude(String location_longitude) {
    this.location_longitude = location_longitude;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public void setHas_dogs(boolean has_dogs) {
    this.has_dogs = has_dogs;
  }

  public void setHas_kids(boolean has_kids) {
    this.has_kids = has_kids;
  }

  @Override
  public String toString() {
    return "Group{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", time='" + time + '\'' +
            ", admin_id='" + admin_id + '\'' +
            ", location_latitude='" + location_latitude + '\'' +
            ", location_longitude='" + location_longitude + '\'' +
            ", duration='" + duration + '\'' +
            ", has_dogs=" + has_dogs +
            ", has_kids=" + has_kids +
            '}';
  }


  public static List<Group> fromString(List<List<String>> results) {
    return results.stream().map(row -> new Group(row.get(0), row.get(1), row.get(2), row.get(3),
                       row.get(4), row.get(5), row.get(6), Boolean.valueOf(row.get(7)),Boolean.valueOf(row.get(8)))).collect(Collectors.toList());
  }
}
