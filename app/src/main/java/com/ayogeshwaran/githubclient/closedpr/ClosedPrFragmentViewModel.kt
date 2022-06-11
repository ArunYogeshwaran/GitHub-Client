package com.ayogeshwaran.githubclient.closedpr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayogeshwaran.githubclient.closedpr.usecase.ClosedPrUseCase
import com.ayogeshwaran.githubclient.mappers.UIPullRequestMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPrFragmentViewModel @Inject constructor(
    private val closedPrUseCase: ClosedPrUseCase,
    private val uiPullRequestMapper: UIPullRequestMapper,
    app: Application
) : AndroidViewModel(app) {
    private val _closedPrsList = MutableLiveData<List<UIClosedPullRequest>>(emptyList())
    val closedPrsList: LiveData<List<UIClosedPullRequest>> = _closedPrsList

    fun getClosedPrs() {
        viewModelScope.launch(Dispatchers.IO) {
            val pullRequestsList = closedPrUseCase.getClosedPullRequests()
            val uiList = pullRequestsList.map {
                uiPullRequestMapper.map(it)
            }
            _closedPrsList.postValue(uiList)
        }
    }
}