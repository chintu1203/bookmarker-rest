package pl.dkolaczynski.bookmarker.provider;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResteasyViolationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResteasyViolationExceptionMapper.class);

//	{
//	  "exception": null,
//	  "fieldViolations": [],
//	  "propertyViolations": [],
//	  "classViolations": [],
//	  "parameterViolations": [
//	    {
//	      "constraintType": "PARAMETER",
//	      "path": "showBookmarks.arg0.test",
//	      "message": "Ojejku!",
//	      "value": ""
//	    }
//	  ],
//	  "returnValueViolations": []
//	}

	@Override
	public Response toResponse(ResteasyViolationException exception) {
		LOGGER.warn("custom mapper launched!");
		Map<String, String> violations = new HashMap<>();

		for (ResteasyConstraintViolation violation : exception.getViolations()) {
			violations.put(getField(violation.getPath()), violation.getMessage());
		}

		return Response.status(Status.BAD_REQUEST).entity(violations).build();
	}

	private static String getField(String path) {
		return path.substring(path.lastIndexOf('.') + 1);
	}

}
