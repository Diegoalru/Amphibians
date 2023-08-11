package com.darssolutions.amphibians.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darssolutions.amphibians.domain.Amphibian
import com.darssolutions.amphibians.domain.AmphibianApiStatus
import com.darssolutions.amphibians.domain.AmphibiansApi
import kotlinx.coroutines.launch

class AmphibianViewModel : ViewModel() {

    private val _status = MutableLiveData<AmphibianApiStatus>()
    private val _amphibians = MutableLiveData<List<Amphibian>?>()
    private val _selectedAmphibian = MutableLiveData<Amphibian?>()

    val status: MutableLiveData<AmphibianApiStatus>
        get() = _status

    val amphibians: MutableLiveData<List<Amphibian>?>
        get() = _amphibians

    val selectedAmphibian: MutableLiveData<Amphibian?>
        get() = _selectedAmphibian

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibians.value = AmphibiansApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibians.value = listOf()
            }
        }
    }

    fun displayAmphibianDetails(amphibian: Amphibian) {
        _selectedAmphibian.value = amphibian
    }
}