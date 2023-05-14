import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.myapplication.database.dao.PasswordDao
import br.com.myapplication.models.Password

@Database(entities = [Password::class], version = 1, exportSchema = false)
abstract class PassRoomDb: RoomDatabase() {
    abstract fun passDao(): PasswordDao
}