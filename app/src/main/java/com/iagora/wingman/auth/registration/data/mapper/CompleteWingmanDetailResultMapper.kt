package com.iagora.wingman.auth.registration.data.mapper

import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailResultDTO
import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailResult

fun CompleteWingmanDetailResultDTO.toCompleteWingmanDetailResult(): CompleteWingmanDetailResult {
    return CompleteWingmanDetailResult(
        wingmanDetail = wingmanDetail.toWingmanDetail(),
        id = id,
        type = type,
        phone = phone,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun CompleteWingmanDetailResult.toCompleteWingmanDetailResultDTO(): CompleteWingmanDetailResultDTO {
    return CompleteWingmanDetailResultDTO(
        wingmanDetail = wingmanDetail.toWingmanDetailDTO(),
        id = id,
        type = type,
        phone = phone,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}