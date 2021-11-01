package com.ssd.iagorawingman.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.remote.api_handle.auth.AuthApi
import com.ssd.iagorawingman.data.source.remote.api_handle.main_menu.MainMenuApi
import com.ssd.iagorawingman.data.source.remote.api_handle.pasar.PasarApi
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.OnProcessApi
import com.ssd.iagorawingman.data.source.remote.api_handle.receive_order.ReceiveOrderApi
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

val apiModule = module {
    single { provideMainMenuApi(get()) }
    single { provideAuthApi(get()) }
    single { providePasarApi(get()) }
    single { provideReceiveOrderApi(get()) }
    single { provideOnProcessApi(get()) }

}

private fun provideMainMenuApi(retrofit: Retrofit): MainMenuApi =
    retrofit.create(MainMenuApi::class.java)

private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
private fun providePasarApi(retrofit: Retrofit): PasarApi = retrofit.create(PasarApi::class.java)
private fun provideReceiveOrderApi(retrofit: Retrofit): ReceiveOrderApi =
    retrofit.create(ReceiveOrderApi::class.java)

private fun provideOnProcessApi(retrofit: Retrofit): OnProcessApi =
    retrofit.create(OnProcessApi::class.java)
