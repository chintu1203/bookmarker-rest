package pl.dkolaczynski.bookmarker.repository;

public class AbstractDao {
	
	protected static final boolean isSuccessful(int affected) {
		return affected > 0;
	}
	
}
