package com.example.livedemo_datastores01

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


class MyPreferencesRepository (_datastore: DataStore<Preferences>)
{
    private val dataStore = _datastore

    val input1: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[INPUT1_KEY] ?: ""
    }.distinctUntilChanged()
    val input2: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[INPUT2_KEY] ?: ""
    }.distinctUntilChanged()
    val input3: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[INPUT3_KEY] ?: ""
    }.distinctUntilChanged()
    val input4: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[INPUT4_KEY] ?: ""
    }.distinctUntilChanged()

    suspend fun saveInput(value: String, index: Int) {
        val key: Preferences.Key<String> = when (index) {
            1 -> INPUT1_KEY
            2 -> INPUT2_KEY
            3 -> INPUT3_KEY
            4 -> INPUT4_KEY
            else -> {
                throw NoSuchFieldException("Invalid input index: $index")
            }
        }

        this.saveStringValue(key, value)
    }

    private suspend fun saveStringValue(key: Preferences.Key<String>, value: String) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "settings"
        private val INPUT1_KEY = stringPreferencesKey("input1")
        private val INPUT2_KEY = stringPreferencesKey("input2")
        private val INPUT3_KEY = stringPreferencesKey("input3")
        private val INPUT4_KEY = stringPreferencesKey("input4")

        private var INSTANCE: MyPreferencesRepository? = null

        fun get(): MyPreferencesRepository {
            return INSTANCE ?: throw IllegalStateException("MyPreferencesRepository has not yet been initialized.")
        }

        fun initialize(context: Context) {
            if ( INSTANCE == null ) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = MyPreferencesRepository(dataStore)
            }
        }
    }
}