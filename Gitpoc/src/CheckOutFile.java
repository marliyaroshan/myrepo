import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

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


public class CheckOutFile {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException,
			InvalidRemoteException, TransportException, GitAPIException {
		try {
			final String login = "s9qaqf";
			final String password = "rbc@2014";
			String loginPassword = login + ":" + password;
			String restUrl = "http://git-dev.swissre.com/rest/api/1.0/projects/SCMPlat/repos/DCSCM/browse/add-indexed-branch.cmd?at=Z_1_0_0_24";
			String auth = new String(Base64.encode(loginPassword));
			// String encoded = new
			// sun.misc.BASE64Encoder().encode(loginPassword
			// .getBytes());
			Client client = Client.create();
			System.out.println("After Client creation");
			WebResource webResource = client.resource(restUrl);

			System.out.println("After web resource initailisation");
			ClientResponse response = webResource
					.header("Authorization", "Basic " + auth)
					.type("application/json").accept("application/json")
					.get(ClientResponse.class);
			System.out.println("After client response settings"+response.getType());
			int statusCode = response.getStatus();
			if (statusCode >= 200 && statusCode <= 400) {
				System.out.println("valid response code");
			} else if (statusCode == 401) {
				System.out.println("Invalid Username or Password");
			} else {
				System.out.println("Invalid response code");
			}
			String json_output = response.getEntity(String.class);
			 File file = null;
		       OutputStream out = null;
		       file = new File("C:/Users/s4gzgk/add-indexed-branch.cmd");
	            out = new FileOutputStream(file);
			System.out.println("Server response .... \n");
			System.out.println("json output:" + json_output);
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json_output);
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println("The size of json object" + jsonObject.size());
			System.out.println(jsonObject.values());
			System.out.println("The  lines is present or not:"
					+ jsonObject.containsKey("lines"));
			JSONArray jsonArray = (JSONArray) jsonObject.get("lines");
			if (jsonArray != null) {
				Iterator itr = jsonArray.iterator();
				ArrayList<JSONObject> list = new ArrayList<JSONObject>();
				while (itr.hasNext()) {
					list.add((JSONObject) itr.next());
				}
				for (int i = 0; i < list.size(); i++) {
					JSONObject test = list.get(i);
					String writeObj =null;
					if (test.get("text") != null) {
						writeObj =test.get("text")+"\r\n";
						System.out.println("writeObj"+writeObj);
			            out.write(writeObj.getBytes());
					}
				}
			} else {
				System.out.println("The object value is null");
			}
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
}
