package com.ayogeshwaran.githubclient.closedpr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClosedPrFragmentViewModel @Inject constructor(
    private val closedPrUseCase: ClosedPrUseCase,
    app: Application
) : AndroidViewModel(app) {
}