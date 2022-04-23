package com.iagora.wingman.auth.otp.domain.use_case.count_down_timer_use_case

import com.iagora.wingman.common.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class CountDownTimerUseCase {
    suspend operator fun invoke(): Flow<Int> {
        return (Constants.START_COUNT_DOWN - 1 downTo 0).asFlow()
            .onEach { delay(1000L) }
            .onStart { emit(Constants.START_COUNT_DOWN) }
            .conflate()
            .transform { remainingSeconds ->
                emit(remainingSeconds)
            }
    }
}