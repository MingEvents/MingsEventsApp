// LoginViewModel.kt
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mingseventsapp.Routes
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.Utilities.Utils.showError
import com.example.mingseventsapp.model.user.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)

class LoginViewModel : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState

    private val _navigateToMenu = MutableSharedFlow<Unit>()
    val navigateToMenu = _navigateToMenu.asSharedFlow()

    private val _error = MutableSharedFlow<Unit>()
    val error = _error.asSharedFlow()

    fun setEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _formState.value = _formState.value.copy(password = password)
    }

    fun login(password: String, email: String) {
        val appRepository = UserRepository()
        viewModelScope.launch {
            try {
                val response = appRepository.login(email, password)

                when {
                    response.isSuccessful -> {
                        response.body()?.let { loggedInApp ->
                            UserLogged.user = loggedInApp
                            showUserInfo(loggedInApp)
                            _navigateToMenu.emit(Unit)

                        } ?: run {
                        }
                    }

                    response.code() == 404 -> {
                        _formState.value = _formState.value.copy(error = "Usuario o contraseña incorrectos")
                    }

                    else -> {
                        _formState.value = _formState.value.copy(error = "Usuario o contraseña incorrectos")
                    }
                }
            } catch (e: Exception)
            {
                _formState.value = _formState.value.copy(error = "Usuario o contraseña incorrectos")
                e.printStackTrace()
            }

        }
    }
    private fun showUserInfo(user: User) {
        val userInfo = """
            Nombre: ${user.name}
            Correo: ${user.email}
            Rol: ${user.role_id}
            ID: ${user.user_id}
        """.trimIndent()
    }

    fun clearError() {
        _formState.value = _formState.value.copy(error = null)
    }
}