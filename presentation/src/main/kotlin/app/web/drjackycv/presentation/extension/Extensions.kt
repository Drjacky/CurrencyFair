package app.web.drjackycv.presentation.extension

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import app.web.drjackycv.presentation.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? =
    if (p1 != null && p2 != null) block(p1, p2) else null

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun ImageView.load(
    url: String?,
    @DrawableRes placeholderRes: Int = R.drawable.ic_placeholder,
    isCircular: Boolean = false,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {}
) {
    val requestOptions = RequestOptions().apply {
        placeholder(placeholderRes)
        error(placeholderRes)
        if (isCircular) circleCrop() else centerCrop()
    }
    val glideRequest = Glide
        .with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onError()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccess()
                return false
            }
        })

    if (isCircular) glideRequest.apply(RequestOptions.circleCropTransform())

    glideRequest.into(this)
}