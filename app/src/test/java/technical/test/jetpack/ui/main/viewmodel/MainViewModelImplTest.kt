package technical.test.jetpack.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.Assert
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import technical.test.jetpack.network.repository.SuperHerosRepositoryImplTest
import technical.test.jetpack.ui.main.view.MainFragment

@RunWith(JUnit4::class)
class MainViewModelImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    var view: MainFragment = MainFragment.newInstance()
    val repository: SuperHerosRepositoryImplTest = SuperHerosRepositoryImplTest()

    var mainViewModel: MainViewModelImpl = MainViewModelImpl(view, repository)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadSuperHeros_Success() {
        Assert.assertNotNull(mainViewModel.getSuperHeros().value!!.get(0))
    }

}