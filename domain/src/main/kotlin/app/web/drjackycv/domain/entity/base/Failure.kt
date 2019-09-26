package app.web.drjackycv.domain.entity.base

private const val UNKNOWN_CODE = -1
private const val UNKNOWN_STAT = "UNKNOWN STAT"

sealed class Failure(var retryAction: () -> Unit) : Throwable() {

    abstract class FailureWithMessage(override val message: String) : Failure({})

    class Error(val stat: String, val code: Int, override val message: String) :
        FailureWithMessage(message) {
        constructor(message: String) : this(UNKNOWN_STAT, UNKNOWN_CODE, message)
    }

    class Timeout(override val message: String) : FailureWithMessage(message)

    class NoInternet(override val message: String) : FailureWithMessage(message)

    object Unauthorized : Failure({})

    object NotInDatabase : Failure({})

    object NoLogin : Failure({})

    object NotFound : Failure({})

}