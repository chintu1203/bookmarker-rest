package pl.dkolaczynski.bookmarker.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.dkolaczynski.bookmarker.config.Configuration;

@SuppressWarnings("serial")
public class PageCriteria implements Serializable {

	public static enum Order {

		DEFAULT, CREATED, LAST_VISIT, POPULARITY, RATING, ALPHABETICAL;

	}

	public static enum Direction {

		ASC, DESC;

	}

	@QueryParam("start")
	@Min(0)
	private int start;

	@QueryParam("limit")
	@DefaultValue(Configuration.DEFAULT_BOOKMARK_RESULT)
	@Min(0)
	@Max(Configuration.MAX_BOOKMARK_RESULTS)
	private int limit;

	@QueryParam("order")
	@DefaultValue(Configuration.DEFAULT_ORDER)
	private Order order;

	@QueryParam("direction")
	@DefaultValue(Configuration.DEFAULT_DIRECTON)
	private Direction direction;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

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
