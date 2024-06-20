package com.example.bdapimodulo6wallet.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bdapimodulo6wallet.data.response.UserInfoResponse

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val points: Int,
    val roleId: Int
)

fun UserInfoResponse.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        email = this.email,
        firstName = this.first_name,
        lastName = this.last_name,
        password = this.password,
        points = this.points,
        roleId = this.roleId
    )
}

fun UserEntity.toUserInfoResponse(): UserInfoResponse {
    return UserInfoResponse(
        id = this.id,
        email = this.email,
        first_name = this.firstName,
        last_name = this.lastName,
        password = this.password,
        points = this.points,
        roleId = this.roleId
    )
}