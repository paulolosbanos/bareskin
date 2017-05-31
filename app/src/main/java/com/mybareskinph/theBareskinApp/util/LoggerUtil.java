package com.mybareskinph.theBareskinApp.util;


import com.google.gson.Gson;
import com.orhanobut.logger.Logger;


public class LoggerUtil {


    public static void log(Object o) {
        Logger.d(new Gson().toJson(o));
    }
}
