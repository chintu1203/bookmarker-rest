package pl.dkolaczynski.bookmarker.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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

	public Bookmark showBookmark(long id) {
		LOGGER.warn("bookmark: {}", bookmarkService);
//		return null;
		return bookmarkService.getBookmark(id);
	}

}
