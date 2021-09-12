package model;

import java.io.Serializable;

public class Billboard implements Serializable{

	private static final long serialVersionUID = 1L;
	private double width,height;
	private boolean inUse;
	private String brand;
	
	public Billboard(double width, double height, boolean inUse, String brand) {
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}
	
	public double calculateArea() {
		double area=0;
		area=width*height;
		return area;
	}
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	

}
