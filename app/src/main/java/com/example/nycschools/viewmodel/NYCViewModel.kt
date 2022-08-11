package com.example.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nycschools.model.Repository

/**
 * DI (Dependency injection)
 * 2 types of Injections
 * Constructor injection.- Create the object along with the
 * needed dependencies.
 * Field injection.- Create the object at runtime, from a field references.
 * Is mandatory to use @Inject. (Android components and external libraries)
 */
class NYCViewModel(private val repository: Repository): ViewModel() {


}