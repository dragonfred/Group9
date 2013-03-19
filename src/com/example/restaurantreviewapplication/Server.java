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
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

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

	private static String serverURL = "http://cop4331.atmdvdusa.com/server/";
	private static String username;
	private static String password;
	private static final int BASE64_OPTS = Base64.NO_PADDING | Base64.NO_WRAP | Base64.URL_SAFE;
    
	/**
	 * @param serverURL Set the URL of the server. It is set by default, hardcoded in this class.
	 */
	public static void setServer(String serverURL) {
		Log.i("Server.getServer", "Server URL changed to "+serverURL);
		Server.serverURL = serverURL;
	}
//	public static void setPolicy() {
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//		StrictMode.setThreadPolicy(policy);
//	}
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
    private static String sendServer(String urlParameters) {
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
            Log.e("Server.sendServer", "Sent: "+urlParameters);
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
        	Log.i("Server.sendServer", e.toString());
        }
        Log.i("Server.sendServer", "Got: "+sb.toString());
        return sb.toString();
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
