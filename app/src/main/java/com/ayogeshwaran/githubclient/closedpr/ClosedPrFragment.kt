package com.ayogeshwaran.githubclient.closedpr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.databinding.FragmentClosedPrBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClosedPrFragment : Fragment() {
    private val closedPrFragmentViewModel by viewModels<ClosedPrFragmentViewModel>()
    private lateinit var closedPrBinding: FragmentClosedPrBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        closedPrBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_closed_pr, container, false)
        return closedPrBinding.rvClosedPr
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closedPrFragmentViewModel.getClosedPrs()
    }

    companion object {
        fun newInstance(): ClosedPrFragment {
            return ClosedPrFragment()
        }
    }
}