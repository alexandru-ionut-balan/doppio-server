package io.coffee.tools.doppio.members.requests

import io.coffee.tools.doppio.members.Member
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(name = "Member Registration Body")
data class MemberRegistrationRequest(
    @field:Schema(
        description = "The fist name/names of the user.",
        required = false,
        example = "Alexandru Ionut"
    )
    val firstName: String?,

    @field:Schema(
        description = "The last name of the user.",
        required = false,
        example = "Balan"
    )
    val lastName: String?,

    @field:Schema(
        description = "Uniquely identifying username.",
        required = true,
        example = "alexandru-ionut-balan"
    )
    val username: String,

    @field:Schema(
        description = "A strong and complicated password to keep your account safe.",
        minLength = 8,
        required = true,
        example = "complicated-pass-123"
    )
    val password: String
)

fun MemberRegistrationRequest.toMember() = Member().apply {
    this.firstName = this@toMember.firstName
    this.lastName = this@toMember.lastName
    this.username = this@toMember.username
    this.password = this@toMember.password
}