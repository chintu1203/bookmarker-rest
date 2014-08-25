package pl.dkolaczynski.bookmarker.config;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.service.BookmarkServiceImpl;
import pl.dkolaczynski.bookmarker.web.BookmarkResourceImpl;

@ApplicationPath("/")
public class RestApplication extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

	private final Set<Object> singletons = new HashSet<>();
	
	@Inject
	private BookmarkServiceImpl service;

	public RestApplication() {

		LOGGER.warn("@@@@@@@@@ initialize");
		singletons.add(new BookmarkResourceImpl());

	}

	@Override
	public Set<Object> getSingletons() {

		return singletons;
	}

	public static void main(String[] args) throws Exception {

		Client client = ClientBuilder.newClient();

		try {
			System.out.println("** Testing RESTEasy:");
			String bookmark = client.target("http://localhost:8080/bookmarker-rest/customers/1").request()
					.get(String.class);
			System.out.println(bookmark);
		} finally {
			client.close();
		}
	}

}
