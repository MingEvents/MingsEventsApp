// LoginViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {
    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState

    fun setEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _formState.value = _formState.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            _formState.value = _formState.value.copy(isLoading = true)
            delay(2000)

            if (_formState.value.email == "admin@example.com" && _formState.value.password == "1234") {
                _formState.value = _formState.value.copy(error = null)

            } else {
                _formState.value = _formState.value.copy(error = "Credenciales incorrectas")
            }

            _formState.value = _formState.value.copy(isLoading = false)
        }
    }
}