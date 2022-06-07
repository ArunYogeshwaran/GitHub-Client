package com.ayogeshwaran.githubclient.closedpr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayogeshwaran.githubclient.common.SingleLiveEvent

class MainActivityViewModel : ViewModel() {
    private val _closedPrClicked: MutableLiveData<Unit> = SingleLiveEvent()
    val closedPrClicked: LiveData<Unit> = _closedPrClicked

    fun showClosedPr() {
        _closedPrClicked.value = Unit
    }
}