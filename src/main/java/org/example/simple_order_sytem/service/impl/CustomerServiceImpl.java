package org.example.simple_order_sytem.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.example.simple_order_sytem.repository.CustomerRepository;
import org.example.simple_order_sytem.service.CustomerService;
import org.example.simple_order_sytem.mapper.CustomerMapper;
import org.example.simple_order_sytem.filter.CustomerFilter;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.entity.Customer;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

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

    @Override
    public Response<List<CustomerDto>> getFilterByQuery(Integer id, Integer employeeId, String firstName, String lastName,
                                                        String email, String phone, String address1, String address2, String city,
                                                        String state, String zipCode, String country, Double creditLimit,
                                                        Integer page, Integer size) {

        String sql = "SELECT id AS id, " +
                "employee_id AS employeeId, " +
                "first_name AS firstName, " +
                "last_name AS lastName, " +
                "email AS email, " +
                "phone AS phone, " +
                "address1 AS address1, " +
                "address2 AS address2, " +
                "city AS city, " +
                "state AS state, " +
                "zip_code AS zipCode, " +
                "country AS country, " +
                "credit_limit AS creditLimit " +
                "FROM customer WHERE id > 0 ";

        MapSqlParameterSource params = new MapSqlParameterSource();

        if (id != null) {
            sql += " AND id = :id";
            params.addValue("id", id);
        }
        if (employeeId != null) {
            sql += " AND employee_id=:employeeId";
            params.addValue("employeeId", employeeId);
        }
        if (firstName != null) {
            sql += " AND lower(first_name) LIKE :firstName";
            params.addValue("firstName", "%" + firstName.toLowerCase() + "%");
        }
        if (lastName != null) {
            sql += " AND lower(last_name) LIKE :lastName";
            params.addValue("lastName", "%" + lastName.toLowerCase() + "%");
        }
        if (email != null) {
            sql += " AND lower(email) LIKE :email";
            params.addValue("email", "%" + email.toLowerCase() + "%");
        }
        if (phone != null) {
            sql += " AND phone LIKE :phone";
            params.addValue("phone", "%" + phone + "%");
        }
        if (address1 != null) {
            sql += " AND lower(address1) LIKE :address1";
            params.addValue("address1", "%" + address1.toLowerCase() + "%");
        }
        if (address2 != null) {
            sql += " AND lower(address2) LIKE :address2";
            params.addValue("address2", "%" + address2.toLowerCase() + "%");
        }
        if (city != null) {
            sql += " AND lower(city) LIKE :city";
            params.addValue("city", "%" + city.toLowerCase() + "%");
        }
        if (state != null) {
            sql += " AND lower(state) LIKE :state";
            params.addValue("state", "%" + state.toLowerCase() + "%");
        }
        if (zipCode != null) {
            sql += " AND lower(zip_code) LIKE :zipCode";
            params.addValue("zipCode", "%" + zipCode.toLowerCase() + "%");
        }
        if (country != null) {
            sql += " AND lower(country) LIKE :country";
            params.addValue("country", "%" + country.toLowerCase() + "%");
        }
        if (creditLimit != null) {
            sql += " AND credit_limit = :creditLimit";
            params.addValue("creditLimit", creditLimit);
        }
        sql += " LIMIT :limit OFFSET :offset";
        params.addValue("limit", size);
        params.addValue("offset", page * size);

        List<CustomerDto> list = jdbcTemplate.query(sql, params, (rs, rowNum) -> new CustomerDto(
                rs.getInt("id"),
                rs.getInt("employeeId"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("zipCode"),
                rs.getString("country"),
                rs.getDouble("creditLimit")
        ));

        return Response.<List<CustomerDto>>builder()
                .message("OK")
                .code(200)
                .data(list)
                .build();
    }
}
