import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;


public class CheckOutModule {
	final String destinationLocation ="c:/Users/s4gzgk/";
	public static void main(String[] args) throws IOException,
			InvalidRemoteException, TransportException, GitAPIException {
		try {
			CheckOutModule pullProject = new CheckOutModule();
			String repositoryPath = "Gitpoc";
			String fileType ="directory";
			String fileName = "";
			 String tagName=args[1];
			if(fileType.equalsIgnoreCase("directory")){
				pullProject.createDirectory(repositoryPath,fileType,fileName,tagName);
			}
		}catch(Exception e){
			System.out.println("Exception occured:");
			e.printStackTrace();
		}
	}



	private ClientResponse restCall(String repositoryPath, String fileType,
			String fileName,String tagName)  {
		System.out.println("inside restCall");
		final String login = "s9qaqf";
		final String password = "rbc@2014";
		String loginPassword = login + ":" + password;
		String restUrl = null;
		ClientResponse response = null;
		try{
			if(repositoryPath != null && fileName == "")
				restUrl="http://git-dev.swissre.com/rest/api/1.0/projects/SCMPlat/repos/DCSCM/browse/"+repositoryPath+"?at="+tagName;
			else if(repositoryPath != null && fileName != "") {
				if(fileType.equalsIgnoreCase("file"))
					restUrl="http://git-dev.swissre.com/projects/SCMPLAT/repos/dcscm/browse/"+repositoryPath+"/"+fileName+"?at="+tagName+"&raw";
				else if(fileType.equalsIgnoreCase("directory"))
					restUrl="http://git-dev.swissre.com/rest/api/1.0/projects/SCMPlat/repos/DCSCM/browse/"+repositoryPath+"?at="+tagName;
			}
			String auth = new String(Base64.encode(loginPassword));
			Client client = Client.create();
			//System.out.println("After Client creation"+restUrl);
			WebResource webResource = client.resource(restUrl);
			response = webResource
					.header("Authorization", "Basic " + auth)
					//.type("application/json").accept("application/json")
					.get(ClientResponse.class);
			
			return response;
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
		
	}

	@SuppressWarnings("rawtypes")
	private void parseResult(ClientResponse response,String tagName) throws ParseException {
		String jsonOutput = null;
		String repositoryPath = null;
		String fileName = null;
		String fileType = null;
		JSONParser parser0 = new JSONParser();
		JSONParser parser1 = new JSONParser();
		Object obj0 = null;
		Object obj1 = null;
		Object obj2 = null;
		JSONObject jsonObject0 = null;
		JSONObject jsonObject1 = null;
		JSONObject jsonObject2 = null;
		JSONObject jsonObject3 = null;
		JSONObject jsonObject4 = null;
		try{
		jsonOutput =  response.getEntity(String.class);
		parser0 = new JSONParser();
		obj0= parser0.parse(jsonOutput);
		jsonObject0 = (JSONObject) obj0;
		//System.out.println("The size of json object" + jsonObject0.size());
		//System.out.println("The  path is present or not:"+ jsonObject0.containsKey("path"));
		
		obj1 = parser1.parse(jsonObject0.get("path").toString());
		jsonObject1 = (JSONObject) obj1;
		repositoryPath = jsonObject1.get("toString").toString();
			//System.out.println("repository path:"+repositoryPath);
		obj1 = parser1.parse(jsonObject0.get("children").toString());
		jsonObject2 = (JSONObject) obj1;
		JSONArray jsonArray = (JSONArray) jsonObject2.get("values");
			if (jsonArray != null) {
				Iterator itr = jsonArray.iterator();
				ArrayList<JSONObject> valuesList = new ArrayList<JSONObject>();
				while (itr.hasNext()) {
					valuesList.add((JSONObject) itr.next());
				}
				for (int i = 0; i < valuesList.size(); i++) {
					jsonObject3 = valuesList.get(i);
					if (jsonObject3.get("path") != null) {
						//System.out.println("path:"+jsonObject3.get("path"));
						//System.out.println("type:"+jsonObject3.get("type"));
						obj2 = parser1.parse(jsonObject3.get("path").toString());
						jsonObject4 = (JSONObject) obj2;
						if(jsonObject3.get("type").toString().equalsIgnoreCase("file")){
							//System.out.println("contentId:"+jsonObject3.get("contentId"));
							//System.out.println("fileName:"+jsonObject4.get("name"));
							fileName = jsonObject4.get("name").toString();
							fileType = "file";
							createFile(repositoryPath,fileType,fileName,tagName);
						}else if (jsonObject3.get("type").toString().equalsIgnoreCase("directory")){
							//System.out.println("node:"+jsonObject3.get("node"));
							//System.out.println("fileName:"+jsonObject4.get("name"));
							fileType = "directory";
							fileName=jsonObject4.get("name").toString();
							repositoryPath =jsonObject1.get("toString").toString()+"/"+fileName;
							//System.out.println("jolly:"+ repositoryPath+"pop"+fileName);
							createDirectory(repositoryPath,fileType,fileName,tagName);
						}
					}
				}
			}
		}catch(ParseException parseExp){
			throw parseExp;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	private   void createDirectory(String repositoryPath, String fileType,String fileName,String tagName) throws ParseException {
		boolean createFolder = false;
		 ClientResponse   response;
		 createFolder = new File(destinationLocation+repositoryPath).mkdir();
		 response = restCall(repositoryPath, fileType,fileName,tagName);
		 InputStream json_output = response.getEntityInputStream();
		 parseResult(response,tagName);
	}
	private void createFile(String repositoryPath, String fileType,
			String fileName,String tagName) throws ParseException, IOException {
		ClientResponse   response;
		 response = restCall(repositoryPath, fileType,fileName,tagName);
		 InputStream json_output = response.getEntityInputStream();
			 OutputStream output = new FileOutputStream(destinationLocation+repositoryPath+"/"+fileName);
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = json_output.read(buffer)) != -1) {
	               output.write(buffer, 0, bytesRead);
	            }
	            output.close();
		
	}
}
