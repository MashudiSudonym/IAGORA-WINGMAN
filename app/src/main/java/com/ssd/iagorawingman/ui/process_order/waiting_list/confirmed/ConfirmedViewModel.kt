package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmedViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {


    private val _vmGetConfirmedList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1,1,BufferOverflow.DROP_OLDEST)
    val vmGetConfirmedList = _vmGetConfirmedList.distinctUntilChanged()

    private val _vmCountSizeConfirmedList: MutableSharedFlow<Int> = MutableSharedFlow()
    val vmCountSizeConfirmedList = _vmCountSizeConfirmedList.distinctUntilChanged()


    fun initViewModelConfirmed() {
        viewModelScope.launch {
            orderUseCase.getAllListWaiting(FlowProcessOrder.CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmedList.emit(res)
                    _vmCountSizeConfirmedList.emit(res.data?.success?.size ?: 0)
                }
            Log.e("LAUNCH","CONFIRMED")
        }

    }
}