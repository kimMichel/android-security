package br.com.myapplication.ui.store

import androidx.lifecycle.ViewModel
import br.com.myapplication.models.Password
import br.com.myapplication.repository.AppRepository
import br.com.myapplication.shared.security.CryptoManager

class StoreViewModel(private val repository: AppRepository): ViewModel() {

    val passwordList: MutableList<Password> = repository.allPasswords

    fun delete(position: Int) {
        repository.delete(position)
    }

    fun decode(position: Int, encryptText: String) {
        repository.edit(position, CryptoManager.decrypt(encryptText))
    }

}