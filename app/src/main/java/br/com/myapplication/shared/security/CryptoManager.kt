package br.com.myapplication.shared.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal

object CryptoManager {

    private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_RSA
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    private const val KEY_SIZE = 2048
    private const val ALIAS = "myApplicationKeys"
    private const val ANDROID_KEYSTORE = "AndroidKeyStore"

    private fun getPublicKey(): PublicKey {
        return try {
            val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply {
                load(null)
            }
            keyStore.getCertificate(ALIAS).publicKey
        } catch (e: NullPointerException) {
            generateKey().public
        }
    }

    private fun getPrivateKey(): PrivateKey {
        val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply {
            load(null)
        }
        return keyStore.getKey(ALIAS, null) as PrivateKey
    }

    private fun generateKey(): KeyPair {
        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, ANDROID_KEYSTORE)

        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            setCertificateSerialNumber(BigInteger.valueOf(777))
            setCertificateSubject(X500Principal("CN=$ALIAS"))
            setKeySize(KEY_SIZE)
            setBlockModes(BLOCK_MODE)
            setEncryptionPaddings(PADDING)
            setDigests(KeyProperties.DIGEST_SHA256)
            setUserAuthenticationRequired(false)
            build()
        }
        keyPairGenerator.initialize(parameterSpec)
        return keyPairGenerator.genKeyPair()
    }

    fun encrypt(data: String): String {
        val publicKey: PublicKey = getPublicKey()
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, publicKey)
        }
        val cipherData = cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
        return String(cipherData)
    }

    fun decrypt(data: String): String {
        val privateKey: PrivateKey = getPrivateKey()
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, privateKey)
        }
        val cipherData = cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
        return String(cipherData)
    }

}