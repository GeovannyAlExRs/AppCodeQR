package com.ec.code.qr.web.app.model;

public class CodeQR {

	private String code;
	
	private String description;

	private int disc_bus;

	private String img;
	
	public CodeQR() {
	}

	public CodeQR(String code, String description, int disc_bus, String img) {
		this.code = code;
		this.description = description;
		this.disc_bus = disc_bus;
		this.img = img;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisc_bus() {
		return disc_bus;
	}

	public void setDisc_bus(int disc_bus) {
		this.disc_bus = disc_bus;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "CodeQR [code=" + code + ", description=" + description + ", disc_bus=" + disc_bus + ", img=" + img
				+ "]";
	}
}