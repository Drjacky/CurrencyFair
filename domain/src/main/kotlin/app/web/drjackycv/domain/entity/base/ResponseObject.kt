package app.web.drjackycv.domain.entity.base

interface ResponseObject<out DomainObject : Any?> {

    fun toDomain(): DomainObject

}