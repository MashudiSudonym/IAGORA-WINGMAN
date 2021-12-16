package com.iagora.wingman.receive_order.core

import com.iagora.wingman.core.source.remote.network.ApiResponse
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.core.domain.repository.IReceiveOrderRepository
import com.iagora.wingman.receive_order.core.source.remote.ReceiveOrderRemoteDataSource
import com.iagora.wingman.receive_order.core.mapper.MapperAcceptedOrder.toModel
import com.iagora.wingman.receive_order.core.mapper.MapperReceiveOrder.toBody
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.helper.model.response.AcceptedOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ReceiveOrderRepository(
    private val receiveOrderRemoteDataSource: ReceiveOrderRemoteDataSource,
) : IReceiveOrderRepository {

    override fun postActionOrder(
        action: String,
        receiveOrder: ReceiveOrder,
    ): Flow<Resource<AcceptedOrder>> = flow {
        emit(Resource.loading("true", null))
        when (val response =
            receiveOrderRemoteDataSource.postActionOrder(action,
                receiveOrder.toBody()
            ).first()
        ) {
            is ApiResponse.Success -> emit(
                Resource.success(
                    response.data.toModel()
                )
            )
            is ApiResponse.Error -> emit(Resource.error(
                response.errorMessage,
                null))
        }

    }
}