package com.android.randomuser.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun MediatorLiveData<*>.addSources(
    vararg sources: LiveData<*>,
    changedCallback: () -> Unit
) {
    sources.forEach {
        addSource(it) {
            changedCallback()
        }
    }
}