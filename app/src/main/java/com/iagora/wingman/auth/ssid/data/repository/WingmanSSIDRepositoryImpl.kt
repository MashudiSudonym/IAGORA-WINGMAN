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
                val sessid = wingmanSSIDAPI.getWingmanSSID(Constants.AUTH_VALUE)
                    .headers()[Constants.SESSID]

                Timber.d(sessid)

                emit(Resource.Success(sessid))
            } catch (e: HttpException) {
                Timber.e(e.message())
                emit(Resource.Error(
                    message = UIText.StringResource(R.string.internet_problem)
                ))
            } catch (e: IOException) {
                Timber.e(e)
                emit(Resource.Error(
                    message = UIText.StringResource(R.string.internet_problem)
                ))
            } catch (e: UnknownHostException) {
                Timber.e(e)
                emit(Resource.Error(
                    message = UIText.StringResource(R.string.error_unknown)
                ))
            }
        }
    }
}