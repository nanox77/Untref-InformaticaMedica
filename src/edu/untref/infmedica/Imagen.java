package edu.untref.infmedica;

import ij.ImagePlus;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {

	private String name;
	private String path;
	private BufferedImage image;
	private int paciente;

	public Imagen(String name) throws IOException {

		this.name = name;
	}

	public Imagen(String name, String path) throws IOException {

		this.name = name;
		this.path = path;
		this.image = ImageIO.read(new File(path));
	}

	public String getName() {

		return this.name;
	}

	public byte[] getBytes() throws IOException {

		return extractBytes();
	}

	private byte[] extractBytes() throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		ImageIO.write(this.image, "jpg", baos);
		baos.flush();
		// String base64String = Base64.encodeBytes(baos.toByteArray());
		byte[] bytes = baos.toByteArray();
		baos.close();
		return bytes;
	}

	public String getPath() {

		return this.path;
	}

	public int[] getHistogram() {

		ImagePlus imagePlus = new ImagePlus(this.name, this.image);
		return imagePlus.getProcessor().getHistogram();
	}

	public void setBytes(byte[] bytes) throws ClassNotFoundException,
			IOException {

		this.image = ImageIO.read(new ByteArrayInputStream(bytes));
	}

	public void setImage(BufferedImage image) {

		this.image = image;
	}

	public Image getImage() {

		return this.image;
	}

	public int getPaciente() {

		return this.paciente;
	}

	public void setPaciente(int paciente) {

		this.paciente = paciente;
	}
}
