package br.com.myapplication.shared.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object CryptoManager {

    private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    private const val ALIAS = "myApplicationKeys"
    private const val ANDROID_KEYSTORE = "AndroidKeyStore"

    private val ks: KeyStore = KeyStore.getInstance(ALIAS).apply {
        load(null)
    }

    private fun getKey(): SecretKey {
        val existingKey = ks.getEntry(ALIAS, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: generateKey()
    }
    private fun generateKey(): SecretKey {
        val kg: KeyGenerator = KeyGenerator.getInstance(ALGORITHM, ANDROID_KEYSTORE)
        val kps: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            setDigests(KeyProperties.DIGEST_SHA256)
            setBlockModes(BLOCK_MODE)
            setEncryptionPaddings(PADDING)
            setUserAuthenticationRequired(false)
            setRandomizedEncryptionRequired(true)
            build()
        }
        kg.init(kps)
        return kg.generateKey()
    }

    fun encrypt(data: String): String {
        val byteArray: ByteArray = Base64.decode(data, Base64.DEFAULT)
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, getKey())
        }
        val cipherData = cipher.doFinal(byteArray)
        return Base64.encodeToString(cipherData, Base64.DEFAULT)
    }

    fun decrypt(data: String): String {
        val byteArray: ByteArray = Base64.decode(data, Base64.DEFAULT)
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey())
        }
        return String(cipher.doFinal(byteArray), Charsets.UTF_8)
    }

}