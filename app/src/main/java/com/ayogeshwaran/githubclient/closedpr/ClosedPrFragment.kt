package com.ayogeshwaran.githubclient.closedpr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.common.MarginItemDecoration
import com.ayogeshwaran.githubclient.databinding.FragmentClosedPrBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

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
        return closedPrBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = setUpAdapter()
        closedPrFragmentViewModel.closedPrsList.observe(viewLifecycleOwner) {
            closedPrBinding.swipeRefresh.isRefreshing = false
            adapter.updateListData(it)
        }
        closedPrBinding.swipeRefresh.setOnRefreshListener {
            closedPrFragmentViewModel.getClosedPrs()
        }

        closedPrFragmentViewModel.getClosedPrs()
    }

    private fun setUpAdapter(): ClosedPrListAdapter {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = ClosedPrListAdapter(this)
        closedPrBinding.rvClosedPr.run {
            this.layoutManager = layoutManager
            this.adapter = adapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.rv_item_margin).roundToInt()
                )
            )
        }
        return adapter
    }

    companion object {
        fun newInstance(): ClosedPrFragment {
            return ClosedPrFragment()
        }
    }
}