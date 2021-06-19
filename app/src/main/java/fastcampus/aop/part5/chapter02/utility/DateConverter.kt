package fastcampus.aop.part5.chapter02.utility

import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}