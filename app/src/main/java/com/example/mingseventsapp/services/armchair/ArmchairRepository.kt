package com.example.mingseventsapp.services.armchairs

import com.example.mingseventsapp.Retrofit
import com.example.mingseventsapp.model.Armchair
import retrofit2.Response

class ArmchairRepository {

    private val armchairService: ArmchairService by lazy {
        Retrofit.createService(ArmchairService::class.java)
    }

    // Obtener todas las sillas
    suspend fun getAllArmchairs(): Response<List<Armchair>> {
        return armchairService.getAllArmchairs()
    }

    // Obtener una silla por ID
    suspend fun getArmchairById(id: Int): Response<Armchair> {
        return armchairService.getArmchairById(id)
    }

    // Obtener sillas por establecimiento
    suspend fun getArmchairsByEstablishmentId(establishId: Int): Response<List<Armchair>> {
        return armchairService.getArmchairsByEstablishmentId(establishId)
    }

    // Crear una nueva silla
    suspend fun createArmchair(armchair: Armchair): Response<Armchair> {
        return armchairService.createArmchair(armchair)
    }

    // Actualizar una silla existente
    suspend fun updateArmchair(id: Int, armchair: Armchair): Response<Any> {
        return armchairService.updateArmchair(id, armchair)
    }

    // Eliminar una silla
    suspend fun deleteArmchair(id: Int): Response<Armchair> {
        return armchairService.deleteArmchair(id)
    }
}