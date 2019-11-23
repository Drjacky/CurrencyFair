package app.web.drjackycv.domain.entity.base

private const val UNKNOWN_CODE = -1
private const val UNKNOWN_STAT = "UNKNOWN STAT"

sealed class Failure : Throwable() {

    abstract class FailureWithMessage(open val msg: String) : Failure()

    class Error(val stat: String, val code: Int, override val message: String) :
        FailureWithMessage(message) {
        constructor(message: String) : this(UNKNOWN_STAT, UNKNOWN_CODE, message)
    }

    class Timeout(override val msg: String) : FailureWithMessage(msg)

    class NoInternet(override val msg: String) : FailureWithMessage(msg)

    class Unauthorized(override val msg: String) : FailureWithMessage(msg)

    class UnKnown(override val msg: String) : FailureWithMessage(msg)

    class NotFound(override val msg: String) : FailureWithMessage(msg)

}