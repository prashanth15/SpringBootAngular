package org.planning.assignment.controller;

import java.util.List;
import org.planning.assignment.dao.OrderDAO;
import org.planning.assignment.model.Order;
import org.planning.assignment.model.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
  
@RestController 
public class MainRESTController {
    
    @Autowired
    private OrderDAO orderDAO;
  
  
    @RequestMapping(value = "/orders", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Order> getOrders() {
        List<Order> list = orderDAO.getAllOrders();
        return list;
    }
    
    @RequestMapping(value = "/order/{orderId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Order getOrder(@PathVariable("orderId") Integer orderId) {
        return orderDAO.getOrder(orderId);
    }
    
    
    @RequestMapping(value = "/order", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Order addOrder(@RequestBody OrderForm orderForm) {  
        System.out.println("(Service Side) Creating order with orderId: " + orderForm.getOrderId());
        return orderDAO.addOrder(orderForm);
    }
    
    @RequestMapping(value = "/order", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Order updateOrder(@RequestBody OrderForm orderForm) {  
        System.out.println("(Service Side) Editing order with orderId: " + orderForm.getOrderId());  
        return orderDAO.updateOrder(orderForm);
    }
    
    
    @RequestMapping(value = "/order/{orderId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteOrder(@PathVariable("orderId") Integer orderId) { 
        System.out.println("(Service Side) Deleting order with orderId: " + orderId);  
        orderDAO.deleteOrder(orderId);
    }
    
    @RequestMapping(value = "/buyOrders", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Order> getBuyOrders() {
        List<Order> list = orderDAO.getAllBuyOrders();
        return list;
    }
    
    @RequestMapping(value = "/sellOrders", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Order> getSellOrders() {
        List<Order> list = orderDAO.getAllSellOrders();
        return list;
    }
}