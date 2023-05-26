package io.coffee.tools.doppio.members

import io.coffee.tools.doppio.common.responses.NotFoundResponse
import io.coffee.tools.doppio.members.requests.MemberRegistrationRequest
import io.coffee.tools.doppio.members.requests.toMember
import io.coffee.tools.doppio.members.responses.MemberResponse
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import java.net.URI

@Path("/members")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class MemberResource {

    @POST
    fun register(memberRegistrationRequest: MemberRegistrationRequest): Uni<RestResponse<Unit>> {
        return memberRegistrationRequest.toMember()
            .persist<Member>()
            .map { RestResponse.created(URI.create("http://localhost:8080/members/${it.id}")) }
    }

    @GET
    @Path("{id}")
    fun get(@PathParam(value = "id") id: Long): Uni<RestResponse<MemberResponse>> {
        return Member.findById(id)
            .onItem().ifNull().failWith(NotFoundException("Cannot find a member with id $id"))
            .onItem().ifNotNull().transform { it?.toMemberResponse() }.map { RestResponse.ok(it) }
    }

    @ServerExceptionMapper
    fun mapNotFoundException(e: NotFoundException): Uni<RestResponse<NotFoundResponse>> {
        return Uni.createFrom().item(
            RestResponse.status(RestResponse.Status.NOT_FOUND, NotFoundResponse(e.message.toString()))
        )
    }
}