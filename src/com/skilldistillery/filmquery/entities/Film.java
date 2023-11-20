package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import java.util.List;
import java.util.Objects;

public class Film {
	DatabaseAccessorObject db = new DatabaseAccessorObject();
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int langId;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String feature;
	private String language;
	private List<Actor> actors;

	public Film(int filmId, String title, String description, short releaseYear, int langId, int rentDur, double rate,
			int length, double repCost, String rating, String features, String language) {
		this.id = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.langId = langId;
        this.rentDur = rentDur;
        this.rate = rate;
        this.length = length;
        this.repCost = repCost;
        this.rating = rating;
        this.feature = features;
        this.language=language;
        this.actors = new ArrayList<>();
   
	}

	public Film() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getRepCost() {
		return repCost;
	}

	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Actor> getActors() {
		List<Actor> copy = new ArrayList<>(actors);
		return copy;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, feature, id, langId, length, rate, rating, releaseYear, rentDur, repCost,
				title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && Objects.equals(feature, other.feature)
				&& id == other.id && langId == other.langId && length == other.length
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("Film id: ");
	    builder.append(id);
	    builder.append(" Title: ");
	    builder.append(title);
	    builder.append(" Description: ");
	    builder.append(description);
	    builder.append(" Release Year: ");
	    builder.append(releaseYear);
	    builder.append(" Rating: ");
	    builder.append(rating);
	    builder.append(" Language: ");
	    builder.append(language);
	    builder.append(" Actors: =");
	    builder.append(actors);
	    builder.append(db.findActorsByFilmId(id));
	    return builder.toString();
	}


}
