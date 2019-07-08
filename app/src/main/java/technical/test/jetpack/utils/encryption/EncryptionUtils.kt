package technical.test.jetpack.utils.encryption

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class EncryptionUtils {

    companion object {

        private val HASH_TYPE = "MD5"

        fun hashString(input: String): String {

            val md = MessageDigest.getInstance(HASH_TYPE)
            return BigInteger(1, md.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')

        }

        fun generateTimeStamp(): String {
            return SimpleDateFormat.getDateTimeInstance().format(Date())
        }
    }

}