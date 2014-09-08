package pl.dkolaczynski.bookmarker.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.dkolaczynski.bookmarker.domain.Bookmark;
import pl.dkolaczynski.bookmarker.domain.Page;
import pl.dkolaczynski.bookmarker.domain.PageCriteria;

@Path("/bookmarks")
public interface BookmarkResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response createBookmark(Bookmark bookmark);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
//	@NotNull(message = "{a.c.b.war}")
	Bookmark showBookmark(@PathParam("id") long id);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Page<Bookmark> showBookmarks(@Valid @BeanParam PageCriteria criteria);
 
	@DELETE
	@Path("{id}")
	Response deleteBookmark(@PathParam("id") long id);

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	long countAllBookmarks();

}
