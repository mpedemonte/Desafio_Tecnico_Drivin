package com.mpdev.razasperrosdrivin.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mpdev.razasperrosdrivin.data.model.Breeds
import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import com.mpdev.razasperrosdrivin.domain.GetBreedsUseCase
import com.mpdev.razasperrosdrivin.domain.GetImageUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogBreedsViewModelUnitTest {
    private lateinit var viewModel: DogBreedsViewModel

    private val getBreedsUseCase: GetBreedsUseCase = mockk()
    private val getImageUseCase: GetImageUseCase = mockk()

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        viewModel = DogBreedsViewModel(getBreedsUseCase, getImageUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    //Verifica que se obtengan las razas de perros al iniciar la aplicación.
    @Test
    fun `when you start the app you get the dog breeds`() = runTest {
        //Given
        val breedsModelResponse = BreedsModelResponse(listOf("affenpinscher", "african", "airedale"), "success")
        coEvery {
            getBreedsUseCase()
        } returns breedsModelResponse

        //When
        viewModel.getBreeds()

        //Then

        coVerify { getBreedsUseCase() }
        assert(viewModel.listBreedsApi.value == breedsModelResponse.breeds)
    }

     //Verifica que ocurra un error al obtener las razas de perros al iniciar la aplicación.
    @Test
    fun `when the application starts an error occurs when obtaining the dog breeds`() = runTest {
        //Given
        val breedsModelResponse = BreedsModelResponse(null, "Error fetching breeds")
        coEvery {
            getBreedsUseCase()
        } returns breedsModelResponse

        //When
        viewModel.getBreeds()
        //Then
        coVerify { getBreedsUseCase() }
        assert(viewModel.errorToastMessage.value == breedsModelResponse.status)
    }

    //Verifica que las razas de perros obtenidas se muestren correctamente
    @Test
    fun `when the obtained dog breeds are displayed`() = runTest {
        val breedlist = listOf("affenpinscher", "african", "airedales")

        val image1 = "https://images.dog.ceo/breeds/affenpinscher/n02110627_6310.jpg"
        val image2 = "https://images.dog.ceo/breeds/african/n02116738_4382.jpg"
        val image3 = null

        coEvery { getImageUseCase(any()) } returns ImageModelResponse(image1, "success") andThen ImageModelResponse(
            image2,
            "success"
        ) andThen ImageModelResponse(image3, "error")

        val imageListObserver = mockk<Observer<List<Breeds>>>(relaxed = true)
        viewModel.listImage.observeForever(imageListObserver)

        viewModel.showBreeds(breedlist)

        assert(viewModel.listImage.value?.size == 3)
        assert(viewModel.listImage.value?.get(0)?.breed == "affenpinscher")
        assert(viewModel.listImage.value?.get(0)?.image =="https://images.dog.ceo/breeds/affenpinscher/n02110627_6310.jpg")
        assert(viewModel.listImage.value?.get(1)?.breed == "african")
        assert(viewModel.listImage.value?.get(1)?.image =="https://images.dog.ceo/breeds/african/n02116738_4382.jpg")
        assert(viewModel.listImage.value?.get(2)?.breed == "error")
        assert(viewModel.listImage.value?.get(2)?.image =="https://img.freepik.com/vector-gratis/plantilla-web-error-404-lindo-perro_23-2147763341.jpg")

    }


}