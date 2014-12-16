package edu.untref.infmedica;

import ij.ImagePlus;
import ij.io.Opener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Image {

	private String name;
	private byte[] bytes;
	private String path;

	public Image(String name, String path) throws IOException {

		this.name = name;
		this.path = path;
		createBinary();
	}

	public String getName() {

		return this.name;
	}

	public byte[] getBytes() {

		return this.bytes;
	}

	public String getPath() {

		return this.path;
	}

	public int[] getHistogram() {

		Opener opener = new Opener();
		ImagePlus imgPlus = opener.openImage(getPath());
		return imgPlus.getProcessor().getHistogram();
	}

	private void createBinary() throws IOException {

		File file = new File(getPath());
		this.bytes = Files.readAllBytes(file.toPath());
	}

	public void setBytes(byte[] bytes) {

		this.bytes = bytes;
	}
}
