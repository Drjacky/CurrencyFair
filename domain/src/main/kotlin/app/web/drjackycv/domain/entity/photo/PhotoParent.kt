package app.web.drjackycv.domain.entity.photo

import app.web.drjackycv.domain.entity.base.PaginationObject

class PhotoParent(
    val photos: PaginationObject<Photo>,
    val stat: String
)