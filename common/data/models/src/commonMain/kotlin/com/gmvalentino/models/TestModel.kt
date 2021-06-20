package com.gmvalentino.models

import kotlinx.serialization.Serializable

@Serializable
data class TestModel(
    val name: String,
    val email: String
)