package app.endershrooms.thatcatapp.db;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import app.endershrooms.thatcatapp.model.Weight;
import java.util.Arrays;
import java.util.List;

public class Converters {

  @TypeConverter
  public static Weight weightFromString(String value) {
    String[] fields = value.split("\\|");
    if (fields.length != 2) {
      return new Weight("n/a", "n/a");
    }
    return new Weight(fields[0], fields[1]);
  }

  @TypeConverter
  public static String weightToDatabaseString(Weight weight) {
    return weight.getImperial() + "|" + weight.getMetric();
  }

  @TypeConverter
  public static String breedIdsToDatabaseString(List<String> breedIds) {
    return TextUtils.join("|", breedIds);
  }

  @TypeConverter
  public static List<String> breedIdsFromString(String databaseIdString) {
    String[] ids = databaseIdString.trim().split("\\|");
    return Arrays.asList(ids);
  }

}
