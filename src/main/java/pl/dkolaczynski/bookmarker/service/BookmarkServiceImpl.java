package pl.dkolaczynski.bookmarker.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkServiceImpl {
	
	@PersistenceContext(unitName = "mainPU")
	private EntityManager em;

	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkServiceImpl.class);

	public BookmarkServiceImpl() {
		LOGGER.warn("$$$$$$$$$$$$$$ initialized!");
	}
	
	public void doSomething() {
		LOGGER.warn("entity manager: {}", em);
	}

}
