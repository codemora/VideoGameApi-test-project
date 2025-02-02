package entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class VideoGame {
    private int id;
    private String name;
    private String releaseDate;
    private int reviewScore;
    private String category;
    private String rating;


    public VideoGame() {}

    public VideoGame(String name, String releaseDate, int reviewScore, String category, String rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
        this.category = category;
        this.rating = rating;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public JsonObject toJson(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);

        // Convert JSON String to JsonObject
        return gson.fromJson(jsonString, JsonObject.class);
    }
}
