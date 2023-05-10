package br.com.myapplication.ui.home

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import br.com.myapplication.shared.security.CryptoManager
import java.util.Random

class HomeViewModel: ViewModel() {

    private var encryptedPassword: String = ""
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

    fun encryptTest(password: String, context: Context) {
        encryptedPassword = CryptoManager.encrypt(password)
        Toast.makeText(context, encryptedPassword, Toast.LENGTH_SHORT).show()
    }

    fun decryptTest(context: Context) {
        val decrypt = CryptoManager.decrypt(encryptedPassword)
        Toast.makeText(context, decrypt, Toast.LENGTH_SHORT).show()
    }
}