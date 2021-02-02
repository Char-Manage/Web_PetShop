package org.csu.wr.persistence.impl;
import org.csu.wr.domain.Orders;
import org.csu.wr.persistence.DBUtil;
import org.csu.wr.persistence.OrdersDAO;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    private static final String FIND_ORDER_BY_ORDERID="SELECT*FROM orders WHERE orderid=?";
    private static final String GET_ORDERSBYUSERNAME = "select * from orders where userid = ?";
    private static final String GET_ORDER_INFO="SELECT*FROM orders WHERE orderid=?";
    private static final String INSERT_ORDER = "insert into orders (orderid,userid,orderdate,shipaddr1,shipaddr2,shipcity,shipstate,shipzip,shipcountry,billaddr1,billaddr2,billcity,billstate,billzip,\n" +
            "                        billcountry,courier,totalprice,billtofirstname,billtolastname,shiptofirstname,shiptolastname,creditcard,exprdate,cardtype,locale) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    @Override
    public ArrayList<Orders> getOrdersByUsername(String username) {
        ArrayList<Orders> list = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERSBYUSERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                int orderId = resultSet.getInt(1);
                String userid = resultSet.getString(2);
                Date orderDate = resultSet.getDate(3);
                String shipAddress1 = resultSet.getString(4);
                String shipAddress2 = resultSet.getString(5);
                String shipCity = resultSet.getString(6);
                String shipState = resultSet.getString(7);
                String shipZip = resultSet.getString(8);
                String shipCountry = resultSet.getString(9);
                String billAddress1 = resultSet.getString(10);
                String billAddress2 = resultSet.getString(11);
                String billCity = resultSet.getString(12);
                String billState = resultSet.getString(13);
                String billZip = resultSet.getString(14);
                String billCountry = resultSet.getString(15);
                String courier = resultSet.getString(16);
                BigDecimal totalPrice = resultSet.getBigDecimal(17);
                String billToFirstName = resultSet.getString(18);
                String billToLastName = resultSet.getString(19);
                String shipToFirstName = resultSet.getString(20);
                String shipToLastName = resultSet.getString(21);
                String creditCard = resultSet.getString(22);
                String expiryDate = resultSet.getString(23);
                String cardType = resultSet.getString(24);
                String locale = resultSet.getString(25);
                Orders orders = new Orders(orderId,userid,orderDate,shipAddress1,shipAddress2,shipCity,shipState,shipZip,shipCountry,billAddress1,billAddress2,billCity,billState,billZip,
                        billCountry,courier,totalPrice,billToFirstName,billToLastName,shipToFirstName,shipToLastName,creditCard,expiryDate,cardType,locale);
                list.add(orders);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  list;
    }

    @Override
    public Orders getOrder(int oId) {
        Orders orders = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ORDERID);
            preparedStatement.setInt(1,oId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int orderId = resultSet.getInt(1);
                String userid = resultSet.getString(2);
                Date orderDate = resultSet.getDate(3);
                String shipAddress1 = resultSet.getString(4);
                String shipAddress2 = resultSet.getString(5);
                String shipCity = resultSet.getString(6);
                String shipState = resultSet.getString(7);
                String shipZip = resultSet.getString(8);
                String shipCountry = resultSet.getString(9);
                String billAddress1 = resultSet.getString(10);
                String billAddress2 = resultSet.getString(11);
                String billCity = resultSet.getString(12);
                String billState = resultSet.getString(13);
                String billZip = resultSet.getString(14);
                String billCountry = resultSet.getString(15);
                String courier = resultSet.getString(16);
                BigDecimal totalPrice = resultSet.getBigDecimal(17);
                String billToFirstName = resultSet.getString(18);
                String billToLastName = resultSet.getString(19);
                String shipToFirstName = resultSet.getString(20);
                String shipToLastName = resultSet.getString(21);
                String creditCard = resultSet.getString(22);
                String expiryDate = resultSet.getString(23);
                String cardType = resultSet.getString(24);
                String locale = resultSet.getString(25);
                orders = new Orders(orderId,userid,orderDate,shipAddress1,shipAddress2,
                        shipCity,shipState,shipZip,shipCountry,billAddress1,billAddress2,billCity, billState,billZip,
                        billCountry,courier,totalPrice,billToFirstName
                        ,billToLastName,shipToFirstName,shipToLastName,creditCard,expiryDate,cardType,locale);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return orders;
    }
    @Override
    public void insertOrder(Orders order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setString(2,order.getUsername());
            preparedStatement.setDate(3,(java.sql.Date)order.getOrderDate());
            preparedStatement.setString(4,order.getShipAddress1());
            preparedStatement.setString(5,order.getShipAddress2());
            preparedStatement.setString(6,order.getShipCity());
            preparedStatement.setString(7,order.getShipState());
            preparedStatement.setString(8,order.getShipZip());
            preparedStatement.setString(9,order.getShipCountry());
            preparedStatement.setString(10,order.getBillAddress1());
            preparedStatement.setString(11,order.getBillAddress2());
            preparedStatement.setString(12,order.getBillCity());
            preparedStatement.setString (13,order.getBillState());
            preparedStatement.setString(14,order.getBillZip());
            preparedStatement.setString(15,order.getBillCountry());
            preparedStatement.setString(16,order.getCourier());
            preparedStatement.setBigDecimal(17,order.getTotalPrice());
            preparedStatement.setString(18,order.getBillToFirstName());
            preparedStatement.setString(19,order.getBillToLastName());
            preparedStatement.setString(20,order.getShipToFirstName());
            preparedStatement.setString(21,order.getShipToLastName());
            preparedStatement.setString(22,order.getCreditCard());
            preparedStatement.setString(23,order.getExpiryDate());
            preparedStatement.setString(24,order.getCardType());
            preparedStatement.setString(25,order.getLocale());
            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Orders order) {

    }

    public static void main(String[] args) {
        OrdersDAOImpl imp = new OrdersDAOImpl();
        Orders orders = new Orders();
        orders.setOrderId(1);
        orders.setUsername("wr");
        imp.insertOrder(orders);
    }
}
