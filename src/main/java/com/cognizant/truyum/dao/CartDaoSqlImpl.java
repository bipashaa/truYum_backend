package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.controller.MenuItemController;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Component
public class CartDaoSqlImpl implements CartDao {
	private static PreparedStatement preparedStatement = null;

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@Override
	public void addCartItem(long userId, long menuItemId) {
		LOGGER.info("Start - CartDaoSqlImpl : addCartItem");
		try {

			Connection connection = ConnectionHandler.getConnection();

			String query = "insert into cart (ct_us_id, ct_pr_id) values(?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, menuItemId);

			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("Query Successful");
			} else {
				System.out.println("Query Unsuccessful");
			}
			preparedStatement.clearParameters();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		LOGGER.info("End - CartDaoSqlImpl : addCartItem");

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		LOGGER.info("Start - CartDaoSqlImpl : getAllCartItems");
		List<MenuItem> menuItemList = new ArrayList<>();
		Cart cart = new Cart(menuItemList, 0);
		double total = 0;
		int count = 0;
		try {
			Connection connection = ConnectionHandler.getConnection();
			String query = "SELECT * FROM menu_item JOIN cart ON menu_item.me_id = cart.ct_pr_id WHERE cart.ct_us_id = ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setLong(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				count++;
				long id = resultSet.getLong("me_id");
				String name = resultSet.getString("me_name");
				float price = resultSet.getFloat("me_price");
				total += price;
				boolean active = resultSet.getInt("me_active") == 1;
				Date dateOfLaunch = resultSet.getDate("me_date_of_launch");
				String category = resultSet.getString("me_category");
				boolean freeDelivery = resultSet.getInt("me_free_delivery") == 1;
				MenuItem menuItem = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
				menuItemList.add(menuItem);
			}
			preparedStatement.clearParameters();
			if (count == 0) {
				throw new CartEmptyException();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cart.setMenuItemList(menuItemList);
		cart.setTotal(total);
		LOGGER.info("End - CartDaoSqlImpl : getAllCartItems");
		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		LOGGER.info("Start - CartDaoSqlImpl : removeCartItem");

		try {
			Connection connection = ConnectionHandler.getConnection();
			String query = "delete from cart where ct_us_id = ? AND ct_pr_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, menuItemId);

			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("Query Successful");
			} else {
				System.out.println("Query Unsuccessful");
			}

			preparedStatement.clearParameters();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End - CartDaoSqlImpl : removeCartItem");

	}

}
