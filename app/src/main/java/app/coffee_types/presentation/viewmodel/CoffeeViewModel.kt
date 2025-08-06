package app.coffee_types.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coffee_types.domain.model.Coffee
import app.coffee_types.domain.usecase.GetCoffeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val getCoffeesUseCase: GetCoffeesUseCase
) : ViewModel() {

    var coffees by mutableStateOf<List<Coffee>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
    var error by mutableStateOf<String?>(null)


    init {
        loadCoffee()
    }

    private fun loadCoffee() {
        viewModelScope.launch {
            try {
                coffees = getCoffeesUseCase()
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

}