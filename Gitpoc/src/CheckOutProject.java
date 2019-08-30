import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;


public class CheckOutProject {
	public static void main(String[] args) throws IOException,
			InvalidRemoteException, TransportException, GitAPIException {
		try {
			 File file = null;
		       OutputStream out = null;
		       
			final String login = "s9qaqf";
			final String password = "rbc@2014";
			String loginPassword = login + ":" + password;
			String restUrl = "http://git-dev.swissre.com/rest/api/1.0/projects/SCMPlat/repos/DCSCM/browse/Gitpoc?at=tag_for_testing";
			String auth = new String(Base64.encode(loginPassword));
			Client client = Client.create();
			System.out.println("After Client creation");
			WebResource webResource = client.resource(restUrl);
			System.out.println("After web resource initailisation");
			ClientResponse response = webResource
					.header("Authorization", "Basic " + auth)
					.type("application/json").accept("application/json")
					.get(ClientResponse.class);
			String result = response.getEntity(String.class);
			System.out.println(result);
			int statusCode = response.getStatus();
			if (statusCode >= 200 && statusCode <= 400) {
				System.out.println("valid response code");
			} else if (statusCode == 401) {
				System.out.println("Invalid Username or Password");
			} else {
				System.out.println("Invalid response code");
			}
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	
	private boolean creatFile(String url){
		 File file = null;
	       OutputStream out = null;
	       
		final String login = "s9qaqf";
		final String password = "rbc@2014";
		String loginPassword = login + ":" + password;
		String restUrl = "http://git-dev.swissre.com/projects/SCMPLAT/repos/dcscm/browse/Gitpoc?at=tag_for_testing&raw";
		String auth = new String(Base64.encode(loginPassword));
		Client client = Client.create();
		System.out.println("After Client creation");
		WebResource webResource = client.resource(restUrl);

		System.out.println("After web resource initailisation");
		ClientResponse response = webResource
				.header("Authorization", "Basic " + auth)
				//.type("application/json").accept("application/json")
				.get(ClientResponse.class);
		String result = response.getEntity(String.class);
		return true;
	}
	
	
	private boolean creatFolder(String url){
		 File file = null;
	       OutputStream out = null;
	       
		final String login = "s9qaqf";
		final String password = "rbc@2014";
		String loginPassword = login + ":" + password;
		String restUrl = "http://git-dev.swissre.com/projects/SCMPLAT/repos/dcscm/browse/Gitpoc?at=tag_for_testing&raw";
		String auth = new String(Base64.encode(loginPassword));
		Client client = Client.create();
		System.out.println("After Client creation");
		WebResource webResource = client.resource(restUrl);

		System.out.println("After web resource initailisation");
		ClientResponse response = webResource
				.header("Authorization", "Basic " + auth)
				//.type("application/json").accept("application/json")
				.get(ClientResponse.class);
		String result = response.getEntity(String.class);
		return true;
	}
}
