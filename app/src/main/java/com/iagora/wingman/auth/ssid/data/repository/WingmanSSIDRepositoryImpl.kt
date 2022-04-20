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
    override suspend fun getWingmanSSID(): Flow<Resource<String>> {
        return flow {
            try {
                // get sessid from header response
                val sessid = wingmanSSIDAPI.getWingmanSSID(Constants.AUTH_KEY)
                    .headers()[Constants.SESSID]

                Timber.d(sessid)

                Resource.Success(sessid)
            } catch (e: HttpException) {
                Timber.e(e.message())
                Resource.Error(
                    message = UIText.StringResource(R.string.internet_problem),
                    data = null
                )
            } catch (e: IOException) {
                Timber.e(e)
                Resource.Error(
                    message = UIText.StringResource(R.string.internet_problem),
                    data = null
                )
            } catch (e: UnknownHostException) {
                Timber.e(e)
                Resource.Error(message = UIText.StringResource(R.string.error_unknown), data = null)
            }
        }
    }
}