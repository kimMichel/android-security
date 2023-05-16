package br.com.myapplication.repository

import br.com.myapplication.models.Password

class AppRepository() {

    val allPasswords: MutableList<Password> = mutableListOf()

    fun insert(password: Password) {
        allPasswords.add(password)
    }

    fun delete(index: Int) {
        allPasswords.removeAt(index)
    }

    fun edit(index: Int, decodedText: String) {
        allPasswords[index] = Password(decodedText)
    }

}