package app.coffee_types.domain.repo

import app.coffee_types.domain.model.Coffee

interface CoffeeRepository {
    suspend fun getHotCoffees(): List<Coffee>
}