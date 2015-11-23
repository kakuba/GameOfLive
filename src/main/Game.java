package main;

import java.util.List;

public class Game {
	
	private List<Person> persons;
	private int mapSize;

	public Game(List<Person> persons, int mapSize) {
		this.mapSize = mapSize;
		this.persons = persons;
	}
	
	public List<Person> playRound() {
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		round.makePersonsAlive(persons, mapList, mapSize);
		round.killedOrRebornPersons(mapList, mapSize);
		List<Person> personsListAlive = round.arePersonsAlive(mapList);
		return personsListAlive;
	}
	public List<Person> startGame() {
		List<Person> personsListAlive = playRound();
		return personsListAlive;
	}
	public Person isPersonAlive(Person person) {
		return person;
	}
	public List<Person> playXRound(int x) {
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		round.makePersonsAlive(persons, mapList, mapSize);
		for (int i = 0; i < x; i++) {
			round.killedOrRebornPersons(mapList, mapSize);
		}
		List<Person> personsListAlive = round.arePersonsAlive(mapList);
		return personsListAlive;
	}
}
