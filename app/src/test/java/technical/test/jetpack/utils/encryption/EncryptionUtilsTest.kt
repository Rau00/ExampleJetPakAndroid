package technical.test.jetpack.utils.encryption

import org.junit.Assert
import org.junit.Test

class EncrytionUtilsTest {

    @Test
    fun md5_is_correct() {

        Assert.assertEquals(
            EncryptionUtils
                .hashString(
                    "Test EncryptionUtils"
                ), "d01ee2ab007a1396c2a96d4d2ce554e2"
        )

    }

    @Test
    fun md5_is_incorrect(){

        Assert.assertNotEquals(
            EncryptionUtils
                .hashString(
                    "TestEncryptionUtils"
                ), "d01ee2ab007a1396c2a96d4d2ce554e2"
        )

    }
}