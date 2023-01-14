package com.mkrzyszczyk.shop.admin.order.controller.mapper;

import com.mkrzyszczyk.shop.admin.order.controller.dto.AdminOrderDto;
import com.mkrzyszczyk.shop.admin.order.model.AdminOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminOrderMapper {

    public static Page<AdminOrderDto> mapToPageDtos(Page<AdminOrder> orders) {
        return new PageImpl<>(mapToDtoList(orders.getContent()), orders.getPageable(), orders.getTotalElements());
    }

    private static List<AdminOrderDto> mapToDtoList(List<AdminOrder> content) {
        return content.stream()
                .map(AdminOrderMapper::mapToAdminOrderDto)
                .toList();
    }

    private static AdminOrderDto mapToAdminOrderDto(AdminOrder adminOrder) {
        return AdminOrderDto.builder()
                .id(adminOrder.getId())
                .orderStatus(adminOrder.getOrderStatus())
                .placementDate(adminOrder.getPlacementDate())
                .grossValue(adminOrder.getGrossValue())
                .build();
    }
}
