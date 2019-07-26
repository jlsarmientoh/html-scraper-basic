package com.web.scraper.core.http;


import com.web.scraper.core.exceptions.HTTPException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements Client {

    public static final String USER_AGENT = "Mozilla/5.0";
    public static final String GET_REQUEST = "GET";
    public static final String ERROR_SUMMARY = "Unable to get html from site: %s - Cause: %s";
    public static final String ERROR_SITE_NOT_AVAILABLE = "Site not available or moved: %s - HTTP Status code : %d";

    public String get(String siteURL) throws HTTPException {
        URL url = null;
        try {
            url = new URL(siteURL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(GET_REQUEST);
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = httpURLConnection.getResponseCode();

            if(HttpURLConnection.HTTP_MOVED_TEMP == responseCode
                        || HttpURLConnection.HTTP_MOVED_PERM == responseCode
                        || HttpURLConnection.HTTP_SEE_OTHER == responseCode){

                String newUrl = httpURLConnection.getHeaderField("Location");
                httpURLConnection.disconnect();
                httpURLConnection = (HttpURLConnection) new URL(newUrl).openConnection();
            } else if(HttpURLConnection.HTTP_OK != responseCode) {
                throw new Exception(String.format(ERROR_SITE_NOT_AVAILABLE, siteURL, responseCode));
            }

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
