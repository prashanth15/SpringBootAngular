package org.planning.assignment.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.planning.assignment.model.Order;
import org.planning.assignment.model.OrderForm;
import org.planning.assignment.model.Unit;
import org.springframework.stereotype.Repository;
  
@Repository
public class OrderDAO {
  
    private static final Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
    
    private static final Map<Integer, Order> buyOrderMap = new HashMap<Integer, Order>();
    
    private static final Map<Integer, Order> sellOrderMap = new HashMap<Integer, Order>();
  
    static {
        initOrders();
    }
  
    private static void initOrders() {
    	
    	Order order1 = new Order(1,"BUY",21,5);
    	Order order2 = new Order(2,"BUY",20,10);
    	Order order3 = new Order(3,"BUY",19,10);
    	Order order4 = new Order(4,"SELL",23,10);
    	Order order5 = new Order(5,"SELL",26,5);
        
    	buyOrderMap.put(order1.getOrderId(), order1);
    	buyOrderMap.put(order2.getOrderId(), order2);
    	buyOrderMap.put(order3.getOrderId(), order3);
    	sellOrderMap.put(order4.getOrderId(), order4);
    	sellOrderMap.put(order5.getOrderId(), order5);
        
        orderMap.putAll(buyOrderMap);
        orderMap.putAll(sellOrderMap);
    }
  
    public Integer getMaxOrderId() {
        Set<Integer> keys = orderMap.keySet();
        Integer max = 0;
        for (Integer key : keys) {
            if (key > max) {
                max = key;
            }
        }
        return max;
    }
  
    public Order getOrder(Integer orderId) {
        return orderMap.get(orderId);
    }
  
    public Order addOrder(OrderForm orderForm) {
        Integer orderId= this.getMaxOrderId()+ 1;
        orderForm.setOrderId(orderId);
        Order newOrder = new Order(orderForm);
        if (orderForm.getOrderType().equals("BUY")){
        	buyOrderMap.put(newOrder.getOrderId(), newOrder);
        } else {
        	sellOrderMap.put(newOrder.getOrderId(), newOrder);
        }
        orderMap.put(newOrder.getOrderId(), newOrder);
        return newOrder;
    }
  
    public Order updateOrder(OrderForm orderForm) {
    	Order order = this.getOrder(orderForm.getOrderId());
        if(order!= null)  {
        	order.setOrderType(orderForm.getOrderType());
        	order.setPrice(orderForm.getPrice());
        	order.setVolume(orderForm.getVolume());
        }  
        return order;
    }
  
    public void deleteOrder(Integer orderId) {
    	orderMap.remove(orderId);
    	buyOrderMap.remove(orderId);
    	sellOrderMap.remove(orderId);
    }
  
    public List<Order> getAllOrders() {
        Collection<Order> c = orderMap.values();
        List<Order> list = new ArrayList<Order>();
        list.addAll(c);
        return list;
    }
    
    public List<Order> getAllBuyOrders() {
        Collection<Order> c = buyOrderMap.values();
        List<Order> list = new ArrayList<Order>();
        list.addAll(c);
        return list;
    }
  
    public List<Order> getAllSellOrders() {
        Collection<Order> c = sellOrderMap.values();
        List<Order> list = new ArrayList<Order>();
        list.addAll(c);
        return list;
    }
}
