package fastcampus.aop.part5.chapter02.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fastcampus.aop.part5.chapter02.R
import fastcampus.aop.part5.chapter02.databinding.ActivityMainBinding
import fastcampus.aop.part5.chapter02.presentation.BaseActivity
import fastcampus.aop.part5.chapter02.presentation.BaseFragment
import fastcampus.aop.part5.chapter02.presentation.list.ProductListFragment
import fastcampus.aop.part5.chapter02.presentation.profile.ProfileFragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.observe

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun observeData()  {
        viewModel.mainStateLiveData.observe(this){
            when (it) {
                is MainState.RefreshOrderList -> {
                    binding.bottomNav.selectedItemId = R.id.menu_profile
                    val fragment = supportFragmentManager.findFragmentByTag(ProfileFragment.TAG)
                    (fragment as? BaseFragment<*, *>)?.viewModel?.fetchData()
                }
            }
        }

    }

    private fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(ProductListFragment(), ProductListFragment.TAG)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_products -> {
                showFragment(ProductListFragment(), ProductListFragment.TAG)
                true
            }
            R.id.menu_profile -> {
                showFragment(ProfileFragment(), ProfileFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }

    }

}