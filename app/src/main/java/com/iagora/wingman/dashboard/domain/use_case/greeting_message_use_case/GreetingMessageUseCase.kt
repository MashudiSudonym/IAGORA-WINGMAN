package com.iagora.wingman.dashboard.domain.use_case.greeting_message_use_case

import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.dashboard.domain.model.GreetingMessageData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.*

class GreetingMessageUseCase {
    suspend operator fun invoke(): Flow<Resource<GreetingMessageData>> {
        return flow {
            val calendar = Calendar.getInstance()
            val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

            emit(Resource.Loading(true))

            try {

                emit(Resource.Loading(false))

                when (timeOfDay) {
                    in 0..11 -> emit(Resource.Success(GreetingMessageData(greeting = "Pagi")))
                    in 12..15 -> emit(Resource.Success(GreetingMessageData(greeting = "Siang")))
                    in 16..20 -> emit(Resource.Success(GreetingMessageData(greeting = "Sore")))
                    in 21..23 -> emit(Resource.Success(GreetingMessageData(greeting = "Malam")))
                    else -> emit(Resource.Success(GreetingMessageData(greeting = "-")))
                }
            } catch (e: Exception) {
                emit(Resource.Loading(false))
                emit(Resource.Error(UIText.unknownError()))
            }
        }
    }
}