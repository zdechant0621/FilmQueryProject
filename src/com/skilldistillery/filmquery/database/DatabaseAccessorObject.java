package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PASS = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		String sql = "SELECT film.*, language.name FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film();
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setLangId(filmResult.getInt("language_id"));
			film.setRentDur(filmResult.getInt("rental_duration"));
			film.setRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setRepCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setFeature(filmResult.getString("special_features"));
			film.setLanguage(filmResult.getString("name"));

		}
		filmResult.close();
		stmt.close();
		conn.close();
		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				String language= rs.getString("name");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, language);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
					+ " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorID = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(actorID, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	@Override
	public List<Film> findFilmsByKeyword(String filmKeyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.*, language.name FROM film JOIN language ON language.id = film.language_id WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+ filmKeyword + "%");
			stmt.setString(2, "%"+ filmKeyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				String language= rs.getString("name");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, language);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	


}
