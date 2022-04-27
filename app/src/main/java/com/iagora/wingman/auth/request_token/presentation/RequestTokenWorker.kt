package com.iagora.wingman.auth.request_token.presentation

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class RequestTokenWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val requestTokenWorkerDependency: RequestTokenWorkerDependency
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            requestTokenWorkerDependency.saveTokenToDataStoreUseCase
            Result.success()
        } catch (e: Throwable) {
            Result.retry()
        }
    }
}