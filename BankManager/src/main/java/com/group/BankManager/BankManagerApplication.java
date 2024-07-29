package com.group.BankManager;

import com.group.BankManager.beans.Account;
import com.group.BankManager.beans.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BankManagerApplication {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_manager_db";
	private static final String USER = "root";
	private static final String PASS = "anan1234";
	private Connection conn;

	public static void main(String[] args) {
		SpringApplication.run(BankManagerApplication.class, args);
	}

	public BankManagerApplication() throws SQLException {
		this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
		this.conn.setAutoCommit(false); // Disable auto-commit mode
	}

	public void createCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer (customerID, first_Name, last_Name, email, address) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, customer.getCustomerID());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getEmail());
			pstmt.setString(5, customer.getAddress());
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
			System.out.println("Customer created: " + customer);
		}
	}

	public Customer getCustomerById(long customerId) throws SQLException {
		String sql = "SELECT * FROM customer WHERE customerid = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Customer(
						rs.getLong("customerid"),
						rs.getString("first_Name"),
						rs.getString("last_Name"),
						rs.getString("email"),
						rs.getString("address")
				);
			}
			return null;
		}
	}

	public void updateCustomer(Customer customer) throws SQLException {
		String sql = "UPDATE customer SET first_Name = ?, last_Name = ?, email = ?, address = ? WHERE customerID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getAddress());
			pstmt.setLong(5, customer.getCustomerID());
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
			System.out.println("Customer updated: " + customer);
		}
	}

	/*public void deleteCustomer(long customerId) throws SQLException {
		// First delete all accounts associated with the customer
		String deleteAccountSql = "DELETE FROM account WHERE customerid = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(deleteAccountSql)) {
			pstmt.setLong(1, customerId);
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
		}

		// Then delete the customer
		String deleteCustomerSql = "DELETE FROM customer WHERE customerid = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(deleteCustomerSql)) {
			pstmt.setLong(1, customerId);
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
			System.out.println("Customer deleted: " + customerId);
		}
	}*/

	public void createAccount(Account account) throws SQLException {
		String sql = "INSERT INTO account (accountid, customerid, balance) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, account.getAccountID());
			pstmt.setLong(2, account.getCustomer().getCustomerID());
			pstmt.setDouble(3, account.getBalance());
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
			System.out.println("Account created: " + account);
		}
	}

	public Account getAccountById(long accountId) throws SQLException {
		String sql = "SELECT * FROM account WHERE accountid = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, accountId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Customer customer = getCustomerById(rs.getLong("customerid"));
				return new Account(
						rs.getLong("accountid"),
						rs.getDouble("balance"),
						customer
				);
			}
			return null;
		}
	}

	public void updateAccountBalance(long accountId, double newBalance) throws SQLException {
		String sql = "UPDATE account SET balance = ? WHERE accountid = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDouble(1, newBalance);
			pstmt.setLong(2, accountId);
			pstmt.executeUpdate();
			conn.commit(); // Commit transaction
			System.out.println("Account balance updated: " + newBalance);
		}
	}

	@Bean
	CommandLineRunner runTests() {
		return args -> {
			try {
				BankManagerApplication app = new BankManagerApplication();

				// Check if customer already exists
				if (app.getCustomerById(1) == null) {
					// Create a new customer
					Customer customer = new Customer();
					customer.setCustomerID(1L);
					customer.setFirstName("John");
					customer.setLastName("Doe");
					customer.setEmail("johndoe@example.com");
					customer.setAddress("123 Main St");
					app.createCustomer(customer);
				} else {
					System.out.println("Customer with ID 1 already exists.");
				}

				// Check if account already exists
				if (app.getAccountById(1) == null) {
					// Create a new account for the customer
					Customer customer = app.getCustomerById(1);
					Account account = new Account();
					account.setAccountID(1L);
					account.setCustomer(customer);
					account.setBalance(1000.0);
					app.createAccount(account);
				} else {
					System.out.println("Account with ID 1 already exists.");
				}

				// Update customer information
				Customer customer = app.getCustomerById(1);
				if (customer != null) {
					customer.setFirstName("Jane");
					customer.setLastName("Doe");
					customer.setEmail("janedoe@example.com");
					customer.setAddress("456 Elm St");
					app.updateCustomer(customer);
				}

				// Update account balance
				app.updateAccountBalance(1, 2000.0);

				// Delete customer (this will now also delete associated accounts)
				//app.deleteCustomer(1);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
	}
}
