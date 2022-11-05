package com.example.livedemo_datastores01

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "MyViewModel"

class MyViewModel : ViewModel()
{
    private val prefs = MyPreferencesRepository.get()

    fun loadInputs(act: MainActivity) {
        viewModelScope.launch {
            prefs.input1.collectLatest {
                act.firstInput.text = it.toString()
                Log.v(TAG, "Done collection input1")
            }
        }
        viewModelScope.launch {
            prefs.input2.collectLatest {
                act.secondInput.text = it.toString()
                Log.v(TAG, "Done collection input2")
            }
        }
        viewModelScope.launch {
            prefs.input3.collectLatest {
                act.thirdInput.text = it.toString()
                Log.v(TAG, "Done collection input3")
            }
        }
        viewModelScope.launch {
            prefs.input4.collectLatest {
                act.fourthInput.text = it.toString()
                Log.v(TAG, "Done collection input4")
            }
        }
    }

    fun saveInput(s: String, index: Int) {
        this.viewModelScope.launch {
            prefs.saveInput(s, index)
            Log.v(TAG, "Done saving input #$index")
        }
    }
}