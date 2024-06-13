package org.example.simple_order_sytem.service.impl;

import org.example.simple_order_sytem.repository.CustomerRepository;
import org.example.simple_order_sytem.service.CustomerService;
import org.example.simple_order_sytem.mapper.CustomerMapper;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.entity.Customer;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Response<CustomerDto> create(CustomerDto dto) {
        Customer customer = this.customerMapper.toEntity(dto);
        customer.setCreatedAt(LocalDateTime.now());
        this.customerRepository.save(customer);
        return Response.<CustomerDto>builder()
                .message("Customer created successfully")
                .status(HttpStatus.CREATED)
                .data(customerMapper.toDto(customer))
                .build();
    }

    @Override
    public Response<CustomerDto> get(Integer id) {
        Optional<Customer> optional = this.customerRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setCreatedAt(LocalDateTime.now());
            return Response.<CustomerDto>builder()
                    .message("Customer found")
                    .data(customerMapper.toDto(optional.get()))
                    .status(HttpStatus.FOUND)
                    .build();
        }
        return Response.<CustomerDto>builder()
                .message("Customer not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<CustomerDto> update(CustomerDto dto, Integer id) {
        Optional<Customer> optional = this.customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setUpdatedAt(LocalDateTime.now());
            this.customerMapper.update(dto, customer);
            this.customerRepository.save(customer);
            return Response.<CustomerDto>builder()
                    .message("Customer updated successfully")
                    .data(customerMapper.toDto(customer))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<CustomerDto>builder()
                .message("Customer not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<CustomerDto> delete(Integer id) {
        Optional<Customer> optional = this.customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setDeletedAt(LocalDateTime.now());
            this.customerRepository.delete(customer);
            return Response.<CustomerDto>builder()
                    .message("Customer deleted successfully")
                    .data(customerMapper.toDto(customer))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<CustomerDto>builder()
                .message("Customer not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<CustomerDto>> getAll() {
        List<Customer> customerList = this.customerRepository.findAll();
        if (!customerList.isEmpty()) {
            return Response.<List<CustomerDto>>builder()
                    .message("Customer found")
                    .status(HttpStatus.OK)
                    .data(customerList.stream().map(this.customerMapper::toDto).toList())
                    .build();
        }
        return Response.<List<CustomerDto>>builder()
                .message("Customer not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<Page<CustomerDto>> getPage(Integer page, Integer size) {
        Page<Customer> all = this.customerRepository.findAll(PageRequest.of(page, size));
        return Response.<Page<CustomerDto>>builder()
                .message("Customer found")
                .status(HttpStatus.OK)
                .data(all.map(this.customerMapper::toDto))
                .build();
    }
}
