package pl.dkolaczynski.bookmarker.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

@Path("/customers")
public interface BookmarkResource {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Bookmark showBookmark(@PathParam("id") long id);

}
