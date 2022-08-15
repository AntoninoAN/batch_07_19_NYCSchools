package com.example.nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.Repository
import com.example.nycschools.model.local.SchoolSatEntity
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * DI (Dependency injection)
 * 2 types of Injections
 * Constructor injection.- Create the object along with the
 * needed dependencies.
 * Field injection.- Create the object at runtime, from a field references.
 * Is mandatory to use @Inject. (Android components and external libraries)
 */
class NYCViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _schoolSat = MutableLiveData<List<SchoolSatEntity>>()
    val schoolSat: LiveData<List<SchoolSatEntity>>
    get() = _schoolSat

    /**
     * Create a custom scope to be dispose manually.
     * Dispatchers
     * Input/Output used for any network call or db transaction.
     */
    private val customCoroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Dispatchers.Main
     * Connecting to the main thread of the process
     */
    private val customCoroutineScopeM = CoroutineScope(Dispatchers.Main)

    /**
     * Dispatchers.Default
     * Create a worker thread (separated thread)
     */
    private val customCoroutineScopeD = CoroutineScope(Dispatchers.Default)

    /**
     * Dispatchers.unconfined
     * Swamp/Jump/Trampolin between multiple threads, including the main thread.
     */
    private val customCoroutineScopeU = CoroutineScope(Dispatchers.Unconfined)

    /**
     * Custom scope that will have an empty Job.
     * Used for Supervisor Job.
     */
    private val customCoroutineScope2 = CoroutineScope(Job()+Dispatchers.Main+CoroutineName("Antonino"))

    init {
        viewModelScope.launch {
            val response = async {
                repository.getData()
            }
            val data = response.await()
            _schoolSat.value = data
        }
        viewModelScope.launch {
            _schoolSat.value = repository.getData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        customCoroutineScope.cancel()
    }
}