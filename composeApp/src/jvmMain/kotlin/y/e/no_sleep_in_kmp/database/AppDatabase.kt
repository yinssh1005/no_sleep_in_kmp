package y.e.no_sleep_in_kmp.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val dbFile = File(System.getProperty("user.home"), ".no_sleep_notes.db")
                val builder = Room.databaseBuilder<AppDatabase>(
                    name = dbFile.absolutePath
                )
                builder.setDriver(BundledSQLiteDriver())
                val db = builder.build()
                INSTANCE = db
                db
            }
        }
    }
}
