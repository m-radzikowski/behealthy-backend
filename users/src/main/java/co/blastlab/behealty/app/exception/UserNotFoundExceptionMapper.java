package co.blastlab.behealty.app.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

	@Override
	public Response toResponse(UserNotFoundException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
			.entity("User not found")
			.build();
	}
}
