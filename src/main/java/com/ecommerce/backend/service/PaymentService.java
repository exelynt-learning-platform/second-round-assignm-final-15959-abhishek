//package com.ecommerce.backend.service;
//
//import com.ecommerce.backend.dto.PaymentRequest;
//import com.ecommerce.backend.entity.OrderEntity;
//import com.ecommerce.backend.entity.enums.OrderStatus;
//import com.ecommerce.backend.exception.BadRequestException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PaymentService {
//
//    public String checkout(String username, PaymentRequest request, OrderService orderService) {
//
//        OrderEntity order = orderService.getMyOrder(username, request.orderId());
//
//        if (order.getStatus() == OrderStatus.PAID) {
//            throw new BadRequestException("Order already paid");
//        }
//
//        // 🔥 PayPal sandbox demo link
//        String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=ORDER_" + order.getId();
//
//        // Save reference
//        order.setPaymentReference("PAYPAL_ORDER_" + order.getId());
//
//        // Demo: mark paid
//        order.setStatus(OrderStatus.PAID);
//
//        orderService.save(order);
//
//        return approvalUrl;
//    }
//}

package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.PaymentRequest;
import com.ecommerce.backend.entity.OrderEntity;
import com.ecommerce.backend.entity.enums.OrderStatus;
import com.ecommerce.backend.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String checkout(String username, PaymentRequest request) {

        OrderEntity order = orderService.getMyOrder(username, request.orderId());

        if (order.getStatus() == OrderStatus.PAID) {
            throw new BadRequestException("Order already paid");
        }

        String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=ORDER_" + order.getId();

        order.setPaymentReference("PAYPAL_ORDER_" + order.getId());
        order.setStatus(OrderStatus.PAID);

        orderService.save(order);

        return approvalUrl;
    }
}