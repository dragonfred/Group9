package com.example.restaurantreviewapplication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import android.location.Location;
import android.os.AsyncTask;
//import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

/**
 * 
 * Manage connections to the application server.
 * 
 * @author phil
 *
 */
public class Server {
	private static String serverURL = 
			"http://cop4331.atmdvdusa.com/server/";
	private static String username;
	private static String password;
	private static final int BASE64_OPTS = Base64.NO_PADDING | Base64.NO_WRAP | Base64.URL_SAFE;
    private static boolean isReady = true;
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    // Stubs  / Tests
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    //Location has latitude and longitude in it.
    public static ArrayList<Restaurant> getRestaurantsByLocation(Location location) {
    	ArrayList<Restaurant> result = new ArrayList<Restaurant>();
    	result.add(new Restaurant("Joe's Restaurant", "123 Fake St", "123-123-1233", "Here"));
    	result.add(new Restaurant("Bob's Restaurant", "234 fake st", "234-234-2344", "There"));
    	return result;
    }
    
    //Zip is "0" (zero) if no zip entered
    public static ArrayList<Restaurant> getRestaurantsByZipKeyword(int zip, String keyword) {
    	ArrayList<Restaurant> result = new ArrayList<Restaurant>();
    	result.add(new Restaurant("Joe's Restaurant", "123 Fake St", "123-123-1233", "Here"));
    	result.add(new Restaurant("Bob's Restaurant", "234 fake st", "234-234-2344", "There"));
    	return result;
    }
    
    public static void LogOut(User currentUser){
    	// log user out of server
    	/* The server is not stateful. There is no "log out." */ 
    }
    
    public static int deleteFriend(User currentUser, Friend friend){
    	int result;
    	// 0 for good, 1 for bad
    	result = 0;
    	return result;
    }
    
    public static int messageFriend(User currentUser, Friend friend, String message){
    	int result;
    	// 0 for good, 1 for bad
    	result = 0;
    	return result;
    }
    
    public static int resetPassword(){
      	int result;
    	// 0 for good, 1 for bad
    	result = 0;
    	return result;
    }
    
    public static ArrayList<Review> getReviews(Restaurant restaurant){
    	ArrayList<Review> reviews = new ArrayList<Review>();
    	
    	Review aReview = new Review();
    	aReview.setReview("This place rocks!");
    	reviews.add(aReview);
    	
    	aReview = new Review();
    	aReview.setReview("This place sucks!");
    	reviews.add(aReview);
    	
    	aReview = new Review();
    	aReview.setReview("This place smells funny!");
    	reviews.add(aReview);
    	
    	return reviews;
    }
    
    public static void addReview(Restaurant restaurant, String Review){
    	// add review to server
    }
    
    public static Friend findFriend(String friendID){
    	Friend result;
    	result = new Friend();
    	result.setUserId("New Friend");
    	return result; //return null for not found
    	
    }
    
    public static void addFriend(User user, Friend newFriend){
    	// new friend to user
    }
    
