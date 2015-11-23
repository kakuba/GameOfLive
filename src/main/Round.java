package main;

import java.util.ArrayList;
import java.util.List;

public class Round {
	
	public List<Person> makeMapList(int mapSize) {
		List<Person> mapList = new ArrayList<>();
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				mapList.add(new Person(j, i));
			}
		}
		return mapList;
	}
	
	
	public void makePersonsAlive(List<Person> persons, List<Person> mapList, int mapSize) {
		int y;
		int x;
		for (int i = 0; i < persons.size(); i++) {
			x = persons.get(i).getX();
			y = persons.get(i).getY();
			mapList.get(y*mapSize + x).alivePerson();
		}
	}
	

	public List<Person> arePersonsAlive(List<Person> mapList){
		List<Person> alivePersonsList = new ArrayList<>();
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.get(i).isAlive()) {
				alivePersonsList.add(mapList.get(i));
			}
		}
		return alivePersonsList;
	}
	
	public void killedOrRebornPersons(List<Person> mapList, int mapSize) {
		resetNumberOfNeighborForAll(mapList);
		for (int i = 0; i < mapList.size(); i++) {
			mapList.get(i).countNumberOfNeighbor(mapList, mapList.get(i).getX(), mapList.get(i).getY(), mapSize);
		}
		for (int i = 0; i < mapList.size(); i++) {
			mapList.get(i).killedOrReborn();
		}
	}


	public void resetNumberOfNeighborForAll(List<Person> mapList) {
		for (int i = 0; i < mapList.size(); i++) {
			mapList.get(i).resetNumberOfNieghbor();
		}
	}

}
