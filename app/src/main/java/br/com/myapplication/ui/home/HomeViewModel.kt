package br.com.myapplication.ui.home

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myapplication.models.Password
import br.com.myapplication.repository.AppRepository
import br.com.myapplication.shared.security.CryptoManager
import kotlinx.coroutines.launch
import java.util.Random

class HomeViewModel(private val repository: AppRepository): ViewModel() {

    fun insert(password: String) = viewModelScope.launch {
        val request = Password(password = CryptoManager.encrypt(password))
        repository.insert(request)
    }

    fun generatePassword(digit: Int, txtResult: TextView) {
        val numbers = mutableListOf<Int>()
        val random = Random()

        while(true) {
            val number = random.nextInt(10)
            numbers.add(number)

            if (numbers.size == digit) {
                break
            }
        }
        txtResult.text = numbers.joinToString(" ")
    }
    fun generateAlphaNumPassword(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString(" ")
    }
}