    public static int changePassword(String newPassword){
    	int result;
    	// 0 for good, 1 for bad
    	result = 0;
    	return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param serverURL Set the URL of the server. It is set by default, hardcoded in this class.
	 */
	public static void setServer(String serverURL) {
		Log.i("Server.getServer", "Server URL changed to "+serverURL);
		Server.serverURL = serverURL;
	}
	
	/**
	 * Set the username. It is important to do this before using the server.
	 * @param username The user's username. 
	 */
	public static void setUsername(String username) {
		Log.i("Server.setUsername", "Username changed to "+username);
		Server.username = username;
	}
	
	/** Set the password. It is important to do this before using the server.
	 * @param password The user's password.
	 */
	public static void setPassword(String password) {
		Log.i("Server.setPassword", "Password changed to "+password);
		Server.password = password;
	}
	
	public static String createAccount(String email) {
		HashMap<String, String> postData;
		postData = new HashMap<String, String>();
		
		postData.put("username", username);
		postData.put("password", password);
		postData.put("email", email);
		postData.put("action", "newUser");
		postData.put("object", "");
		postData.put("uuid", UUID.randomUUID().toString());
		return postToServer(postData);	
	}
	/**
	 * @param o An object returned from getObject
	 * @return True if this object is an error. 
	 */
	public static boolean isError(Object o) {
		String str = (String) o;
		return (o == null) || str.substring(0,4).equals("ERR");
	}
	
	/**
	 * @param o An object that is an error.
	 * @return A string containing the error.
	 */

	public static String getError(Object o) {
		String str = (String) o;
		if(o == null) str = "ERR: No object returned.";
		return str;
	}

	public static User getUser() {
		HashMap<String, String> vars = new HashMap<String, String>();
		Object o;
		vars.put("username", Server.username);
		vars.put("password", Server.password);
		vars.put("action", "getUserData");
		o = stringToObject(postToServer(vars));
		if(isError(o)) {
			Log.e("Server", getError(o));
			return null;
		}
		return (User) stringToObject(postToServer(vars));
	}
	
    /**
     * Put a Storable object on the server.
     * 
     * @param o a Storable object.
     * @param visibility One of "public", "private", "friends"
     * @return The response from the server
     */
    public static String putObject(Storable o, String visibility) {
    	HashMap<String, String> vars = new HashMap<String, String>();
    	vars.put("username", Server.username);
    	vars.put("password", Server.password);
    	vars.put("action", "writeObject");
    	vars.put("visibility", visibility);
    	vars.put("uuid", o.getUuid().toString());
    	vars.put("object", objectToString(o));
    	
    	return postToServer(vars);
    }
    
    /**
     * Get a Storable object from the server.
     * @param uuid The UUID of the object.
     * @return an object. If it is an error, the error will be returned as a String object. 
     */
    public static Object getObject(UUID uuid) {
    	HashMap<String, String> vars = new HashMap<String, String>();
    	vars.put("username", Server.username);
    	vars.put("password", Server.password);
    	vars.put("action", "readObject");
    	vars.put("uuid", uuid.toString());
    	String result = Server.postToServer(vars);
    	if(result.substring(0, 4).equals("ERR")) {
    		Log.e("Server", result);
    		return result;
    	}
    	else return stringToObject(result);
    }
    
    /**
     * 
     * Send a POST request to the server and return the response.
     * This can be used to do anything, but it's the hard way.
     * It's recommended to add a method here instead of using this
     * directly. 
     * 
     * Example: 
     * 
     * HashMap<String, String> vars = new HashMap<String, String>();
     * vars.put("username", "bob");
     * vars.put("password", "secret");
     * vars.put("action", "readObject");
     * vars.put("uuid", "bfaf910e-8b4f-11e2-840a-be257188419e");
     * String result = Server.postToServer(vars);
     * 
     * @param vars A HashMap of POST vars and values.
     * @return The response from the server.
     */
    public static String postToServer(HashMap<String, String> vars) {
    	String postData = mapToPost(vars);
    	return sendServer(postData);
    }
    
    
    /*
     * ****************************************************************
     * Abandon hope all ye who venture below this line. 
     * This is where the sausage is made.
     * ****************************************************************
     */
    
	/**
	 * Wrapper for Android's Base64 conversion.
	 * Converts binary to string.
	 * @param in a byte array
	 * @return The byte array encoded in URL safe Base64.
	 */
	private static String toB64(byte [] in) {
		return Base64.encodeToString(in, BASE64_OPTS);
	}
	
	/**
	 * Wrapper for Android's Base64 conversion.
	 * Converts string to binary.
	 * @param in A string encoded in URL safe Base64
	 * @return the byte array encoded in the string
	 */
	private static byte[] fromB64(String in) {
		return Base64.decode(in, BASE64_OPTS);
	}

    /**
     * Convert a HashMap of POST vars/vals to a string usable with
     * HttpURLConnection.
     * @param vars A HashMap<String, String> of post vars/values
     * @return A String suitable for HTTP url parameters.
     */
    private static String mapToPost(HashMap<String, String> vars) {
    	Iterator<String> keyIter = vars.keySet().iterator();
    	StringBuilder sb = new StringBuilder();
    	while(keyIter.hasNext()) {
    		String key = keyIter.next();
    		sb.append(key+"="+vars.get(key));
    		if(keyIter.hasNext()) {
    			sb.append("&");
    		}
    	}
    	return sb.toString();
    }
    
    /**
     * @param b64in A base-64 encoded string
     * @return An object
     */
    private static Object stringToObject(String b64in) {
            Object o = null;
            byte[] s2 = fromB64(b64in);
            try {
                ObjectInputStream is = new ObjectInputStream
                    (new ByteArrayInputStream(s2));
                o = is.readObject();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return o;
    }
    
    /**
     * @param ob a Storable object.
     * @return A base-64 encoded string that represents the object.
     */
    private static String objectToString(Storable ob) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(ob);
            o.close();
        } catch (IOException e) {
            Log.e("Server", e.toString());
        }
        return toB64(b.toByteArray());    
    }
   
    /**
     * @param urlParameters A string containing POST vars/vals. 
     * @return The response from the server.
     */
   private static String sendServerInternal(String urlParameters) {
        StringBuilder sb = new StringBuilder();
                try {
            URL url = new URL(serverURL); 
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();           
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("POST"); 
            connection.setRequestProperty("Content-Type", 
                                          "application/x-www-form-urlencoded"); 
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + 
                                          Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            Log.e("Server.sendServerInternal", "Sent: "+urlParameters);
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
                        
            BufferedReader rd  = new BufferedReader
                (new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            connection.disconnect();
        } catch (Exception e) {
        	Log.e("Server.sendServerInternal", e.toString());
        }
        Log.i("Server.sendServer", "Got: "+sb.toString());
        return sb.toString();
    }
   
    public static String sendServer(String urlParameters) {
    	setReady(false);
    	String r;
    	AsyncTask<String, Void, String> s = new Server.ServerTask();
    	s.execute(urlParameters);
    	try {
			r=s.get();
		} catch (InterruptedException e) {
			Log.e("Server.sendServer", e.toString());
			return "ERR: Interrupted";
		} catch (ExecutionException e) {
			Log.e("Server.sendServer", e.toString());
			return "ERR: Execution Exception";
		}
    	return r;
    }
    
    private static void setReady(Boolean stat) {
    	isReady=stat;
    }
    
    public static boolean getReady() {
    	return isReady;
    }
    
    public static class ServerTask extends AsyncTask<String, Void, String> {
    	@Override
    	protected String doInBackground(String... args) {
    		Server.setReady(false);
    		return Server.sendServerInternal(args[0]);
    	}
    	
         protected void onPostExecute(String result) {
             Server.setReady(true);
         }
    }
    
    public static void createTestUser(String username, String password) {
    	User user = new User();
    	Server.setPassword(password);
    	Server.setUsername(username);

    	user.setUsername(username);
    	user.setPassword(password);
    	
    	HashMap<String, String> vars = new HashMap<String, String>();
    	vars.put("username", Server.username);
    	vars.put("password", Server.password);
    	vars.put("action", "newUser");
    	vars.put("object", objectToString(user));
    	vars.put("uuid", user.getUuid().toString());
    	postToServer(vars);
    }
}
