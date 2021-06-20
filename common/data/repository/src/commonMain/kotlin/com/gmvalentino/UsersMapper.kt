package com.gmvalentino

import com.gmvalentino.models.User
import com.gmvalentino.entities.UserEntity

object UsersMapper {
    fun toEntity(model: User): UserEntity = UserEntity(
        id = model.login.uuid,
        name = "${model.name.first} ${model.name.last}",
        email = model.email,
        image = model.picture.medium,
        age = model.dob.age,
        phone = model.phone
    )
}
