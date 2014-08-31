package pl.dkolaczynski.bookmarker.domain;

public class Sorting {

	public static enum Order {
		DEFAULT, CREATED, LAST_VISIT, POPULARITY, RATING, ALPHABETICAL;
	}

	public static enum Direction {
		ASC, DESC;
	}

	private Order order;
	private Direction direction;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
