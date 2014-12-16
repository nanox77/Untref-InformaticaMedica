package edu.untref.infmedica.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.untref.infmedica.Image;
import edu.untref.infmedica.ImageDAO;

public class ImageTest {

	private ImageDAO dao;

	@Before
	public void clearDB() throws Exception {

		this.dao = new ImageDAO();
		this.dao.delete();
	}

	@Test
	public void saveImage() throws Exception {

		String name = "Radiografía de mano";
		String path = "resources/radiografia_mano.jpg";
		Image image = new Image(name, path);
		this.dao.save(image);
		List<Image> images = this.dao.getAll();
		Assert.assertEquals(1, images.size());
	}
}
