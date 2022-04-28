package com.iagora.wingman.auth.request_token.presentation

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.common.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@HiltWorker
class RequestTokenWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val saveTokenToDataStoreUseCase: SaveTokenToDataStoreUseCase
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            saveTokenToDataStoreUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> Result.failure()
                    is Resource.Loading -> Unit
                    is Resource.Success -> Timber.d(result.data?.isTokenSaved.toString())
                }
            }
            Timber.i("Worker it's working")
            Result.success()
        } catch (e: Throwable) {
            Timber.i("Worker it's not working, retrying...")
            Result.retry()
        }
    }
}