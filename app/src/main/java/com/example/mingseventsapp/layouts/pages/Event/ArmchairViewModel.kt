package com.example.mingseventsapp.layouts.pages.Event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mingseventsapp.model.Armchair
import com.example.mingseventsapp.services.armchairs.ArmchairRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArmchairViewModel : ViewModel() {

    private val repository = ArmchairRepository()

    // Estado reactivo para observar los datos desde la UI
    private val _armchairsState = MutableStateFlow<List<Armchair>?>(null)
    val armchairsState: StateFlow<List<Armchair>?> = _armchairsState

    // Método que carga las sillas usando viewModelScope
    fun loadArmchairsByEstablishment(establishmentId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getArmchairsByEstablishmentId(establishmentId)

                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    _armchairsState.value = response.body()
                } else {
                    Log.e("ArmchairViewModel", "No chairs found for establishment ID: $establishmentId")
                    _armchairsState.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("ArmchairViewModel", "Error loading armchairs: ${e.message}")
                _armchairsState.value = emptyList()
            }
        }
    }
}