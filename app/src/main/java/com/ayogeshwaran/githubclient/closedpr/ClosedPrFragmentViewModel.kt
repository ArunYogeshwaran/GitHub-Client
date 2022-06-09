package com.ayogeshwaran.githubclient.closedpr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
    fun getClosedPrs() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = closedPrUseCase.getClosedPullRequests()
            print(result)
        }
    }
}