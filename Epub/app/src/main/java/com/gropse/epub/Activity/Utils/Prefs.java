package com.gropse.epub.Activity.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

    private static Prefs pref;

    private final SharedPreferences mPrefs;

    private Prefs(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Prefs instanceOf(Context context){
        pref = new Prefs(context);
        return pref;
    }


    private String PREF_LOGIN="LOGIN";
    private String PREF_SetProfile="Pref_setProfile";
    private String PREF_SetWelcome="Pref_setWelcome";
    private String PREF_SetVerification="Pref_setVerification";
    private String PREF_SetLanguage="PREF_SetLanguage";
    private String setConditions="setConditions";
    private String SetVerify="LOGIN";
    private String security_token="securityToken";
    private String Login_type="Login_type";
    private String IsProvider="IsProvider";
    private String Uid="Uid";
    private String Value="value";
    private String Name="Name";
    private String Url="Url";
    private String providerId="providerId";
    private String DeviceId="DeviceId";
    private String CityCount="CityCount";
    private String mobileNo="mobileNo";
    private String SelectToSpeach="SelectToSpeach";
    private String medium="PREF_LOCALE";



    public String getMedium() {
        return mPrefs.getString(medium,"en");
    }
    public void setMedium(String key) {
        mPrefs.edit().putString(medium, key).apply();
    }

    public void setValue(String value)
    {
        mPrefs.edit().putString(Value,value).apply();
    }
    public String getValue(){ return mPrefs.getString(Value,""); }


    public void setUrl(String value) { mPrefs.edit().putString(Url,value).apply(); }
    public String getUrl(){ return mPrefs.getString(Url,""); }

    public void setSelectToSpeach(String value) { mPrefs.edit().putString(SelectToSpeach,value).apply(); }
    public String getSelectToSpeach(){ return mPrefs.getString(SelectToSpeach,""); }


}
