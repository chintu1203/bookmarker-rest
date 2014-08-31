package pl.dkolaczynski.bookmarker.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

public class BookmarkDao extends AbstractDao {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkDao.class);

	@PersistenceContext(unitName = "mainPU")
	private EntityManager em;

	@Transactional
	public void createBookmark(Bookmark bookmark) {
		em.persist(bookmark);
	}

	public Bookmark findById(long id) {
		return em.find(Bookmark.class, id);
	}

	public List<Bookmark> findAll() {
		return null; // TODO
	}

	private static final String DELETE_BOOKMARK =
			"DELETE FROM Bookmark b WHERE b.id = :id";

	@Transactional
	public boolean deleteBookmark(long id) {
		int deleted = em.createQuery(DELETE_BOOKMARK)
				.setParameter("id", id)
				.executeUpdate();

		return isSuccessful(deleted);
	}

	private static final String COUNT_BOOKMARKS =
			"SELECT COUNT(b.id) FROM Bookmark b";

	public long count() {
		return em.createQuery(COUNT_BOOKMARKS, Long.class).getSingleResult(); 
	}

}
