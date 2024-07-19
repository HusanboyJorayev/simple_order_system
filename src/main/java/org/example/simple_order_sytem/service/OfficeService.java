package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.OfficeDto;
import org.example.simple_order_sytem.dto.OrderDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OfficeService {
    Response<OfficeDto> create(OfficeDto dto);

    Response<OfficeDto> get(Integer id);

    Response<OfficeDto> update(OfficeDto dto, Integer id);

    Response<OfficeDto> delete(Integer id);

    Response<List<OfficeDto>> getAll();

    Response<List<OfficeDto>> getFilter(Integer id,
                                        String city,
                                        String phone,
                                        String address1,
                                        String address2,
                                        String state,
                                        String country,
                                        Integer postCode,
                                        String territory);

    Response<Page<OfficeDto>> getPage(Integer page, Integer size);
}
