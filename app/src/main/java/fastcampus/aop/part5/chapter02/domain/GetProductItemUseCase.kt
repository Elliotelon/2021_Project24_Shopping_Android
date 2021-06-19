package fastcampus.aop.part5.chapter02.domain

import fastcampus.aop.part5.chapter02.data.entity.product.ProductEntity
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke(productId: Long): ProductEntity? {
        return productRepository.getProductItem(productId)
    }

}