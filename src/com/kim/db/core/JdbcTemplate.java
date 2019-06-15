package com.kim.db.core;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    //    需要预处理语句的创建对象对象， 需要结果集
    //   只有runtimeError 不用try catch
    public void query(PreparedStatementCreator psCreator, RowCallbackHandler callbackHandler) throws DataAccessException {
        /**
         * 执行查询操作
         */

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
//            建立数据库连接
            connection = DBHelp.getConnection();
//            创建语句对象
//            为什么以接口形式
            preparedStatement = psCreator.createPreparedStatement(connection);
//            结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                callbackHandler.procesRow(resultSet);
            }
        } catch (SQLException e) {
            throw new DataAccessException("SQL语句错误", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("连接数据库失败", e);
        } finally {
            try {
//                释放资源
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException("数据库释放资源失败", e);
            }
        }

    }

    public static void update(PreparedStatementCreator psCreator) throws DataAccessException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBHelp.getConnection();
            preparedStatement = psCreator.createPreparedStatement(connection);
            int count = preparedStatement.executeUpdate();
            System.out.println("执行成功 " + count + "条");


        } catch (SQLException e) {
            throw new DataAccessException("SQL语句错误", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("数据库连接失败", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException("数据库释放错误", e);
            }
        }

    }

}
