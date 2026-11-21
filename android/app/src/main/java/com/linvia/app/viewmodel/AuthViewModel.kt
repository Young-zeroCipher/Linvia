package com.linvia.app.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn.asStateFlow()

    fun signIn(email: String, password: String) {
        // Sign in logic
        _isSignedIn.value = true
    }

    fun signUp(name: String, email: String, password: String) {
        // Sign up logic
        _isSignedIn.value = true
    }

    fun signOut() {
        _isSignedIn.value = false
    }
}
