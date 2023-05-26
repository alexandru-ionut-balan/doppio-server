package io.coffee.tools.doppio.members.responses

import com.fasterxml.jackson.annotation.JsonInclude
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(name = "Member Response Body")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MemberResponse(
    @field:Schema(
        description = "The first name/names of the user.",
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
        description = "Unique identifier chosen by the user when registering.",
        required = true,
        example = "alexandru-ionut-balan"
    )
    val username: String
)
