package io.coffee.tools.doppio.members

import io.coffee.tools.doppio.members.responses.MemberResponse
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Member : PanacheEntity() {
    var firstName: String? = null

    var lastName: String? = null

    @Column(unique = true, nullable = false)
    var username: String? = null

    @Column(nullable = false)
    var password: String? = null

    fun toMemberResponse() = username?.let { MemberResponse(firstName = firstName, lastName = lastName, username = it) }

    companion object : PanacheCompanion<Member>
}