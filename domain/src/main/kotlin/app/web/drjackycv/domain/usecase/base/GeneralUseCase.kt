package app.web.drjackycv.domain.usecase.base

interface GeneralUseCase<Type, in Params> {

    operator fun invoke(params: Params): Type

}