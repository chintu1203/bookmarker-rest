package pl.dkolaczynski.bookmarker.web;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

@Path("/bookmarks")
public interface BookmarkResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response createBookmark(Bookmark bookmark);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Bookmark showBookmark(@Valid @PathParam("id") long id);

	@DELETE
	@Path("{id}")
	Response deleteBookmark(@PathParam("id") long id);

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	long countAllBookmarks();

}
