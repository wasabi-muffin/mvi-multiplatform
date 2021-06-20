package com.gmvalentino.models

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    val results: List<User>,
    val info: Info
)

@Serializable
data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

@Serializable
data class User(
    val name: Name,
    val email: String,
    val login: Login,
    val dob: Dob,
    val phone: String,
    val picture: Picture
)

@Serializable
data class Name(
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

@Serializable
data class Dob(
    val date: String,
    val age: Int
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
