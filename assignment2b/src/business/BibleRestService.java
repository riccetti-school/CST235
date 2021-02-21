package business;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/bible")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BibleRestService {

	private String phrase = "Here I am! I stand at the door and knock. If anyone hears my voice and opens the door, I will come in and eat with them, and they with me.";
	private String section = "Revelation 3:20";
	
	@GET
	@Path("/getVerse")
	@Produces(MediaType.APPLICATION_JSON)	
	public Object getVerse() {
		return new Object() {
			public String verse = section;
		};
	}
	
	@POST
	@Path("/postVerse")
	@Produces(MediaType.APPLICATION_JSON)	
	public Object postVerse() {
		return new Object() {
			public String verse = section;
		};
	}	
	
	@GET
	@Path("/getWordOccurance")
	@Produces(MediaType.APPLICATION_JSON)	
	public Object getWordOccurance(@Context UriInfo info) {
		String word = info.getQueryParameters().getFirst("word");
		
		if(word == null) {
			word = "";
		}
		
		int i = phrase.indexOf(word);
		return new Object() {
			public int occurance = i;
		};
	}
	
	/**
	 * util to count words
	 * @param str
	 * @param word
	 * @return
	 */
	private static int countOccurences(String str, String word)  
	{ 
	    // split the string by spaces in a 
	    String a[] = str.split(" "); 
	  
	    // search for pattern in a 
	    int count = 0; 
	    for (int i = 0; i < a.length; i++)  
	    { 
		    // if match found increase count 
		    if (word.equals(a[i])) 
		        count++; 
	    } 
	  
	    return count; 
	} 	
	
	@GET
	@Path("/getNumberOfWordOccurance")
	@Produces(MediaType.APPLICATION_JSON)		
	public Object getNumberOfWordOccurance(@Context UriInfo info) {
		String word = info.getQueryParameters().getFirst("word");
		if(word == null) {
			word = "";
		}
		int count = countOccurences(phrase, word);
		return new Object() {
			public int cnt = count;
		};
	}
}
