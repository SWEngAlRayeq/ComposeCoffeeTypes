package app.coffee_types.data.repo_impl

import app.coffee_types.data.api.CoffeeApi
import app.coffee_types.domain.model.Coffee
import app.coffee_types.domain.repo.CoffeeRepository
import javax.inject.Inject
import kotlin.collections.map

class CoffeeRepositoryImpl @Inject constructor(
    private val api: CoffeeApi
): CoffeeRepository {
    override suspend fun getHotCoffees(): List<Coffee> {
        return api.getHotCoffees().map {
            Coffee(it.title, it.description, it.image)
        }
    }
}