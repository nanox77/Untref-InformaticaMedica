package edu.untref.infmedica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageDAO {

	public void save(Imagen image) {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		try {
			String query = "INSERT INTO images VALUES (?, ?, 'histogram')";
			String histogram = Arrays.toString(image.getHistogram());
			histogram = histogram.replace('[', '{');
			histogram = histogram.replace(']', '}');
			query = query.replace("histogram", histogram);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, image.getName());
			ps.setBytes(2, image.getBytes());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Imagen> getAll() throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("SELECT name, image FROM images");
		ResultSet rs = ps.executeQuery();
		List<Imagen> images = new ArrayList<Imagen>();
		while (rs.next()) {
			String name = rs.getString(1);
			InputStream is = rs.getBinaryStream(2);
			byte[] bytes = getArrayByteFromInputStream(is);
			Imagen image = new Imagen(name);
			image.setBytes(bytes);
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

	public Imagen getImage(String name) throws Exception {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		PreparedStatement ps = connection
				.prepareStatement("SELECT name, image FROM images WHERE name = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		rs.next();
		InputStream is = rs.getBinaryStream(2);
		byte[] bytes = getArrayByteFromInputStream(is);
		is.close();
		rs.close();
		ps.close();
		Imagen image = new Imagen(name);
		image.setBytes(bytes);
		return image;
	}
}
