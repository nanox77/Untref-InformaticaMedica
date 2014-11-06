package edu.untref.infmedica;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

	public void save(Image image) {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		try {
			File file = new File(image.getPath());
			FileInputStream fis = new FileInputStream(file);
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO images VALUES (?, ?)");
			ps.setString(1, image.getName());
			ps.setBinaryStream(2, fis, (int) file.length());
			ps.executeUpdate();
			ps.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Image> getAll() throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("SELECT name, image FROM images");
		ResultSet rs = ps.executeQuery();
		List<Image> images = new ArrayList<Image>();
		Image image = null;
		while (rs.next()) {
			image = new Image();
			image.setName(rs.getString(1));
			InputStream is = rs.getBinaryStream(2);
			image.setImage(getArrayByteFromInputStream(is));
			images.add(image);
			is.close();
		}
		rs.close();
		ps.close();
		return images;
	}

	private byte[] getArrayByteFromInputStream(InputStream is) {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		try {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	public void delete() throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("DELETE FROM images");
		ps.executeUpdate();
		ps.close();
	}

	public Image getImage(String name) throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("SELECT name, image FROM images WHERE name = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		Image image = new Image();
		image.setName(rs.getString(1));
		InputStream is = rs.getBinaryStream(2);
		image.setImage(getArrayByteFromInputStream(is));
		is.close();
		rs.close();
		ps.close();
		return image;
	}
}
