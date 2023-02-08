package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.common.mail.EmailClientService;
import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.repository.CartItemRepository;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import com.mkrzyszczyk.shop.order.model.Order;
import com.mkrzyszczyk.shop.order.model.Payment;
import com.mkrzyszczyk.shop.order.model.Shipment;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderListDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;
import com.mkrzyszczyk.shop.order.repository.OrderRepository;
import com.mkrzyszczyk.shop.order.repository.OrderRowRepository;
import com.mkrzyszczyk.shop.order.repository.PaymentRepository;
import com.mkrzyszczyk.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mkrzyszczyk.shop.order.service.mapper.OrderDtoMapper.mapToOrderListDto;
import static com.mkrzyszczyk.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static com.mkrzyszczyk.shop.order.service.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Override
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order order = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, order.getId(), shipment);
        clearOrderCart(cart);
        sendConfirmationEmail(order);
        return createOrderSummary(payment, order);
    }

    @Override
    public List<OrderListDto> getOrderForCustomer(Long userId) {
        return mapToOrderListDto(orderRepository.findByUserId(userId));
    }

    private void sendConfirmationEmail(Order order) {
        emailClientService.getInstance().
                send(order.getEmail(), "Your order has been placed", createEmailMessage(order));
    }

    private void clearOrderCart(Cart cart) {
        cartItemRepository.deleteByCartId(cart.getId());
        cartRepository.deleteByCartId(cart.getId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductsRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveProductsRows(Cart cart, Long orderId) {
        cart.getItems().stream().map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem))
                .forEach(orderRowRepository::save);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }
}