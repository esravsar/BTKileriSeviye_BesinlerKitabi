package esra.avsar.besinlerkitabi.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import esra.avsar.besinlerkitabi.model.Besin

/**
 * Created by EsraAvsar on 28.01.2023.
 */

@Database(entities = arrayOf(Besin::class), version = 1)
abstract class BesinDatabase : RoomDatabase() {

    abstract fun besinDAO() : BesinDAO

    //Singleton : Farklı thread'lerden aynı anda tek bir objeye ulaşmak istediğimiz zaman bu yapıyı kullanıyoruz.
    companion object {

        @Volatile
        private var instance : BesinDatabase? = null

        private var lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BesinDatabase::class.java,
            "besindatabase"
        ).build()
    }
}