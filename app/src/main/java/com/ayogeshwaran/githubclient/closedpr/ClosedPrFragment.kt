package com.ayogeshwaran.githubclient.closedpr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayogeshwaran.githubclient.MainActivityViewModel
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.common.MarginItemDecoration
import com.ayogeshwaran.githubclient.databinding.FragmentClosedPrBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt


@AndroidEntryPoint
class ClosedPrFragment : Fragment(), ClosedPrListAdapter.OnPrItemClickedListener {
    private val closedPrFragmentViewModel by viewModels<ClosedPrFragmentViewModel>()
    private val activityViewModel by activityViewModels<MainActivityViewModel>()
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
        activityViewModel.updateActionBarTitle(getString(R.string.closed_pull_requests))

        val adapter = setUpAdapter()

        closedPrFragmentViewModel.closedPrUiState.observe(viewLifecycleOwner) {
            closedPrBinding.swipeRefresh.isRefreshing = false
            handleUiState(it, adapter)
        }

        closedPrBinding.swipeRefresh.setOnRefreshListener {
            closedPrFragmentViewModel.getClosedPrs()
        }

        closedPrFragmentViewModel.getClosedPrs()
    }

    private fun handleUiState(uiState: UiState<Any>?, adapter: ClosedPrListAdapter) {
        uiState?.let { state ->
            when (state) {
                is UiState.Success -> {
                    closedPrBinding.swipeRefresh.isRefreshing = false
                    closedPrBinding.ivEmptyState.isVisible = false
                    closedPrBinding.ivErrorState.isVisible = false
                    adapter.updateListData(state.data as List<UIClosedPullRequest>)
                }
                is UiState.Loading -> {
                    closedPrBinding.swipeRefresh.isRefreshing = true
                    closedPrBinding.ivEmptyState.isVisible = false
                    closedPrBinding.ivErrorState.isVisible = false
                }
                is UiState.Error -> {
                    closedPrBinding.swipeRefresh.isRefreshing = false
                    closedPrBinding.rvClosedPr.isVisible = false
                    closedPrBinding.ivEmptyState.isVisible = false
                    closedPrBinding.ivErrorState.isVisible = true
                }
                is UiState.NoData -> {
                    closedPrBinding.swipeRefresh.isRefreshing = false
                    closedPrBinding.rvClosedPr.isVisible = false
                    closedPrBinding.ivEmptyState.isVisible = true
                    closedPrBinding.ivErrorState.isVisible = false
                }
            }
        } ?: run {
            closedPrBinding.swipeRefresh.isRefreshing = true
        }
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

    override fun onItemClicked(uiClosedPullRequest: UIClosedPullRequest) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uiClosedPullRequest.pullRequestUrl)
            )
        )
    }

    companion object {
        fun newInstance(): ClosedPrFragment {
            return ClosedPrFragment()
        }
    }
}