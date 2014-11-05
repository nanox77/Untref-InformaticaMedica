package edu.untref.infmedica.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import edu.untref.infmedica.ConnectDB;

public class ConectionTest {

	@Test
	public void conectionSuccessfull() {
		Connection connection = ConnectDB.getInstance().createConnection();
		Assert.assertNotNull(connection);
	}

}