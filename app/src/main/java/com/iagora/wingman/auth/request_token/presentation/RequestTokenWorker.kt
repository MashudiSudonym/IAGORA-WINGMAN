package com.iagora.wingman.auth.request_token.presentation

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class RequestTokenWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val requestTokenWorkerDependency: RequestTokenWorkerDependency
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            requestTokenWorkerDependency.saveTokenToDataStoreUseCase
            Timber.i("Worker it's working")
            Result.success()
        } catch (e: Throwable) {
            Timber.i("Worker it's not working, retrying...")
            Result.retry()
        }
    }
}