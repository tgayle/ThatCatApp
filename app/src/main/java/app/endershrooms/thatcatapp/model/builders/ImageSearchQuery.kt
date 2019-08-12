package app.endershrooms.thatcatapp.model.builders

class ImageSearchQuery(
    var size: ImageSize = ImageSize.MED,
    var order: SearchQueryOrder = SearchQueryOrder.ASC,
    var limit: Int = 1,
    var page: Int = 0,
    var categoryIds: List<Int> = mutableListOf(),
    var breedId: String = ""
) {

    fun toMap(): Map<String, String> = mapOf(
        "size" to size.size,
        "order" to order.toString(),
        "limit" to limit.toString(),
        "page" to page.toString(),
        "category_ids" to categoryIds.joinToString(","),
        "breed_id" to breedId
    )

    fun setSize(size: ImageSize) = apply {
        this.size = size
    }

    fun setOrder(order: SearchQueryOrder) = apply {
        this.order = order
    }

    fun setLimit(limit: Int) = apply {
        this.limit = limit
    }

    fun setPage(page: Int) = apply {
        this.page = page
    }

    fun setCategoryIds(categoryIds: List<Int>) = apply {
        this.categoryIds = categoryIds
    }

    fun setBreedId(breedId: String) = apply {
        this.breedId = breedId
    }
}

