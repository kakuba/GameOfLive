package main;

public enum Direct {
	N(0,1),
	NE(1,1),
	E(1,0),
	SE(1,-1),
	S(0,-1),
	SW(-1,-1),
	W(-1,0),
	NW(-1,1);
	
	private int xValue;
	private int yValue;
	
	private Direct(int xValue, int yValue) {
		this.xValue = xValue;
		this.yValue = yValue;
	}
	
	public int getXValue() {
		return xValue;
	}
	public int getYValue() {
		return yValue;
	}
}
