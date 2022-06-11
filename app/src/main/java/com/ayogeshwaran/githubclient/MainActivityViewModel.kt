package com.ayogeshwaran.githubclient

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _actionBarTitle = MutableLiveData<String>()
    val actionBarTitle: LiveData<String> = _actionBarTitle

    @UiThread
    fun updateActionBarTitle(title: String) {
        _actionBarTitle.value = title
    }
}