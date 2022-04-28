package com.iagora.wingman.auth.registration.data.mapper

import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailResponseDTO
import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailResponse

fun CompleteWingmanDetailResponseDTO.toCompleteWingmanDetailResponse(): CompleteWingmanDetailResponse {
    return CompleteWingmanDetailResponse(
        status = status,
        message = message,
        result = result.toCompleteWingmanDetailResult()
    )
}

fun CompleteWingmanDetailResponse.toCompleteWingmanDetailResponseDTO(): CompleteWingmanDetailResponseDTO {
    return CompleteWingmanDetailResponseDTO(
        status = status,
        message = message,
        result = result.toCompleteWingmanDetailResultDTO()
    )
}