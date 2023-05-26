package io.coffee.tools.doppio.common.responses

import java.time.LocalDateTime

data class NotFoundResponse(
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
