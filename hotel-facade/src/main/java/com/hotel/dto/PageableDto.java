package com.hotel.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageableDto {
    private int page = 1;
    private int size = 25;

    List<Sort.Order> orders;

    public void addOrder(Sort.Order order) {
        if(this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.add(order);
    }

    public Pageable toPageable() {
        return new PageRequest(page - 1, size, orders == null ? null : new Sort(orders));
    }
}
