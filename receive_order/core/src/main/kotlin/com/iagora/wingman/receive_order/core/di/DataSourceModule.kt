package com.iagora.wingman.receive_order.core.di

import com.iagora.wingman.receive_order.core.data.remote.ReceiveOrderRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        ReceiveOrderRemoteDataSource(get(),
            get())
    }
}