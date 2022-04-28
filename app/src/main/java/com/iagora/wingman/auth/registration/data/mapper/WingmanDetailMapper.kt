package com.iagora.wingman.auth.registration.data.mapper

import com.iagora.wingman.auth.registration.data.remote.dto.WingmanDetailDTO
import com.iagora.wingman.auth.registration.domain.model.WingmanDetail

fun WingmanDetailDTO.toWingmanDetail(): WingmanDetail {
    return WingmanDetail(
        name = name,
        email = email,
        address = address,
        city = city
    )
}

fun WingmanDetail.toWingmanDetailDTO(): WingmanDetailDTO {
    return WingmanDetailDTO(
        name = name,
        email = email,
        address = address,
        city = city
    )
}