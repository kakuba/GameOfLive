package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.Game;
import main.Person;
import main.Round;

public class GameTest {

	@Test
	public void shouldPersonBeAlive() {
		//given
		Person person = new Person(4, 4);
		person.alivePerson();
		//when
		boolean result = person.isAlive();
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldPersonBeNotAlive() {
		//given
		Person person = new Person(4, 4);
		person.alivePerson();
		//when
		person.killedOrReborn();
		boolean result = person.isAlive();
		//then
		Assert.assertFalse(result);
	}
	@Test
	public void shouldMakeMapList() {
		//given
		Round round = new Round();
		//when
		List<Person> mapList = round.makeMapList(5);
		//then
		Assert.assertEquals(25, mapList.size());
	}
	@Test
	public void shouldMakePersonsZeroAndThreeFormMapListAlive() {
		//given
		int mapSize = 2;
		List<Person> persons = Arrays.asList(new Person(0,0), new Person(1,1));
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		//when
		round.makePersonsAlive(persons, mapList, mapSize);
		//then
		Assert.assertTrue(mapList.get(0).isAlive());
		Assert.assertTrue(mapList.get(3).isAlive());
	}
	@Test
	public void shouldMakePersonsAliveListWithTwoPerson() {
		//given
		int mapSize = 2;
		List<Person> persons = Arrays.asList(new Person(0,0), new Person(1,1));
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		round.makePersonsAlive(persons, mapList, mapSize);
		//when
		List<Person> personsListAlive = round.arePersonsAlive(mapList);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("0,0");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("1,1");
	}
	@Test
	public void shouldSetNumberOfNeighborToThreeForOnePerson() {
		//given
		int mapSize = 2;
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		//when
		mapList.get(0).setNumberOfNeighbor(3);
		//then
		PersonAssert.assertThat(mapList.get(0)).hasNumberOfNeighbor(3);
	}
	@Test
	public void shouldResetNumberNeighborForAll() {
		//given
		int mapSize = 2;
		Round round = new Round();
		List<Person> mapList = round.makeMapList(mapSize);
		mapList.get(0).setNumberOfNeighbor(3);
		mapList.get(2).setNumberOfNeighbor(4);
		//when
		round.resetNumberOfNeighborForAll(mapList);
		//then
		PersonAssert.assertThat(mapList.get(0)).hasNumberOfNeighbor(0);
		PersonAssert.assertThat(mapList.get(2)).hasNumberOfNeighbor(0);
	}
	@Test
	public void shouldThreePersonsStayAliveAndOneReborn() {
		//given
		List<Person> persons = Arrays.asList(new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Round round = new Round();
		//when
		List<Person> mapList = round.makeMapList(mapSize);
		round.makePersonsAlive(persons, mapList, mapSize);
		round.killedOrRebornPersons(mapList, mapSize);
		List<Person> personsListAlive = round.arePersonsAlive(mapList);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("4,4");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("5,4");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("4,5");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("5,5");
	}
	@Test
	public void shouldFourPersonBeAliveGAME() {
		//given
		List<Person> persons = Arrays.asList(new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playRound();
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("4,4");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("5,4");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("4,5");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("5,5");
	}
	@Test
	public void shouldFourPersonBeAliveSecondRound() {
		//given
		List<Person> persons = Arrays.asList(new Person(0,0), new Person(0,1), new Person(1,1));
		int mapSize = 2;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playXRound(2);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("0,0");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("1,0");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("0,1");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("1,1");
	}
	@Test
	public void shouldEightPersonBeAliveFirstRound() {
		//given
		List<Person> persons = Arrays.asList(new Person(3,2), new Person(4,2),new Person(3,3),new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playXRound(1);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("3,2");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("4,2");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("3,3");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("3,4");
		PersonAssert.assertThat(personsListAlive.get(4)).hasName("4,4");
		PersonAssert.assertThat(personsListAlive.get(5)).hasName("5,4");
		PersonAssert.assertThat(personsListAlive.get(6)).hasName("4,5");
		PersonAssert.assertThat(personsListAlive.get(7)).hasName("5,5");
	}
	@Test
	public void shouldEightPersonBeAliveSecondRound() {
		//given
		List<Person> persons = Arrays.asList(new Person(3,2), new Person(4,2),new Person(3,3),new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playXRound(2);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("3,2");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("4,2");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("2,3");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("5,3");
		PersonAssert.assertThat(personsListAlive.get(4)).hasName("3,4");
		PersonAssert.assertThat(personsListAlive.get(5)).hasName("5,4");
		PersonAssert.assertThat(personsListAlive.get(6)).hasName("3,5");
		PersonAssert.assertThat(personsListAlive.get(7)).hasName("5,5");
	}
	@Test
	public void shouldSevenPersonBeAliveThirdRound() {
		//given
		List<Person> persons = Arrays.asList(new Person(3,2), new Person(4,2),new Person(3,3),new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playXRound(3);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("3,2");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("4,2");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("2,3");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("5,3");
		PersonAssert.assertThat(personsListAlive.get(4)).hasName("2,4");
		PersonAssert.assertThat(personsListAlive.get(5)).hasName("3,4");
		PersonAssert.assertThat(personsListAlive.get(6)).hasName("5,4");
	}
	@Test
	public void shouldSevenPersonBeAliveFourthRound() {
		//given
		List<Person> persons = Arrays.asList(new Person(3,2), new Person(4,2),new Person(3,3),new Person(4,4), new Person(4,5), new Person(5,5));
		int mapSize = 6;
		Game game = new Game(persons, mapSize);
		//when
		List<Person> personsListAlive = game.playXRound(4);
		//then
		PersonAssert.assertThat(personsListAlive.get(0)).hasName("3,2");
		PersonAssert.assertThat(personsListAlive.get(1)).hasName("4,2");
		PersonAssert.assertThat(personsListAlive.get(2)).hasName("2,3");
		PersonAssert.assertThat(personsListAlive.get(3)).hasName("5,3");
		PersonAssert.assertThat(personsListAlive.get(4)).hasName("2,4");
		PersonAssert.assertThat(personsListAlive.get(5)).hasName("3,4");
		PersonAssert.assertThat(personsListAlive.get(6)).hasName("4,4");
	}
	
	
}
