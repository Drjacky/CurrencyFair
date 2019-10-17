package app.web.drjackycv.domain.entity.photo

class Photos(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: String,
    val list: List<Photo>
)