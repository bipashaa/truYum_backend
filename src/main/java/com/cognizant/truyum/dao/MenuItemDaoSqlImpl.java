package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.controller.MenuItemController;
import com.cognizant.truyum.model.MenuItem;

@Component("menuItemDao")
public class MenuItemDaoSqlImpl implements MenuItemDao {
    private static PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        LOGGER.info("Start of getMenuItemListAdmin() in MenuItemDaoSqlImpl");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<MenuItem> menuItemsList = new ArrayList<>();
        try {
            Connection connection = ConnectionHandler.getConnection();
            String query = "select * from menu_item";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                boolean active = resultSet.getInt(4) == 1;
                Date dateOfLaunch = dateFormat.parse(resultSet.getString(5));
                String category = resultSet.getString(6);
                boolean freeDelivery = resultSet.getInt(7) == 1;
                MenuItem item = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
                menuItemsList.add(item);
            }
            preparedStatement.clearParameters();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        LOGGER.info("End of getMenuItemListAdmin() in MenuItemDaoSqlImpl");
        return menuItemsList;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        LOGGER.info("Start of getMenuItemListCustomer() in MenuItemDaoSqlImpl");
        List<MenuItem> menuItemsList = new ArrayList<>();
        try {
            Connection connection = ConnectionHandler.getConnection();
            String query = "select * from menu_item where me_active=true AND me_date_of_launch < now()";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                boolean active = resultSet.getInt(4) == 1;
                Date dateOfLaunch = resultSet.getDate(5);
                String category = resultSet.getString(6);
                boolean freeDelivery = resultSet.getInt(7) == 1;
                MenuItem item = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
                menuItemsList.add(item);
            }
            preparedStatement.clearParameters();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Start of getMenuItemListCustomer() in MenuItemDaoSqlImpl");
        return menuItemsList;
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        LOGGER.info("Start of modifyMenuItem() in MenuItemDaoSqlImpl");

        try {
            Connection connection = ConnectionHandler.getConnection();
            String query = "update menu_item set me_name = ?, me_price = ?, me_active = ?, me_date_of_launch = ?, me_category = ?, me_free_delivery = ? where me_id = ?";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.clearParameters();
            preparedStatement.setString(2, menuItem.getName());
            preparedStatement.setFloat(3, menuItem.getPrice());
            preparedStatement.setBoolean(4, menuItem.isActive());
            preparedStatement.setString(5, format.format(menuItem.getDateOfLaunch()));
            preparedStatement.setString(6, menuItem.getCategory());
            preparedStatement.setBoolean(7, menuItem.isFreeDelivery());
            preparedStatement.setLong(1, menuItem.getId());

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
        LOGGER.info("Start of modifyMenuItem() in MenuItemDaoSqlImpl");

    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {
        LOGGER.info("Start of getMenuItem() in MenuItemDaoSqlImpl");
        MenuItem menuItem = null;
        try {
            Connection connection = ConnectionHandler.getConnection();
            String query = "SELECT * FROM menu_item WHERE me_id =?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, menuItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                boolean active = resultSet.getInt(4) == 1;
                Date dateOfLaunch = resultSet.getDate(5);
                String category = resultSet.getString(6);
                boolean freeDelivery = resultSet.getInt(7) == 1;
                menuItem = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
            }
            preparedStatement.clearParameters();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Start of getMenuItem() in MenuItemDaoSqlImpl");
        return menuItem;
    }

}
