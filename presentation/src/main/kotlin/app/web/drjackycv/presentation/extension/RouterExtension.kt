package app.web.drjackycv.presentation.extension

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.IOException

inline fun <reified T : Parcelable> Fragment.setParcelableParam(obj: T, tag: String = "") {
    if (arguments == null) {
        arguments = Bundle()
    }
    arguments?.putParcelable(T::class.java.name + tag, obj)
}

inline fun <reified T : Parcelable> Fragment.getParcelableParam(
    errorMessage: String,
    tag: String = ""
): T {
    if (arguments == null) {
        throw IOException(errorMessage)
    } else {
        return arguments?.getParcelable(T::class.java.name + tag) as T
    }
}