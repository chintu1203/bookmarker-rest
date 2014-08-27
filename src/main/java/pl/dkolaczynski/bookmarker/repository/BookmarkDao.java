package pl.dkolaczynski.bookmarker.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

public class BookmarkDao {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkDao.class);

	@PersistenceContext(unitName = "mainPU")
	private EntityManager em;
	

	public BookmarkDao() {
		;
	}

	public Bookmark findBookmark(long id) {
		Bookmark bookmark = em.find(Bookmark.class, id);

		return bookmark;
	}

}
