package app.web.drjackycv.presentation.ui.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.ui.base.fragment.BaseFragment
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract var childFragment: BaseFragment?

    @setparam:LayoutRes
    var activityLayout: Int = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        if (savedInstanceState == null)
            childFragment?.let { pushChildStack(it) }
    }

    protected fun pushChildStack(fragment: BaseFragment) {
        val fm = supportFragmentManager
        if (isFinishing.not() && fm.findFragmentById(R.id.parentContainer) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.parentContainer, fragment, fragment::class.java.simpleName)
                //.addToBackStack(fragment::class.java.simpleName)
                .commit()
        }
    }

}