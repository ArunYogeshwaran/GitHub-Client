package com.ayogeshwaran.githubclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ayogeshwaran.githubclient.closedpr.MainActivityViewModel
import com.ayogeshwaran.githubclient.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val mainActivityViewModel by activityViewModels<MainActivityViewModel>()
    private lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainBinding.btnShowClosedPrs.setOnClickListener {
            mainActivityViewModel.showClosedPr()
        }
    }
}