package pl.dkolaczynski.bookmarker.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;
import pl.dkolaczynski.bookmarker.service.BookmarkService;

@ApplicationScoped
public class BookmarkResourceImpl implements BookmarkResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkResourceImpl.class);

	@Inject
	private BookmarkService bookmarkService;

	public BookmarkResourceImpl() {
		LOGGER.warn("bookmark init");
	}

	@Override
	public Response createBookmark(Bookmark bookmark) {
		LOGGER.warn("Bookmark: {}", bookmark);
		bookmarkService.saveBookmark(bookmark);

		return Response.status(Status.CREATED).build();
	}

	@Override
	public Bookmark showBookmark(long id) {
		Bookmark bookmark = bookmarkService.getBookmark(id);

		if (bookmark == null)
			throw new NotFoundException();

		return bookmark;
	}

	@Override
	public Response deleteBookmark(long id) {
		boolean deleted = bookmarkService.deleteBookmark(id);
		Response.Status status = deleted ? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}

	@Override
	public long countAllBookmarks() {
		return bookmarkService.count();
	}

}
