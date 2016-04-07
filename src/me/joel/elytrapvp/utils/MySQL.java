package me.joel.elytrapvp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.Plugin;

import me.joel.elytrapvp.Main;

public class MySQL extends Database {
	private final String user;
	private final String database;
	private final String password;
	private final String port;
	private final String hostname;

	private Connection connection;

	/**
	 * Creates a new MySQL instance
	 * 
	 * @param plugin
	 *            Plugin instance
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 */
	public MySQL(Plugin plugin, String hostname, int port, String database, String username, String password) {
		super(plugin);
		this.hostname = hostname;
		this.port = port + "";
		this.database = database;
		this.user = username;
		this.password = password;
		this.connection = null;
	}

	public void createTable(String name, String values) {
		updateSQL("CREATE TABLE IF NOT EXISTS " + name + "(" + values + ")");
	}

	@Override
	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
		} catch (SQLException e) {
			Main.error(e);
		} catch (ClassNotFoundException e) {
			Main.error(e);
		}
		return connection;
	}

	@Override
	public boolean checkConnection() {
		return connection != null;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				Main.error(e);
			}
		}
	}

	public ResultSet querySQL(String query) {
		Connection c = openConnection();

		if (checkConnection()) {
			c = getConnection();
		} else {
			c = openConnection();
		}

		Statement s = null;

		try {
			s = c.createStatement();
		} catch (SQLException e) {
			Main.error(e);
		}

		ResultSet ret = null;

		try {
			ret = s.executeQuery(query);
		} catch (SQLException e) {
			Main.error(e);
		}

		closeConnection();

		return ret;
	}

	public void updateSQL(String update) {

		Connection c = openConnection();

		if (checkConnection()) {
			c = getConnection();
		} else {
			c = openConnection();
		}

		Statement s = null;

		try {
			s = c.createStatement();
			s.executeUpdate(update);
		} catch (SQLException e) {
			Main.error(e);
		}
		closeConnection();
	}
}

abstract class Database {

	/**
	 * Plugin instance, use for plugin.getDataFolder() and plugin.getLogger()
	 */
	protected Plugin plugin;

	/**
	 * Creates a new Database
	 * 
	 * @param plugin
	 *            Plugin instance
	 */
	protected Database(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Opens a connection with the database
	 * 
	 * @return Connection opened
	 */
	public abstract Connection openConnection();

	/**
	 * Checks if a connection is open with the database
	 * 
	 * @return true if a connection is open
	 */
	public abstract boolean checkConnection();

	/**
	 * Gets the connection with the database
	 * 
	 * @return Connection with the database, null if none
	 */
	public abstract Connection getConnection();

	/**
	 * Closes the connection with the database
	 */
	public abstract void closeConnection();
}
