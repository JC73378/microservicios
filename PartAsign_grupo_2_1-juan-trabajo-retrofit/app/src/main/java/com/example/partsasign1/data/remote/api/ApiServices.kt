package com.example.partsasign1.data.remote.api

import com.example.partsasign1.data.remote.model.OtProgramadaDto
import com.example.partsasign1.data.remote.model.RecepcionDto
import com.example.partsasign1.data.remote.model.RepuestoDto
import com.example.partsasign1.data.remote.model.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RepuestosApi {
    @GET("/api/repuestos")
    suspend fun listar(): List<RepuestoDto>

    @POST("/api/repuestos/crear")
    suspend fun crear(@Body body: RepuestoDto): RepuestoDto

    @PUT("/api/repuestos/{id}")
    suspend fun actualizar(@Path("id") id: Int, @Body body: RepuestoDto): RepuestoDto

    @DELETE("/api/repuestos/{id}")
    suspend fun eliminar(@Path("id") id: Int): Response<Unit>
}

interface OtApi {
    @GET("/api/ot-programadas")
    suspend fun listar(): List<OtProgramadaDto>

    @POST("/api/ot-programadas/crear")
    suspend fun crear(@Body body: OtProgramadaDto): OtProgramadaDto

    @PUT("/api/ot-programadas/{id}")
    suspend fun actualizar(@Path("id") id: Int, @Body body: OtProgramadaDto): OtProgramadaDto

    @DELETE("/api/ot-programadas/{id}")
    suspend fun eliminar(@Path("id") id: Int): Response<Unit>
}

interface RecepcionApi {
    @GET("/api/recepciones")
    suspend fun listar(): List<RecepcionDto>

    @POST("/api/recepciones/crear")
    suspend fun crear(@Body body: RecepcionDto): RecepcionDto

    @PUT("/api/recepciones/{id}")
    suspend fun actualizar(@Path("id") id: Int, @Body body: RecepcionDto): RecepcionDto

    @DELETE("/api/recepciones/{id}")
    suspend fun eliminar(@Path("id") id: Int): Response<Unit>
}

interface AuthApi {
    @GET("/api/usuarios")
    suspend fun listar(): List<UsuarioDto>

    @GET("/api/usuarios/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): UsuarioDto
}
