package com.kim.layerdemo.dao.imp;

import com.kim.db.core.JdbcTemplate;
import com.kim.db.core.PreparedStatementCreator;
import com.kim.db.core.RowCallbackHandler;
import com.kim.layerdemo.dao.CustomerDao;
import com.kim.layerdemo.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpJdbc implements CustomerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    List<Customer> customerList;

    @Override
    public Customer create(Customer customer) {
        String sql = "inset into Customers (id, name, password, address, phone, birthday) values(?,?,?,?,?,?)";
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,  customer.getId());
                preparedStatement.setString(2,  customer.getName());
                preparedStatement.setString(3,  customer.getPassword());
                preparedStatement.setString(4,  customer.getAddress());
                preparedStatement.setString(5,  customer.getPhone());
                preparedStatement.setLong(6,  customer.getBirthday().getTime());
                return  preparedStatement;
            }
        });
        return customer;
    }

    @Override
    public void modify(Customer customer) {
        String sql = "update Customers set name=?, password=?, address=?, phone=?, birthday=? where id=?";
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,  customer.getId());
                return  preparedStatement;
            }
        });

    }

    @Override
    public void remove(Customer customer) {
        String sql = "delete Customers  where id=?";
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,  customer.getName());
                preparedStatement.setString(2,  customer.getPassword());
                preparedStatement.setString(3,  customer.getAddress());
                preparedStatement.setString(4,  customer.getPhone());
                preparedStatement.setLong(5,  customer.getBirthday().getTime());
                preparedStatement.setString(6,  customer.getId());
                return  preparedStatement;
            }
        });

    }

    @Override
    public Customer findByPK(String pk) {
        String sql = "select * from Customers where id = ?";
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, pk);
                return preparedStatement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void procesRow(ResultSet resultSet) throws SQLException {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddress(resultSet.getString("address"));
                customer.setBirthday(new Date(resultSet.getLong("birthday")));
                customerList.add(customer);
            }
        });
        if(customerList.size() == 1){
            return customerList.get(0);
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from Customers ";
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                return preparedStatement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void procesRow(ResultSet resultSet) throws SQLException {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddress(resultSet.getString("address"));
                customer.setBirthday(new Date(resultSet.getLong("birthday")));
                customerList.add(customer);
            }
        });
        return customerList;
    }
}
