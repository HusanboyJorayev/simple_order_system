package org.example.simple_order_sytem.service.impl;

import org.example.simple_order_sytem.filter.CustomerFilter;
import org.example.simple_order_sytem.repository.CustomerRepository;
import org.example.simple_order_sytem.service.CustomerService;
import org.example.simple_order_sytem.mapper.CustomerMapper;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.entity.Customer;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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

    @Override
    public Response<List<CustomerDto>> getFilter(Integer id, Integer employeeId, String firstName,
                                                 String lastName, String email, String phone, String address1,
                                                 String address2, String city, String state, String zipCode,
                                                 String country, Double creditLimit) {
        Specification<Customer> specification = new CustomerFilter(id, employeeId, firstName, lastName, email, phone,
                address1, address2, city, state, zipCode, country, creditLimit);

        List<CustomerDto> dtoList = this.customerRepository.findAll(specification)
                .stream().map(this.customerMapper::toDto).toList();
        return Response.<List<CustomerDto>>builder()
                .message("Customer found")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }

    @Override
    public Response<Map<Integer, List<CustomerDto>>> group_byCustomerByEmployeeId() {
        Map<Integer, List<CustomerDto>> collect = this.customerRepository.findAll()
                .stream().map(this.customerMapper::toDto)
                .collect(Collectors.groupingBy(CustomerDto::getEmployeeId));

        return Response.<Map<Integer, List<CustomerDto>>>builder()
                .message("Ok")
                .status(HttpStatus.OK)
                .data(collect)
                .build();
    }

    public Response<Double> getAllEmployeeCredit() {
        Double sum = this.customerRepository.findAll()
                .stream()
                .map(this.customerMapper::toDto)
                .filter(dto -> dto.getEmployeeId() != null)
                .mapToDouble(CustomerDto::getCreditLimit).sum();

        return Response.<Double>builder()
                .message("Ok")
                .status(HttpStatus.OK)
                .data(sum)
                .build();
    }
}
