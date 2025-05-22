package com.example.mingseventsapp.layouts.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mingseventsapp.UserLogged
import com.example.mingseventsapp.model.user.User
import com.example.mingseventsapp.model.user.UsersViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

data class RegisterFormState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
                            )

class RegisterViewModel : ViewModel() {
    private val _formState = MutableStateFlow(RegisterFormState())

    val userViewModel = UsersViewModel()
    val formState: StateFlow<RegisterFormState> = _formState

    private val _navigateToMenu = MutableSharedFlow<Unit>()
    val navigateToMenu = _navigateToMenu.asSharedFlow()

    fun setName(name: String) {
        _formState.value = _formState.value.copy(name = name)
    }

    fun setLastName(lastName: String) {
        _formState.value = _formState.value.copy(lastName = lastName)
    }

    fun setEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun setPhone(phone: String) {
        _formState.value = _formState.value.copy(phone = phone)
    }

    fun setPassword(password: String) {
        _formState.value = _formState.value.copy(password = password)
    }

    fun setConfirmPassword(confirmPassword: String) {
        _formState.value = _formState.value.copy(confirmPassword = confirmPassword)
    }

    fun setLoading(isLoading: Boolean) {
        _formState.value = _formState.value.copy(isLoading = isLoading)
    }

    fun setError(error: String?) {
        _formState.value = _formState.value.copy(error = error)
    }

    fun register() {
        viewModelScope.launch {

            val name = _formState.value.name.trim()
            val lastName = _formState.value.lastName.trim()
            val email = _formState.value.email.trim()
            val phone = _formState.value.phone.trim()
            val password = _formState.value.password
            val confirmPassword = _formState.value.confirmPassword

            if (name.isEmpty() ||
                lastName.isEmpty() ||
                email.isEmpty() ||
                phone.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {
                setError("Todos los campos son obligatorios")
                return@launch
            }

            if (!phone.isNumeric()) {
                setError("La edad debe ser un número válido")
                return@launch
            }

            // Validar contraseñas coincidan
            if (password != confirmPassword) {
                setError("Las contraseñas no coinciden")
                return@launch
            }

            var user: User = User()
            user.name = name
            user.email = email
            user.password = password
            user.phone = phone.toInt()
            user.second_name = lastName

            val user_id = userViewModel.newUser(user)!!
            user.user_id = user_id

            UserLogged.user = user

            if (user == null) {
                setError("No se ha podido crear el usuario")
            } else {
                _navigateToMenu.emit(Unit)
            }

        }
    }

    fun clearError() {
        _formState.value = _formState.value.copy(error = null)
    }

    // Extensión para validar si un String es numérico
    fun String.isNumeric(): Boolean {
        return this.matches(Regex("^\\d+\$"))
    }
}