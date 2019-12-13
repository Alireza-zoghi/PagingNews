package com.alireza.zoghi.news.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 6:50 PM
 ************************************************/

public class utility {

    public static String getDateDuration(String date) {
        String postReleaseDate = date.replace("T", " ");
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = df.format(c.getTime());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = sdf.parse(postReleaseDate);
            d2 = sdf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert d1 != null;
        assert d2 != null;
        int diff = getDateDiff(d1, d2, TimeUnit.MINUTES);
        String result = "";

        if (diff <= 59) {
            result = diff + " دقیقه";
        } else if (diff <= 1439) {
            result = diff / 60 + " ساعت";
        } else if (diff <= 10079) {
            result = diff / 1440 + " روز";
        } else if (diff <= 43200) {
            result = diff / 10080 + " هفته";
        } else if (diff > 43200) {
            result = diff / 43200 + " ماه";
        }

        result = result + " پیش";
        return result;
    }

    private static int getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return (int) timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
