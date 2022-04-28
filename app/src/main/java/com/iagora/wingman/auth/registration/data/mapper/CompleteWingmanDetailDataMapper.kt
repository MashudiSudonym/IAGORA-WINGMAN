package com.iagora.wingman.auth.registration.data.mapper

import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailDataDTO
import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailData

fun CompleteWingmanDetailDataDTO.toCompleteWingmanDetailData(): CompleteWingmanDetailData {
    return CompleteWingmanDetailData(
        name = name,
        email = email,
        address = address,
        city = city
    )
}

fun CompleteWingmanDetailData.toCompleteWingmanDetailDataDTO(): CompleteWingmanDetailDataDTO {
    return CompleteWingmanDetailDataDTO(
        name = name,
        email = email,
        address = address,
        city = city
    )
}