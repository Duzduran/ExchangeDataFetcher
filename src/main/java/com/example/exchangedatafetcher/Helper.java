package com.example.exchangedatafetcher;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.support.NullValue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Helper {
    public static void catchException(String currency, String no) {
        try {
            Integer intRep = Integer.parseInt(no);
            if (intRep > 255 || intRep < 0)
                throw new IllegalArgumentException("number can't be bigger than 255 or lower than 0!");
            else if (currency == null || currency.isBlank())
                throw new IllegalArgumentException("Currency can't be blank or null");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
    public static HashSet<Rate> buySellDiffParse(String responseBody) {
        try {
            HashSet<Rate> setRates = new HashSet<>();
            JSONObject responseObj = new JSONObject(responseBody); // Create a JSONObject from the response body
            JSONArray ratesArray = responseObj.getJSONArray("rates"); // Get the "rates" JSON array
            double ask,bid,difference;
            String no,effectiveDate;
            for (int i = 0; i < ratesArray.length(); i++) {
                JSONObject rateObj = ratesArray.getJSONObject(i);
                ask = rateObj.getDouble("ask");
                bid = rateObj.getDouble("bid");
                no = rateObj.getString("no");
                effectiveDate = rateObj.getString("effectiveDate");
                difference = ask - bid;
                Rate rate = new Rate(no,effectiveDate,difference);
                System.out.println(rate.toStringDiff());
                setRates.add(rate);
            }
            return setRates;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Double> maxMinParse(String responseBody) {
        try {
            List<Double> numbers = new ArrayList<>();

            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            JSONObject responseObj = new JSONObject(responseBody); // Create a JSONObject from the response body
            JSONArray ratesArray = responseObj.getJSONArray("rates"); // Get the "rates" JSON array

            for (int i = 0; i < ratesArray.length(); i++) {
                JSONObject rateObj = ratesArray.getJSONObject(i);
                double mid = rateObj.getDouble("mid");
                max = Math.max(max, mid);
                min = Math.min(min, mid);
            }
            System.out.println("Max average value: " + max + " Min average value: " + min);
            numbers.add(max);
            numbers.add(min);
            return numbers;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static Double parseMid(String responseBody) {
        try {
            JSONObject responseObj = new JSONObject(responseBody); // Create a JSONObject from the response body
            String currency = responseObj.getString("currency");
            JSONArray ratesArray = responseObj.getJSONArray("rates"); // Get the "rates" JSON array

            JSONObject rateObj = (JSONObject) ratesArray.get(0);

            double mid = rateObj.getDouble("mid");
            String effectiveDate = rateObj.getString("effectiveDate");
            if(effectiveDate != null && effectiveDate.length() >0) return mid;
            else throw new IllegalAccessException("Can't retriete data! weekend date or holiday");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
