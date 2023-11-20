package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		int userInput = 0;
		while (userInput != 3) {
			System.out.println("Please choose one of the following options");
			System.out.println("1. Look up a film by it's film id.");
			System.out.println("2. Look up a film by a keyword");
			System.out.println("3. Exit");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				lookUpAFilmByFilmID(input);
				List<Actor> actors = db.findActorsByFilmId(userInput);
				for (Actor actor : actors) {
					System.out.println(actor);
				}

				break;
			case 2:
				lookUpAFilmByKeyword(input);
				break;
			case 3:
				System.out.println("Goodbye");
				break;
			default:
				System.err.println("Try again");
				break;

			}
		}
	}

	private void lookUpAFilmByFilmID(Scanner filmID) throws SQLException {
		Film film = null;
		System.out.println("Please enter a film ID");
		int id = filmID.nextInt();
		film = db.findFilmById(id);
		if (film != null) {
		System.out.println(film);

		}

	}

	private void lookUpAFilmByKeyword(Scanner filmKeyword) {
		System.out.println("Please enter a keyword");
		String input = filmKeyword.next();
		List<Film> films = db.findFilmsByKeyword(input);
		if (!input.isEmpty()) {
			for (Film film : films) {
				System.out.println(film);
			}

		} else {
			System.out.println("No films match your keyword.");
		}

	}


}
