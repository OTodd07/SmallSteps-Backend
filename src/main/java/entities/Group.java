package entities;

import javax.xml.datatype.Duration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Field;

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
  private boolean is_walking;

  public Group() {

  }


  public Group(String id, String name, String time, String admin_id, String location_latitude,
               String location_longitutde, String duration, boolean has_dogs, boolean has_kids) {
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

  public boolean isIs_walking() {
    return is_walking;
  }

  public void setIs_walking(boolean is_walking) {
    this.is_walking = is_walking;
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
    this.admin_id = admin_id.trim();
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
    return results.stream().map(row -> {
      Group group = new Group(row.get(0), row.get(1), row.get(2), row.get(3),
              row.get(4), row.get(5), row.get(6), Boolean.valueOf(row.get(7)), Boolean.valueOf(row.get(8)));

      // set is_walking
      boolean is_walking = false;

      String current = new SimpleDateFormat("yyyy-M-d H:m:s").format(new Date());
      String startTime = group.getTime();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m:s");
      Date currentDate = null;
      Date startDate = null;
      try {
        currentDate = sdf.parse(current);
        startDate = sdf.parse(startTime);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      if(currentDate.after(startDate)) {
        is_walking = true;
      }

      group.setIs_walking(is_walking);
      return group;
    }).collect(Collectors.toList());

  }

  public boolean groupValidityCheck() {

    // Null checks
    Field[] fields = getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getName().equals("id")) continue;

      try {
        if (field.get(this) == null) return false;
      } catch (IllegalAccessException e) {
        return false;
      }
    }


    if (admin_id.length() != 36) return false;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:m:s");

    try{
      formatter.parse(time);
    } catch (ParseException e) {
      return false;
    }

    try {
      String[] parts = duration.split(":");
      java.time.Duration.parse(String.format("PT%sH%sM%sS",parts[0],parts[1],parts[2]));
    } catch (Exception e) {
      return false;
    }

    try {
      Double.parseDouble(location_latitude);
    } catch (Exception e) {
      return false;
    }

    try {
      Double.parseDouble(location_longitude);
    } catch (Exception e) {
      return false;
    }



    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Group group = (Group) o;

    if (has_dogs != group.has_dogs) return false;
    if (has_kids != group.has_kids) return false;
    if (!id.equals(group.id)) return false;
    if (!name.equals(group.name)) return false;
    if (!time.equals(group.time)) return false;
    if (!admin_id.equals(group.admin_id)) return false;
    if (!location_latitude.equals(group.location_latitude)) return false;
    if (!location_longitude.equals(group.location_longitude)) return false;
    return duration.equals(group.duration);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + time.hashCode();
    result = 31 * result + admin_id.hashCode();
    result = 31 * result + location_latitude.hashCode();
    result = 31 * result + location_longitude.hashCode();
    result = 31 * result + duration.hashCode();
    result = 31 * result + (has_dogs ? 1 : 0);
    result = 31 * result + (has_kids ? 1 : 0);
    return result;
  }


}
