package com.example.newzapp.ui.theme

import com.example.newzapp.DataClass.JsonConveter


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.size.ViewSizeResolver
import com.example.newzapp.DataBase.DDataBase
import com.example.newzapp.DataBase.Entity
import com.example.newzapp.DataClass.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class vieww(application: Application) : AndroidViewModel(application) {

    var content by mutableStateOf("")

    private val _result = MutableStateFlow<JsonConveter?>(null)
    val result: StateFlow<JsonConveter?> = _result

    private val apiKey = "pub_d7c8ed5d1f2443ffbc7b2b0d00cc1269"  //paste ap key here
    private val obj = ApiService.create()

    private var searchJob: Job? = null
    private var _lastSearch = ""

    private val state = MutableStateFlow<State_d?>(null)
    val staee: StateFlow<State_d?> = state

    private var isLoadingNextPage = false
    private var lastLoadedPage: String? = null
    val databasee = DDataBase.getDatabase(context = application)

    private val Tag = "result"


    var screen by mutableStateOf(true )
    private val _offline = MutableStateFlow<List<Entity>>(emptyList())
    val offline: StateFlow<List<Entity>> = _offline


    init {
        viewModelScope.launch {

            databasee.user_content().display().collect { c ->
                _offline.value = c
            }

        }
    }


    // PAGINATION
    fun nextpage(page: String?) {

        if (page == null || isLoadingNextPage || page == lastLoadedPage) return

        if (!isInternetAvailable(getApplication())) {
            state.value = State_d.Error("No Active Internet Connection")
            return
        }

        isLoadingNextPage = true
        lastLoadedPage = page

        viewModelScope.launch {

            try {

                delay(1000)

                val response = obj.getitem(
                    search = _lastSearch,
                    apikey = apiKey,
                    page = page
                )

                val currentList = _result.value?.results ?: emptyList()
                val updatedList = currentList + (response.results ?: emptyList())

                _result.value = response.copy(results = updatedList)

                Log.d(Tag, "NEXT PAGE SUCCESS")

            } catch (e: Exception) {

                Log.d(Tag, "NEXT PAGE ERROR ${e}")
                state.value = State_d.Error(e.toString())

            } finally {

                isLoadingNextPage = false
            }
        }
    }

    // SEARCH
    fun getall(news: String) {


        val newz = news.trim()

        _lastSearch = newz
        lastLoadedPage = null
        _result.value = null

        // cancel previous search
        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            try {

                state.value = State_d.loading
                if (!isInternetAvailable(getApplication())) {
                    delay(50)
                    state.value = State_d.Error("No Active Internet Connection")
                    return@launch

                }

                val response = obj.getitem(
                    search = newz,
                    apikey = apiKey,
                    page = null
                )


                _result.value = response
                state.value = State_d.Success



                Log.d(Tag, "NEW SEARCH SUCCESS")

            } catch (e: Exception) {

                Log.d(Tag, "SEARCH ERROR ${e}")

                state.value = State_d.Error(e.toString())

            }
        }
    }


    val repositry = Repositry(context = application)

    val _toaast = MutableSharedFlow<String>()
    var toaast = _toaast.asSharedFlow()

    fun insert(title: String, Description: String, url: String) {
        viewModelScope.launch {
            val duplicate = databasee.user_content().check(title, Description)
            if (duplicate) {
                _toaast.emit("Already Saved !! ")
            } else {
                repositry.insertentity(title = title, Description = Description, httpurl = url)
                _toaast.emit("Added Successfully")
            }
        }
    }

    fun delete(Id: Int) {
        viewModelScope.launch {
            repositry.deleteentity(Id)
        }
    }


    fun isInternetAvailable(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val capabilities =
            connectivityManager.getNetworkCapabilities(network)
                ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    var id by mutableStateOf("")
    fun artichle(): Result? {
        try {
            return _result.value?.results?.find { it.article_id == id }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

}