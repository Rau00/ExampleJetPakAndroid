package technical.test.jetpack.di

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class PresenterModuleTest : KoinTest {

    @Test
    fun `checking modules`() {
        koinApplication { modules(viewModelModule) }.checkModules()
    }
}