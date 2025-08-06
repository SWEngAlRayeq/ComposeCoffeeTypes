package app.coffee_types.data.api

import app.coffee_types.data.model.CoffeeDto
import retrofit2.http.GET

interface CoffeeApi {

    @GET("coffee/hot")
    suspend fun getHotCoffees(): List<CoffeeDto>

}