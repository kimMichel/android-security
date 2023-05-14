package br.com.myapplication.repository

import androidx.annotation.WorkerThread
import br.com.myapplication.database.dao.PasswordDao
import br.com.myapplication.models.Password
import kotlinx.coroutines.flow.Flow

class AppRepository(private val passwordDao: PasswordDao) {

    val allPasswords: Flow<List<Password>> = passwordDao.getAllPasswords()

    @WorkerThread
    suspend fun insert(password: Password) {
        passwordDao.upsertPassword(password)
    }
}