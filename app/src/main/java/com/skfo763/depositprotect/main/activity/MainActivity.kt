package com.skfo763.depositprotect.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.skfo763.base.BaseActivity
import com.skfo763.component.tracker.FirebaseTracker
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.admob.AdMobManager
import com.skfo763.depositprotect.databinding.ActivityMainBinding
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.depositprotect.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity(
    override val layoutResId: Int = R.layout.activity_main,
    override val navHostResId: Int? = R.id.main_nav_host
): BaseActivity<ActivityMainBinding, MainViewModel, MainActivityUseCase>() {

    override val viewModel: MainViewModel by viewModels()
    override var useCase: MainActivityUseCase = MainActivityUseCase(this)
    private lateinit var appBarConfiguration: AppBarConfiguration
    @Inject lateinit var adMobManager: AdMobManager
    @Inject lateinit var firebaseTracker: FirebaseTracker

    override val bindingVariable: (ActivityMainBinding) -> Unit = {
        it.viewModel = viewModel
        adMobManager.setTemplateView(it.bannerTemplate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adMobManager.showNativeAd()

        setToolbar()
        viewModel.initializeNaviDrawer()
    }

    override fun connectNavHostToController(host: NavHostFragment) {
        appBarConfiguration = AppBarConfiguration(host.navController.graph, binding.mainDrawerLayout)
        binding.mainToolbar.toolbar.setupWithNavController(host.navController, appBarConfiguration)
        binding.mainNavigationView.mainNavView.setupWithNavController(host.navController)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.mainToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val navController = navHostResId?.let { findNavController(this, it) } ?: return super.onSupportNavigateUp()
                return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
            }
            R.id.toolbar_menu_developer_info -> {
                AlertDialog.Builder(this)
                    .setTitle(R.string.developer_info_title)
                    .setMessage(R.string.developer_info_message)
                    .setPositiveButton(R.string.common_confirm) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}