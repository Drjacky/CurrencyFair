package app.web.drjackycv.domain.entity.base

class PaginationObject<T : Any>(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    val list: List<T>
)