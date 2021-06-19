package fastcampus.aop.part5.chapter02.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import fastcampus.aop.part5.chapter02.data.db.dao.ProductDao
import fastcampus.aop.part5.chapter02.data.entity.product.ProductEntity
import fastcampus.aop.part5.chapter02.utility.DateConverter

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ProductDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ProductDataBase.db"
    }

    abstract fun productDao(): ProductDao

}