package pl.dkolaczynski.bookmarker.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;
import pl.dkolaczynski.bookmarker.repository.BookmarkDao;

public class BookmarkService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkService.class);

	public BookmarkService() {
		;
	}

	@Inject
	private BookmarkDao bookmarkDao;

	public Bookmark getBookmark(long id) {
		return id <= 0 ? null : bookmarkDao.findBookmark(id);
	}

}
