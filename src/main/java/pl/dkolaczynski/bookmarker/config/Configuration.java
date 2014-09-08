package pl.dkolaczynski.bookmarker.config;

import pl.dkolaczynski.bookmarker.domain.PageCriteria;

public final class Configuration {

	public static final String DEFAULT_BOOKMARK_RESULT = "18";

	public static final long MAX_BOOKMARK_RESULTS = 36;

	/** @see {@link PageCriteria.Direction} */
	public static final String DEFAULT_DIRECTON = "ASC";

	/** @see {@link PageCriteria.Order} */
	public static final String DEFAULT_ORDER = "DEFAULT";

	private Configuration() {
	}

}
