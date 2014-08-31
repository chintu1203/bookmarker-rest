package pl.dkolaczynski.bookmarker.service;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;
import pl.dkolaczynski.bookmarker.repository.BookmarkDao;

public class BookmarkService {

	/** (key)=(value) */
	private static final Pattern DUPLICATE_VALUE = Pattern.compile("\\((\\w+)\\)=\\((.+)\\)");

	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkService.class);

	@Inject
	private BookmarkDao bookmarkDao;

	public void saveBookmark(Bookmark bookmark) {
		bookmark.setId(null);

		try {
			bookmarkDao.createBookmark(bookmark);
		} catch (PersistenceException ex) {
			LOGGER.info("Exception: {}", aaa(ex));

		}
	}

	public Bookmark getBookmark(long id) {
		return id <= 0 ? null : bookmarkDao.findById(id);
	}

	public boolean deleteBookmark(long id) {
		return id <= 0 ? false : bookmarkDao.deleteBookmark(id);
	}

	private static Entry<String, String> aaa(Exception ex) {
		Matcher matcher = DUPLICATE_VALUE.matcher(getMessage(ex));
		return matcher.find() ? new SimpleImmutableEntry<String, String>(matcher.group(1), matcher.group(2)) : null;
	}

	private static String getMessage(Exception ex) {
		return ExceptionUtils.getRootCause(ex).getMessage();
	}

	public long count() {
		return bookmarkDao.count();
	}

}
