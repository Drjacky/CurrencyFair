package app.web.drjackycv.data.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable

fun <T> LiveData<T>.toFlowable(owner: LifecycleOwner): Flowable<T> =
    Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(owner, this))

fun <T> Flowable<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this)