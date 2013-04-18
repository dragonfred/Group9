package com.example.restaurantreviewapplication;

import android.app.Activity;

public class RestaurantBackController {
		private static RestaurantBackController instance;
	    private Activity activity1, activity2, activity3, activity4, activity5;

	    private RestaurantBackController() {}
	    
	    public static synchronized RestaurantBackController getInstance() {
	        if(instance == null) {
	            instance = new RestaurantBackController();
	        }

	        return instance;
	    }

	    public void setActivity1(Activity activityObject) { activity1 = activityObject; }
	    public void setActivity2(Activity activityObject) { activity2 = activityObject; }
	    public void setActivity3(Activity activityObject) { activity3 = activityObject; }
	    public void setActivity4(Activity activityObject) { activity4 = activityObject; }
	    public void setActivity5(Activity activityObject) { activity5 = activityObject; }

	    public void closeAllActivities() {
	        if(activity1 != null) {
	            activity1.finish();
	        }
	        if(activity2 != null) {
	            activity2.finish();
	        }
	        if(activity3 != null) {
	            activity3.finish();
	        }
	        if(activity4 != null) {
	            activity4.finish();
	        }
	        if(activity5 != null) {
	            activity5.finish();
	        }
	    }
}
