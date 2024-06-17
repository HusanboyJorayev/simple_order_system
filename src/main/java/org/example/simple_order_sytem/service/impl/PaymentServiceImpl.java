package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.PaymentDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.Payment;
import org.example.simple_order_sytem.filter.PaymentFilter;
import org.example.simple_order_sytem.mapper.PaymentMapper;
import org.example.simple_order_sytem.repository.PaymentRepository;
import org.example.simple_order_sytem.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Response<PaymentDto> create(PaymentDto paymentDto) {
        Payment entity = this.paymentMapper.toEntity(paymentDto);
        entity.setCreatedAt(LocalDateTime.now());
        this.paymentRepository.save(entity);
        return Response.<PaymentDto>builder()
                .message("Ok")
                .status(HttpStatus.CREATED)
                .data(this.paymentMapper.toDto(entity))
                .build();
    }

    @Override
    public Response<PaymentDto> get(Integer id) {
        Optional<Payment> optional = this.paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = optional.get();
            return Response.<PaymentDto>builder()
                    .message("Ok")
                    .data(this.paymentMapper.toDto(payment))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<PaymentDto>builder()
                .message("Not Found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<PaymentDto> update(PaymentDto paymentDto, Integer id) {
        Optional<Payment> optional = this.paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = optional.get();
            payment.setUpdatedAt(LocalDateTime.now());
            this.paymentMapper.update(paymentDto, payment);
            this.paymentRepository.save(payment);
            return Response.<PaymentDto>builder()
                    .message("Ok")
                    .data(this.paymentMapper.toDto(payment))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<PaymentDto>builder()
                .message("Not Found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<PaymentDto> delete(Integer id) {
        Optional<Payment> optional = this.paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = optional.get();
            payment.setDeletedAt(LocalDateTime.now());
            this.paymentRepository.delete(payment);
            return Response.<PaymentDto>builder()
                    .message("Ok")
                    .data(this.paymentMapper.toDto(payment))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<PaymentDto>builder()
                .message("Not Found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<PaymentDto>> getAll() {
        List<Payment> paymentList = this.paymentRepository.findAll();
        if (!paymentList.isEmpty()) {
            return Response.<List<PaymentDto>>builder()
                    .message("Ok")
                    .status(HttpStatus.OK)
                    .data(paymentList.stream().map(this.paymentMapper::toDto).toList())
                    .build();
        }
        return Response.<List<PaymentDto>>builder()
                .message("not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<Page<PaymentDto>> getPAge(Integer page, Integer size) {
        Page<Payment> paymentPage = this.paymentRepository.findAll(PageRequest.of(page, size));
        if (!paymentPage.isEmpty()) {
            return Response.<Page<PaymentDto>>builder()
                    .message("Ok")
                    .status(HttpStatus.OK)
                    .data(paymentPage.map(this.paymentMapper::toDto))
                    .build();
        }
        return Response.<Page<PaymentDto>>builder()
                .message("not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<PaymentDto>> getFilter(Integer id, Integer customerId, Double amount) {
        Specification<Payment> specification = new PaymentFilter(id, customerId, amount);
        List<Payment> paymentList = this.paymentRepository.findAll(specification);
        if (!paymentList.isEmpty()) {
            return Response.<List<PaymentDto>>builder()
                    .message("Ok")
                    .status(HttpStatus.OK)
                    .data(paymentList.stream().map(this.paymentMapper::toDto).toList())
                    .build();
        }
        return Response.<List<PaymentDto>>builder()
                .message("not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
