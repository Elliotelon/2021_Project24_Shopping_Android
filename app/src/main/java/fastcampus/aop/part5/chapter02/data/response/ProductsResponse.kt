package fastcampus.aop.part5.chapter02.data.response

data class ProductsResponse(
    val items: List<ProductResponse>,
    val count: Int
)