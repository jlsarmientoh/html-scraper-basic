package com.web.scraper.core.http;


import com.web.scraper.core.exceptions.HTTPException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpClient implements Client {

    public static final String USER_AGENT = "Mozilla/5.0";
    public static final String GET_REQUEST = "GET";
    public static final String ERROR_SUMMARY = "Unable to get html from site: %s - Cause: %s";
    public static final String ERROR_SITE_NOT_AVAILABLE = "Site not available or moved";

    public String get(String siteURL) throws HTTPException {
        URL url = null;
        try {
            url = new URL(siteURL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(GET_REQUEST);
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode != 200)
                throw new Exception(ERROR_SITE_NOT_AVAILABLE);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            return response.toString();

        } catch (Exception e) {
            throw new HTTPException(String.format(ERROR_SUMMARY, siteURL, e.getMessage()), e);
        }
    }
}
