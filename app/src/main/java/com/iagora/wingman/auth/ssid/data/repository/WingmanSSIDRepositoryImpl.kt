package com.iagora.wingman.auth.ssid.data.repository

import com.iagora.wingman.auth.ssid.data.remote.WingmanSSIDAPI
import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.SimpleResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

class WingmanSSIDRepositoryImpl(private val wingmanSSIDAPI: WingmanSSIDAPI) :
    WingmanSSIDRepository {
    override suspend fun getWingmanSSID(): Flow<SimpleResource> {
        return flow {
            try {
                // get sessid from header response
                val sessid = wingmanSSIDAPI.getWingmanSSID(Constants.AUTH_KEY)
                    .headers()[Constants.SESSID].toString()

                Timber.d(sessid)

                // TODO: Save sessid to local storage
            } catch (e: HttpException) {
                Timber.e(e.message())
            } catch (e: IOException) {
                Timber.e(e)
            } catch (e: UnknownHostException) {
                Timber.e(e)
            }
        }
    }
}