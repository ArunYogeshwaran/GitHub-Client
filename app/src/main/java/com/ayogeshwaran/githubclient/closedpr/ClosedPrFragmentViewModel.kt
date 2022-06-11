package com.ayogeshwaran.githubclient.closedpr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayogeshwaran.githubclient.BuildConfig
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.closedpr.usecase.ClosedPrUseCase
import com.ayogeshwaran.githubclient.common.DispatcherProvider
import com.ayogeshwaran.githubclient.datasource.pr.PullRequestModel
import com.ayogeshwaran.githubclient.mappers.UIPullRequestMapper
import com.ayogeshwaran.githubclient.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPrFragmentViewModel @Inject constructor(
    private val closedPrUseCase: ClosedPrUseCase,
    private val uiPullRequestMapper: UIPullRequestMapper,
    private val dispatcherProvider: DispatcherProvider,
    app: Application
) : AndroidViewModel(app) {
    private val _closedPrUiState =
        MutableLiveData<UiState<Any>>(UiState.Loading)
    val closedPrUiState: LiveData<UiState<Any>> = _closedPrUiState

    /**
     * Gets all the closed PRs corresponding to the [userId] and [repoId]
     *
     * Hardcoded these values for demonstration.
     */
    fun getClosedPrs(
        userId: String = BuildConfig.USER_ID,
        repoId: String = BuildConfig.REPO_ID
    ) {
        viewModelScope.launch(dispatcherProvider.io()) {
            val pullRequestsList = closedPrUseCase.getClosedPullRequests(
                userId, repoId
            )
            pullRequestsList.collect {
                handleResult(it)
            }
        }
    }

    private fun handleResult(result: NetworkResult<List<PullRequestModel>>) {
        when (result) {
            is NetworkResult.Success -> {
                val uiList = result.data?.map { model ->
                    uiPullRequestMapper.map(model)
                }
                uiList?.let {
                    _closedPrUiState.postValue(UiState.Success(it))
                } ?: _closedPrUiState.postValue(UiState.NoData)
            }
            is NetworkResult.Error -> {
                _closedPrUiState.postValue(UiState.Error(R.string.closed_pr_request_failed))
            }
            is NetworkResult.Loading -> {
                _closedPrUiState.postValue(UiState.Loading)
            }
        }
    }
}