package br.com.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password_table")
data class Password(
    @PrimaryKey(autoGenerate = true) val int: Int? = null,
    @ColumnInfo(name = "password") val password: String
)
