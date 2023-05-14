package br.com.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import br.com.myapplication.models.Password
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Upsert
    suspend fun upsertPassword(password: Password)

    @Delete
    suspend fun delete(password: Password)

    @Query("SELECT * FROM password_table")
    fun getAllPasswords(): Flow<List<Password>>
}
