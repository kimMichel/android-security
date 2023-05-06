package br.com.myapplication.ui.home

import android.widget.TextView
import androidx.lifecycle.ViewModel
import java.util.Random

class HomeViewModel: ViewModel() {
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