package app.web.drjackycv.data.entity.base

import com.squareup.moshi.Json

class ErrorResponse(
    @Json(name = "stat") val stat: String,
    @Json(name = "code") val code: Int,
    @Json(name = "message") val message: String
)