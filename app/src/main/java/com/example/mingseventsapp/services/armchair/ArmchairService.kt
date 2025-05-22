package com.example.mingseventsapp.services.armchairs

import com.example.mingseventsapp.model.Armchair
import retrofit2.Response
import retrofit2.http.*

interface ArmchairService {

    // GET: api/Armchair - Obtener todas las sillas
    @GET("api/Armchair")
    suspend fun getAllArmchairs(): Response<List<Armchair>>

    // GET: api/Armchair/{id} - Obtener por ID
    @GET("api/Armchair/{id}")
    suspend fun getArmchairById(@Path("id") id: Int): Response<Armchair>

    // GET: api/Armchair/establishment/{establish_id} - Obtener por establecimiento
    @GET("api/Armchair/establishment/{establish_id}")
    suspend fun getArmchairsByEstablishmentId(@Path("establish_id") establishId: Int): Response<List<Armchair>>

    // POST: api/Armchair - Crear nueva silla
    @POST("api/Armchair")
    suspend fun createArmchair(@Body armchair: Armchair): Response<Armchair>

    // PUT: api/Armchair/{id} - Actualizar silla
    @PUT("api/Armchair/{id}")
    suspend fun updateArmchair(@Path("id") id: Int, @Body armchair: Armchair): Response<Any>

    // DELETE: api/Armchair/{id} - Eliminar silla
    @DELETE("api/Armchair/{id}")
    suspend fun deleteArmchair(@Path("id") id: Int): Response<Armchair>
}