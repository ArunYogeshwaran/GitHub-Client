package com.ayogeshwaran.githubclient.closedpr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayogeshwaran.githubclient.closedpr.usecase.ClosedPrUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPrFragmentViewModel @Inject constructor(
    private val closedPrUseCase: ClosedPrUseCase,
    app: Application
) : AndroidViewModel(app) {
    private val _closedPrsList = MutableLiveData<List<UIClosedPullRequest>>(emptyList())
    val closedPrsList: LiveData<List<UIClosedPullRequest>> = _closedPrsList

    fun getClosedPrs() {
        viewModelScope.launch(Dispatchers.IO) {
            val pullRequestsList = closedPrUseCase.getClosedPullRequests()
            val uiList = pullRequestsList.map {
                UIClosedPullRequest(
                    it.title,
                    it.createdDate,
                    it.closedDate,
                    it.userName,
                    it.userImageUrl,
                    it.pullRequestUrl
                )
            }
            _closedPrsList.postValue(uiList)
        }
    }
}