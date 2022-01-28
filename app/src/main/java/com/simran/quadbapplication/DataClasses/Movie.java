package com.simran.quadbapplication.DataClasses;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Movie implements Serializable {

    double score;
    int id;
    String url;
    String name;
    String type;
    String language;
    List<String> genres;
    String status;
    int runtime;
    int avgRuntime;
    String premiered;
    String ended;
    String officialSite;
    Map<String, Object> schedule;
    Map<String, Float> rating;
    int weight;
    Map<String, Object> network;
    Map<String, Object> webChannel;
    String dvdCountry;
    Map<String, Object> externals;
    Map<String, String> image;
    String summary;
    long updated;
    Map<String, Map<String, String>> links;

    public Movie(double score, int id, String url, String name, String type, String language, List<String> genres, String status, int runtime, int avgRuntime, String premiered, String ended, String officialSite, Map<String, Object> schedule, Map<String, Float> rating, int weight, Map<String, Object> network, Map<String, Object> webChannel, String dvdCountry, Map<String, Object> externals, Map<String, String> image, String summary, long updated, Map<String, Map<String, String>> links) {
        this.score = score;
        this.id = id;
        this.url = url;
        this.name = name;
        this.type = type;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.runtime = runtime;
        this.avgRuntime = avgRuntime;
        this.premiered = premiered;
        this.ended = ended;
        this.officialSite = officialSite;
        this.schedule = schedule;
        this.rating = rating;
        this.weight = weight;
        this.network = network;
        this.webChannel = webChannel;
        this.dvdCountry = dvdCountry;
        this.externals = externals;
        this.image = image;
        this.summary = summary;
        this.updated = updated;
        this.links = links;
    }

    public double getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getAvgRuntime() {
        return avgRuntime;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getEnded() {
        return ended;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public Map<String, Object> getSchedule() {
        return schedule;
    }

    public Map<String, Float> getRating() {
        return rating;
    }

    public int getWeight() {
        return weight;
    }

    public Map<String, Object> getNetwork() {
        return network;
    }

    public Map<String, Object> getWebChannel() {
        return webChannel;
    }

    public String getDvdCountry() {
        return dvdCountry;
    }

    public Map<String, Object> getExternals() {
        return externals;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public long getUpdated() {
        return updated;
    }

    public Map<String, Map<String, String>> getLinks() {
        return links;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setAvgRuntime(int avgRuntime) {
        this.avgRuntime = avgRuntime;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public void setSchedule(Map<String, Object> schedule) {
        this.schedule = schedule;
    }

    public void setRating(Map<String, Float> rating) {
        this.rating = rating;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setNetwork(Map<String, Object> network) {
        this.network = network;
    }

    public void setWebChannel(Map<String, Object> webChannel) {
        this.webChannel = webChannel;
    }

    public void setDvdCountry(String dvdCountry) {
        this.dvdCountry = dvdCountry;
    }

    public void setExternals(Map<String, Object> externals) {
        this.externals = externals;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public void setLinks(Map<String, Map<String, String>> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "score=" + score +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", genres=" + genres +
                ", status='" + status + '\'' +
                ", runtime=" + runtime +
                ", avgRuntime=" + avgRuntime +
                ", premiered='" + premiered + '\'' +
                ", ended='" + ended + '\'' +
                ", officialSite='" + officialSite + '\'' +
                ", schedule=" + schedule +
                ", rating=" + rating +
                ", weight=" + weight +
                ", network=" + network +
                ", webChannel=" + webChannel +
                ", dvdCountry='" + dvdCountry + '\'' +
                ", externals=" + externals +
                ", image=" + image +
                ", summary='" + summary + '\'' +
                ", updated=" + updated +
                ", links=" + links +
                '}';
    }
}
