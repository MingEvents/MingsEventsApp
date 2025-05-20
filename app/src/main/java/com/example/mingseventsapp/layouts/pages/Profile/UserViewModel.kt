package com.example.mingseventsapp.layouts.pages.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.UserRepository
import com.example.mingseventsapp.model.user.User
import kotlinx.coroutines.launch
import android.util.Log

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                val response = userRepository.updateUser(user.user_id, user)
                if (response.isSuccessful) {
                    val updatedUser = response.body()
                    if (updatedUser != null) {
                        // Usuario actualizado correctamente
                        Log.d("UserViewModel", "Usuario actualizado: $updatedUser")
                    } else {
                        Log.e("UserViewModel", "La respuesta fue exitosa pero el cuerpo es null")
                    }
                } else {
                    Log.e("UserViewModel", "Error en la actualización. Código: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Excepción al actualizar usuario", e)
            }
        }
    }
}