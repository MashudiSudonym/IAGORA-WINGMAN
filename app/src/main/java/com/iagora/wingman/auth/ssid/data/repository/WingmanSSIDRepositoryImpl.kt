package com.iagora.wingman.auth.ssid.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.ssid.data.remote.WingmanSSIDAPI
import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

class WingmanSSIDRepositoryImpl(private val wingmanSSIDAPI: WingmanSSIDAPI) :
    WingmanSSIDRepository {
    override suspend fun getWingmanSSID(): String? {
        return try {
            // get sessid from header response
            wingmanSSIDAPI.getWingmanSSID(Constants.AUTH_VALUE)
                .headers()[Constants.SESSID].toString()
        } catch (e: HttpException) {
            Timber.e(e.message())
            null
        } catch (e: IOException) {
            Timber.e(e)
            null
        } catch (e: UnknownHostException) {
            Timber.e(e)
            null
        }
    }
}