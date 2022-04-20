package com.iagora.wingman.auth.ssid.domain.repository

import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow

interface WingmanSSIDRepository {
    suspend fun getWingmanSSID(): String?
}