package app.endershrooms.thatcatapp.db;

import androidx.room.TypeConverter;
import app.endershrooms.thatcatapp.model.Weight;

public class Converters {

  @TypeConverter
  public static Weight fromString(String value) {
    String[] fields = value.split("\\|");
    if (fields.length != 2) {
      return new Weight("n/a", "n/a");
    }
    return new Weight(fields[0], fields[1]);
  }

  @TypeConverter
  public static String toDatabaseString(Weight weight) {
    return weight.getImperial() + "|" + weight.getMetric();
  }
}
