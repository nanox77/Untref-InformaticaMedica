package edu.untref.infmedica.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.untref.infmedica.Image;
import edu.untref.infmedica.ImageDAO;

public class ImageTest {

	@Before
	public void clearDB() throws Exception {
		ImageDAO dao = new ImageDAO();
		dao.delete();
	}

	@Test
	public void saveImage() throws Exception {
		Image image = new Image();
		image.setPath("resources/radiografia_mano.jpg");
		image.setName("Radiografia de mano");
		ImageDAO dao = new ImageDAO();
		dao.save(image);

		List<Image> images = dao.getAll();

		Assert.assertEquals(1, images.size());
	}
}
