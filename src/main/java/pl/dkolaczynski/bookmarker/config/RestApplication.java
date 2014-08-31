package pl.dkolaczynski.bookmarker.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

@ApplicationPath("/")
public class RestApplication extends Application {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

	private static final String APP_URI = "http://localhost:8080/bookmarker-rest";

	public static void main(String[] args) throws Exception {

		Client client = ClientBuilder.newClient();

		try {
			Response response = null;
			Bookmark entity = new Bookmark();
			entity.setName("Test!");
			entity.setUrl("www.com");
			entity.setRating(2);
			entity.setFavorite(false);

			String ent = "{\"name\":\"sada\",\"url\":\"asd\",\"favorite\":true,\"rating\":1}";
			response = client.target(APP_URI + "/bookmarks").request().post(Entity.json(ent));
			System.out.println(response.getEntity());
			System.out.println(response.getStatus() + "");
			if (response.getStatus() != 201)
				throw new RuntimeException("Failed to create");
			response.close();

			System.out.println("** Testing RESTEasy:");
			String bookmark = client.target(APP_URI + "/bookmarks/1").request().get(String.class);
			System.out.println(bookmark);
			
			 response = client.target(APP_URI + "/bookmarks/1").request().delete();
			 System.out.println(response.getStatus() + "");
		} finally {
			client.close();
		}
	}

}
