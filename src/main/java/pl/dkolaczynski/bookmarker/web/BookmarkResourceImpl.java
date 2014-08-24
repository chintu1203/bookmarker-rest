package pl.dkolaczynski.bookmarker.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import pl.dkolaczynski.bookmarker.domain.Bookmark;

@Path("/customers")
public class BookmarkResourceImpl {

	public BookmarkResourceImpl() {
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput getCustomer(@PathParam("id") int id) {
		final Bookmark bookmark = new Bookmark();
		bookmark.setName("Strona testowa");
		bookmark.setUrl("http://www.test.com");
		bookmark.setId(1L);

		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomer(outputStream, bookmark);
			}
		};
	}

	protected void outputCustomer(OutputStream os, Bookmark bookmark) throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<bookmark id=\"" + bookmark.getId() + "\">");
		writer.println("   <name>" + bookmark.getName() + "</name>");
		writer.println("   <url>" + bookmark.getUrl() + "</url>");
		writer.println("</bookmark>");
	}

}
