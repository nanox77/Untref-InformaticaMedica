package edu.untref.infmedica.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import edu.untref.infmedica.ConnectDB;

public class ConectionTest {

	@Test
	public void connect() {

		Connection connection = ConnectDB.getInstance().connect();
		Assert.assertNotNull(connection);
	}

	@Test
	public void connectInfoMedicaDB() {

		Connection connection = ConnectDB.getInstance().connectInfoMedicaDB();
		Assert.assertNotNull(connection);
	}
}
