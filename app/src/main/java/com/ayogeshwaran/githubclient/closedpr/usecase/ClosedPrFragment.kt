package com.ayogeshwaran.githubclient.closedpr.usecase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.databinding.FragmentClosedPrBinding

class ClosedPrFragment : Fragment() {
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

    companion object {
        fun newInstance(): ClosedPrFragment {
            return ClosedPrFragment()
        }
    }
}