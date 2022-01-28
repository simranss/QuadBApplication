package com.simran.quadbapplication.Utils;

import androidx.annotation.NonNull;

import com.simran.quadbapplication.DataClasses.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieQueryUtils {

    public static final String TAG = "MovieQueryUtils";

    public static List<Movie> getMovies(String jsonString) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonString);
        List<JSONObject> movieJsonList = getMovieJsonList(jsonArray);

        List<Movie> movies = new ArrayList<>();
        for (JSONObject jsonObject : movieJsonList) {
            Movie movie = convertJsonToMovie(jsonObject);
            movies.add(movie);
        }

        return movies;
    }

    private static Movie convertJsonToMovie(@NonNull JSONObject jsonObject) {
        Movie movie = null;

        double score = jsonObject.optDouble("score");
        JSONObject movieObject = jsonObject.optJSONObject("show");

        if (movieObject != null) {
            int id = movieObject.optInt("id");
            String url = movieObject.optString("url");
            String name = movieObject.optString("name");
            String type = movieObject.optString("type");
            String language = movieObject.optString("language");
            JSONArray genreArray = movieObject.optJSONArray("genres");
            List<String> genres = new ArrayList<>();
            if (genreArray != null)
                genres = getListFromJsonArray(genreArray);
            String status = movieObject.optString("status");
            int runtime = movieObject.optInt("runtime");
            int avgRuntime = movieObject.optInt("averageRuntime");
            String premiered = movieObject.optString("premiered");
            String ended = movieObject.optString("ended");
            String officialSite = movieObject.optString("officialSite");
            JSONObject scheduleObject = movieObject.optJSONObject("schedule");
            Map<String, Object> schedule = new HashMap<>();
            if (scheduleObject != null) {
                String time = scheduleObject.optString("time");
                JSONArray dayArray = scheduleObject.optJSONArray("days");
                List<String> days = new ArrayList<>();
                if (dayArray != null) {
                    days = getListFromJsonArray(dayArray);
                }
                schedule.put("time", time);
                schedule.put("days", days);
            }
            Map<String, Float> rating = new HashMap<>();
            JSONObject ratingObject = movieObject.optJSONObject("rating");
            if (ratingObject != null) {
                double avg = ratingObject.optDouble("average");
                rating.put("average", (float) avg);
            }
            int weight = movieObject.optInt("weight");
            Map<String, Object> network = new HashMap<>();
            JSONObject networkObject = movieObject.optJSONObject("network");
            if (networkObject != null) {
                int id1 = networkObject.optInt("id");
                network.put("id", id1);
                String name1 = networkObject.optString("name");
                network.put("name", name1);
                Map<String, String> country = new HashMap<>();
                JSONObject countryObject = networkObject.optJSONObject("country");
                if (countryObject != null) {
                    String name2 = countryObject.optString("name");
                    String code = countryObject.optString("code");
                    String timezone = countryObject.optString("timezone");
                    country.put("name", name2);
                    country.put("code", code);
                    country.put("timezone", timezone);
                }
                network.put("country", country);
            }
            Map<String, Object> webChannel = new HashMap<>();
            JSONObject webChannelObject = movieObject.optJSONObject("webChannel");
            if (webChannelObject != null) {
                int id1 = webChannelObject.optInt("id");
                webChannel.put("id", id1);
                String name1 = webChannelObject.optString("name");
                webChannel.put("name", name1);
                Map<String, String> country = new HashMap<>();
                JSONObject countryObject = webChannelObject.optJSONObject("country");
                if (countryObject != null) {
                    String name2 = countryObject.optString("name");
                    String code = countryObject.optString("code");
                    String timezone = countryObject.optString("timezone");
                    country.put("name", name2);
                    country.put("code", code);
                    country.put("timezone", timezone);
                }
                webChannel.put("country", country);
            }
            String dvdCountry = movieObject.optString("dvdCountry");
            Map<String, Object> externals = new HashMap<>();
            JSONObject externalsObject = movieObject.optJSONObject("externals");
            if (externalsObject != null) {
                String tvRage = externalsObject.optString("tvrage");
                int thetvdb = externalsObject.optInt("thetvdb");
                String imdb = externalsObject.optString("imdb");
                externals.put("tvrage", tvRage);
                externals.put("thetvdb", thetvdb);
                externals.put("imdb", imdb);
            }
            Map<String, String> image = new HashMap<>();
            JSONObject imageObj = movieObject.optJSONObject("image");
            if (imageObj != null) {
                String medium = imageObj.optString("medium");
                String original = imageObj.optString("original");
                image.put("medium", medium);
                image.put("original", original);
            }
            String summary = movieObject.optString("summary");
            long updated = movieObject.optLong("updated");
            Map<String, Map<String, String>> links = new HashMap<>();
            JSONObject linksObj = movieObject.optJSONObject("_links");
            if (linksObj != null) {
                Map<String, String> self = new HashMap<>();
                JSONObject selfObj = linksObj.optJSONObject("self");
                if (selfObj != null) {
                    String href = selfObj.optString("href");
                    self.put("href", href);
                }
                Map<String, String> previousEpisode = new HashMap<>();
                JSONObject previousEpObj = linksObj.optJSONObject("previousepisode");
                if (previousEpObj != null) {
                    String href = previousEpObj.optString("href");
                    previousEpisode.put("href", href);
                }
                Map<String, String> nextEpisode = new HashMap<>();
                JSONObject nextEpObj = linksObj.optJSONObject("nextepisode");
                if (nextEpObj != null) {
                    String href = nextEpObj.optString("href");
                    nextEpisode.put("href", href);
                }
                links.put("self", self);
                links.put("previousEpisode", previousEpisode);
                links.put("nextEpisode", nextEpisode);
            }

            movie = new Movie(score, id, url, name, type, language, genres, status, runtime, avgRuntime, premiered, ended, officialSite, schedule, rating, weight, network, webChannel, dvdCountry, externals, image, summary, updated, links);
        }

        return movie;
    }

    private static List<JSONObject> getMovieJsonList(JSONArray jsonArray) throws JSONException {
        List<JSONObject> jsonObjects = new ArrayList<>();
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            jsonObjects.add(jsonArray.getJSONObject(i));
        }
        return jsonObjects;
    }

    private static List<String> getListFromJsonArray(JSONArray jsonArray) {
        List<String> strings = new ArrayList<>();
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            strings.add(jsonArray.optString(i));
        }
        return strings;
    }
}
