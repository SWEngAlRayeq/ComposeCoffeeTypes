package app.coffee_types.domain.usecase

import app.coffee_types.domain.repo.CoffeeRepository
import javax.inject.Inject

class GetCoffeesUseCase @Inject constructor(
    private val repo: CoffeeRepository
) {
    suspend operator fun invoke() = repo.getHotCoffees()
}