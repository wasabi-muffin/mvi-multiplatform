package com.gmvalentino.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    @SerialName(value = "msg_id") val msgId: String? = null,
    @SerialName(value = "msg_title") val msgTitle: String? = null,
    @SerialName(value = "msg") val msg: String? = null
)