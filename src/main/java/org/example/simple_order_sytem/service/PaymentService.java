package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.PaymentDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaymentService {
    Response<PaymentDto> create(PaymentDto paymentDto);

    Response<PaymentDto> get(Integer id);

    Response<PaymentDto> update(PaymentDto paymentDto, Integer id);

    Response<PaymentDto> delete(Integer id);

    Response<List<PaymentDto>> getAll();

    Response<List<PaymentDto>> getFilter(Integer id,
                                         Integer customerId,
                                         Double amount);

    Response<Page<PaymentDto>> getPAge(Integer page, Integer size);
}
