package br.com.myapplication.repository

import br.com.myapplication.models.Password

class AppRepository() {

    val allPasswords: List<Password> = listOf()

    fun insert(password: Password) {
        allPasswords.plus(password)
    }

}