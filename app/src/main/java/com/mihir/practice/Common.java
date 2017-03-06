package com.mihir.practice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class Common {
    /**
     * @author Mihir
     * @description use to check internet newtwork connection
     * if network connection not available than alert for open network settings
     * @param context
     * @param message
     * @return
     */
    //	public static boolean isOnline(final Context context, boolean message)
    //	{
    //		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    //		NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
    //		if (netInfo != null)
    //		{
    //			if(netInfo.isConnectedOrConnecting())
    //			{
    //				return true;
    //			}
    //		}
    //		if (message)
    //		{
    //			AlertDialog.Builder builder = new AlertDialog.Builder(context);
    //			builder.setMessage("Internet Avability");
    //			builder.setCancelable(false);
    //
    //			builder.setPositiveButton("Network Setting",new DialogInterface.OnClickListener()
    //			{
    //				@Override
    //				public void onClick(DialogInterface dialog,int which)
    //				{
    //				  context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    //				}
    //			});
    //			builder.setNegativeButton("Wifi Settings", new DialogInterface.OnClickListener()
    //			{
    //				@Override
    //				public void onClick(DialogInterface dialog, int which)
    //				{
    //					context.startActivity(new Intent(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK)));
    //				}
    //			});
    //
    //			AlertDialog alert = builder.create();
    //			alert.show();
    //			return false;
    //		}
    //		return false;
    //	}

    /**
     * @return true if monted else unmounted
     * @author Mihir
     * @description check file system mounted or not
     */
    public static boolean isFileSystemMounted() {
        boolean ismounted;
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED))
            ismounted = true;
        else
            ismounted = false;

        return ismounted;
    }

    /**
     * @param path
     * @return true if file exists else create it
     * @author Mihir
     * @description create directory if not exists
     */
    public static String createDirIfNotExists(Context mContext, String path) {
        final String externalStorage = System.getenv("EXTERNAL_STORAGE");//internal
        File file = new File(externalStorage + "/", path);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                file.mkdir();
            }
            Log.d("Success", file.toString() + " Directory created Successfully");
        } else {
            Log.d("Success", file.toString() + " Directory Exists");
        }
        return file.getAbsolutePath();
    }

/*	*//**
     * @author Mihir
     * @Description Set Shared Preference for currunt folder name
     * @param mContext
     * @param current_folder_name
     *//*
	public static void setSharedPrefForCurruntFolderName(Context mContext, String current_folder_name) 
	{
		SharedPreferences sp = mContext.getSharedPreferences(Constant.SHAREDPREF_NAME_NYTTIUKA,Context.MODE_PRIVATE);

		SharedPreferences.Editor sharedPrefEdit = sp.edit();
		sharedPrefEdit.putString(Constant.CURRUNT_FOLDER_NAME, current_folder_name);
		sharedPrefEdit.commit();
	}*/
}
