import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

//import com.sun.xml.internal.messaging.saaj.util.Base64;

//import com.sun.org.apache.xml.internal.security.utils.Base64;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ConnectionTest {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException,
			InvalidRemoteException, TransportException, GitAPIException {
		try {

			String loginPassword = login + ":" + password;
			String restUrl = "http://git-dev.swissre.com/rest/api/1.0/projects/SCMPlat/repos/DCSCM/tags";
			String auth = new String(Base64.encode(loginPassword));
			// String encoded = new
			// sun.misc.BASE64Encoder().encode(loginPassword
			// .getBytes());
			Client client = Client.create();
			System.out.println("After Client creation");
			WebResource webResource = client.resource(restUrl);
			System.out.println("After web resource initailisation");
			ClientResponse response = webResource
					//.header("Authorization", "Basic " + auth)
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
			System.out.println("Server response .... \n");
			System.out.println("json output:" + json_output);
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json_output);
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println("The size of json object" + jsonObject.size());
			System.out.println("The  size is present or not:"
					+ jsonObject.containsKey("size"));
			System.out.println("The values is present or not:"
					+ jsonObject.containsKey("values"));
			JSONArray jsonArray = (JSONArray) jsonObject.get("values");
			// JSONObject innerJsonObj = (JSONObject) jsonObject.get("values");
			if (jsonArray != null) {
				Iterator itr = jsonArray.iterator();
				ArrayList<JSONObject> list = new ArrayList<JSONObject>();
				while (itr.hasNext()) {
					list.add((JSONObject) itr.next());
				}
				System.out.println("The display id value:" + list.size());
				System.out.println("tags");
				for (int i = 0; i < list.size(); i++) {
					JSONObject test = list.get(i);
					// System.out.println(list.get(i));

					if (test.get("displayId") != null) {

						System.out.println(test.get("displayId"));
					}

				}
			} else {
				System.out.println("The object value is null");
			}
			/*
			 * ArrayList<String> resultList = new ArrayList<String>(); for(int
			 * i=0; i<jsonArray.l; i++){
			 * resultList.add(jsonArray.getJSONObject(i
			 * ).getString("displayId")); }
			 */
			// System.out.println("The size of teh list is:"+resultList.size());
			// JSONObject jsonObject = new JSONObject(json_output);
			// JSONObject myResponse = jsonObject.getJSONObject("displayId");
			// System.out.println("The response id json object"+myResponse);
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}
