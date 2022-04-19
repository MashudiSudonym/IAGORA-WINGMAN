package com.iagora.wingman.auth.ssid.domain.use_case.get_wingman_ssid_use_case

import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository
import com.iagora.wingman.common.util.SimpleResource
import kotlinx.coroutines.flow.Flow

class GetWingmanSSIDUseCase(private val wingmanSSIDRepository: WingmanSSIDRepository) {
    suspend operator fun invoke(): Flow<SimpleResource> {
        return wingmanSSIDRepository.getWingmanSSID()
    }
}