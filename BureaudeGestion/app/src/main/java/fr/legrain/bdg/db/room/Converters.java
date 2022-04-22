package fr.legrain.bdg.db.room;

import java.math.BigDecimal;
import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static BigDecimal fromTimestamp(Float value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public static Float dateToTimestamp(BigDecimal date) {
        return date == null ? null : date.floatValue();
    }

    @TypeConverter
    public static BigDecimal fromTimestampS(String value) {
        if (value==null || value.equals("")) {
            return BigDecimal.valueOf(0.0);
        }
        return new BigDecimal(value);
    }

    @TypeConverter
    public static String dateToTimestampS(BigDecimal date) {
        return date == null ? null : date.toPlainString();
    }


}
