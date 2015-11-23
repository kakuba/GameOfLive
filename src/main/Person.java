package main;

import java.util.List;

public class Person {
	private int x;
	private int y;
	private String name;
	private int numberOfNeighbor = 0;
	private boolean live = false;

	public Person(int x, int y) {
		this.x = x;
		this.y = y;
		this.name = String.format("%d,%d", x, y);
	}
	
	
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getNumberOfNeighbor() {
		return numberOfNeighbor;
	}
	public boolean isAlive() {
		return live;
	}
	
	public void alivePerson() {
		live = true;
	}
	public void killedPerson() {
		live = false;
	}
	
	public void resetNumberOfNieghbor() {
		numberOfNeighbor = 0;
	}
	
	public void setNumberOfNeighbor(int number) {
		numberOfNeighbor = number;
	}
	
	public void killedOrReborn() {
		if (numberOfNeighbor < 2 && live) {
			live = false;
		}  else if ((numberOfNeighbor == 2 || numberOfNeighbor == 3) && live) {
			live = true;
		} else if (numberOfNeighbor > 3 && live) {
			live = false;
		} else if (numberOfNeighbor == 3 && !live) {
			live = true;
		}
	}
	public void countNumberOfNeighbor(List<Person> mapList, int x, int y, int mapSize) {
		for (int i = 0; i < mapList.size(); i++) {
			addNeighbor(mapList.get(i), mapSize);
		}
	}
	
	private void addNeighbor(Person person, int mapSize) {
		checkDirectNeighbor(Direct.N, person, mapSize);
		checkDirectNeighbor(Direct.NE, person, mapSize);
		checkDirectNeighbor(Direct.E, person, mapSize);
		checkDirectNeighbor(Direct.SE, person, mapSize);
		checkDirectNeighbor(Direct.S, person, mapSize);
		checkDirectNeighbor(Direct.SW, person, mapSize);
		checkDirectNeighbor(Direct.W, person, mapSize);
		checkDirectNeighbor(Direct.NW, person, mapSize);
	}
	
	private void checkDirectNeighbor(Direct n, Person person, int mapSize) {
		if (checkMapSize(n, person, mapSize)) {
			if (person.getX() == x + n.getXValue() && person.getY() == y + n.getYValue() && person.isAlive()) {
				numberOfNeighbor++;
				
			}
		}
	}

	private boolean checkMapSize(Direct n,Person person, int mapSize) {
		boolean result = false;
		if (x + n.getXValue() <= mapSize && x + n.getXValue() >= 0 && y + n.getYValue() <= mapSize && y + n.getYValue() >= 0) {
			result = true;
		}
		return result;
	}
}
