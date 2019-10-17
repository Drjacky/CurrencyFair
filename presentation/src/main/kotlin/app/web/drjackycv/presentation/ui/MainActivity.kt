package app.web.drjackycv.presentation.ui

import app.web.drjackycv.presentation.ui.base.activity.BaseActivity
import app.web.drjackycv.presentation.ui.base.fragment.BaseFragment
import app.web.drjackycv.presentation.ui.home.PhotoFragment

class MainActivity : BaseActivity() {

    override var childFragment: BaseFragment? = PhotoFragment.getFragment()

}
