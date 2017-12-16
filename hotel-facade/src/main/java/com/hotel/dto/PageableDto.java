package com.hotel.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class PageableDto {
    private int page = 1;
    private int size = 25;

    public Pageable toPageable() {
        return new PageRequest(page - 1, size);
    }
}
