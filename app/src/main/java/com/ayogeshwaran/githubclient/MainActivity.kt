package com.ayogeshwaran.githubclient

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ayogeshwaran.githubclient.closedpr.ClosedPrFragment
import com.ayogeshwaran.githubclient.closedpr.MainActivityViewModel
import com.ayogeshwaran.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        observeShowPrClick()
    }

    private fun observeShowPrClick() {
        mainActivityViewModel.closedPrClicked.observe(this) {
            val closedPrFragment = ClosedPrFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_activity_main, closedPrFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}