	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

	import org.eclipse.jgit.api.Git;
	import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
	import org.eclipse.jgit.lib.ObjectId;
	import org.eclipse.jgit.lib.ObjectLoader;
	import org.eclipse.jgit.lib.Ref;
	import org.eclipse.jgit.lib.Repository;
	import org.eclipse.jgit.revwalk.RevCommit;
	import org.eclipse.jgit.revwalk.RevTree;
	import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

	/**
	* Snippet which shows how to use RevWalk and TreeWalk to read the contents
	* of a specific file from a specific commit.
	*
	* @author dominik.stadler at gmx.at
	*/

public class SubModule {

	    public static void main(String[] a) throws IOException, GitAPIException {
	    	//File gitWorkDir = new File("c:/Users/s4gzgk/git/dcscm/.git");
	    	////CHRB1045.CORP.GWPNET.COM/homes/G/S4GZGK/Documents/git/dcscm/.git
	    	///usr/local/scm/dev/d50/git_dev/1
	    	File gitWorkDir = new File("/usr/local/scm/dev/d50/git_dev/1");
			Git git = Git.open(gitWorkDir);
			Repository repository = git.getRepository();

	       
	       File file = null;
	       OutputStream out = null;
	       
	        RevWalk walk = new RevWalk(repository);
	       // Ref tagRef = repository.getRef("refs/heads/testbranch");
	        Map<AnyObjectId, Set<Ref>> tagRefMap = repository.getAllRefsByPeeledObjectId();
	        Iterator itr= tagRefMap.entrySet().iterator();
	        for (Object key : tagRefMap.keySet()) {
	    		System.out.println("Key : " + key.toString() + " Value : "
	    			+ tagRefMap.get(key));
	    	}
	        Ref tagRef = repository.getRef("refs/heads/testbranch");
	        RevCommit commit = walk.parseCommit(tagRef.getObjectId());
	        RevTree tree = commit.getTree();
	        TreeWalk treeWalk = new TreeWalk(repository);
	        treeWalk.addTree(tree);
	        treeWalk.setRecursive(true);
	        String folderName = null;
	        while (treeWalk.next()) {
	            //System.out.println("found: " + treeWalk.getPathString());
	           // file = new File("C:/Users/s4gzgk/testpoc/"+treeWalk.getPathString());
	           
	            String fileName = treeWalk.getPathString().substring(treeWalk.getPathString().lastIndexOf("/")+1, treeWalk.getPathString().length());
	            if(fileName !=null){
	          //  System.out.println("The file name is:"+fileName);
	            	folderName = treeWalk.getPathString().substring(0, treeWalk.getPathString().indexOf(fileName));
	            	//System.out.println("The folder name is:"+folderName);
	            }
	            String[] test=folderName.split("/");
	            boolean createFolder = false;
	            String dest ="/tmp/gitmodules/";
	            for(String i :test){
	            	System.out.println("dest:"+dest);
	            	createFolder= new File(dest+i).mkdir();
	            	dest = dest+"/"+i+"/";
	            }
	            //boolean createFolder= new File("C:/Users/s4gzgk/testpoc/"+folderName).mkdir();
	           // if(!createFolder)
	            //System.out.println("createFolder "+createFolder);
	            String destLocation ="/tmp/gitmodules/"+folderName;
	            file = new File(destLocation+fileName);
	            
	            if(file.exists()){
	            	file.delete();
	            	file.createNewFile(); 
	            }
	            out = new FileOutputStream(file);
	            	ObjectId objectId = treeWalk.getObjectId(0);
	                ObjectLoader loader = repository.open(objectId);
	                
	                // and then one can the loader to read the file
	              //  loader.copyTo(System.out);
	                out.write(loader.getBytes());
	            System.out.println("success");
	            out.flush();
	        }
	       // out.close();
	        repository.close();
	    }
	}