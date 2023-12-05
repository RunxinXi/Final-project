package mie.ether_example;


import java.nio.file.Files;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.toronto.dbservice.config.MIE354DBHelper;
import edu.toronto.dbservice.types.EtherAccount;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIsRegisteredTask {

	private Connection connection;

	public CheckIsRegisteredTask(Connection connection) {
		this.connection = connection;
	}

	public boolean isProductRegistered(String productId) {
		String query = "SELECT COUNT(*) FROM product_list WHERE product_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

