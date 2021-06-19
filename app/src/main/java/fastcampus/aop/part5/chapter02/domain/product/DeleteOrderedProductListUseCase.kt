package fastcampus.aop.part5.chapter02.domain.product

import fastcampus.aop.part5.chapter02.data.repository.ProductRepository
import fastcampus.aop.part5.chapter02.domain.UseCase

internal class DeleteOrderedProductListUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke() {
        return productRepository.deleteAll()
    }

}