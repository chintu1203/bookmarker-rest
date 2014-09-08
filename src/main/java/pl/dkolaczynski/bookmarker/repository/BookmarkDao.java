package pl.dkolaczynski.bookmarker.repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;
import pl.dkolaczynski.bookmarker.domain.PageCriteria;
import pl.dkolaczynski.bookmarker.domain.PageCriteria.Direction;
import pl.dkolaczynski.bookmarker.domain.PageCriteria.Order;

public class BookmarkDao extends AbstractDao {
	
	private static final Map<Order, String> order;
	
	static { // TODO add missing
		order = new EnumMap<PageCriteria.Order, String>(Order.class);
		order.put(Order.ALPHABETICAL, "name");
		order.put(Order.CREATED, "created");
		order.put(Order.DEFAULT, null);
		order.put(Order.RATING, "rating");
	}

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

	public List<Bookmark> findAll(PageCriteria criteria) {
		CriteriaBuilder query = em.getCriteriaBuilder();
		CriteriaQuery<Bookmark> select = query.createQuery(Bookmark.class);
		Root<Bookmark> from = select.from(Bookmark.class);
		select.select(from).orderBy(query.asc(from.get("name")));
		return em.createQuery(select).setFirstResult(criteria.getStart()).setMaxResults(criteria.getLimit()).getResultList();
	}

	private static javax.persistence.criteria.Order getOrder(PageCriteria criteria, CriteriaBuilder query,
			Root<Bookmark> from) {
		String orderBy = order.get(criteria.getOrder());

		if (orderBy != null) {
			Path<Object> path = from.get(orderBy);

			switch (criteria.getDirection()) {
			case ASC:
				return query.asc(path);
			case DESC:
				return query.desc(path);

			}
		}

		return null;
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
