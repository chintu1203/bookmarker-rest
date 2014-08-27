package pl.dkolaczynski.bookmarker.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/")
public class RestApplication extends Application {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

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
