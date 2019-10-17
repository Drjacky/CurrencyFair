package app.web.drjackycv.presentation.entity.base

interface Mapper<DomainObject, UIObject> {

    fun mapToUI(obj: DomainObject): UIObject

    fun mapToDomain(obj: UIObject): DomainObject

}