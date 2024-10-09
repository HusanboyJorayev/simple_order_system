package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.CustomerService;
import org.example.simple_order_sytem.service.impl.CustomerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer/")
@Slf4j
public class CustomerController implements CustomerService {
    public final CustomerServiceImpl customerServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<CustomerDto> create(@RequestBody CustomerDto dto) {
        return this.customerServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<CustomerDto> get(@RequestParam("id") Integer id) {
        return this.customerServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<CustomerDto> update(@RequestBody CustomerDto dto,
                                        @RequestParam("id") Integer id) {
        return this.customerServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<CustomerDto> delete(@RequestParam("id") Integer id) {
        return this.customerServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<CustomerDto>> getAll() {
        log.info("getAll : {}", log.getName());
        return this.customerServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPage")
    public Response<Page<CustomerDto>> getPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.customerServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/customer_filter")
    public Response<List<CustomerDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam(value = "employee_id", required = false) Integer employeeId,
                                                 @RequestParam(value = "firstname", required = false) String firstName,
                                                 @RequestParam(value = "lastname", required = false) String lastName,
                                                 @RequestParam(value = "email", required = false) String email,
                                                 @RequestParam(value = "phone", required = false) String phone,
                                                 @RequestParam(value = "address1", required = false) String address1,
                                                 @RequestParam(value = "address2", required = false) String address2,
                                                 @RequestParam(value = "city", required = false) String city,
                                                 @RequestParam(value = "state", required = false) String state,
                                                 @RequestParam(value = "zip_code", required = false) String zipCode,
                                                 @RequestParam(value = "country", required = false) String country,
                                                 @RequestParam(value = "credit_limit", required = false) Double creditLimit) {
        return this.customerServiceImpl.getFilter(id, employeeId, firstName, lastName, email, phone, address1, address2, city, state, zipCode, country, creditLimit);
    }

    @Override
    @GetMapping("/group_by_CustomerByEmployeeId")
    public Response<Map<Integer, List<CustomerDto>>> group_byCustomerByEmployeeId() {
        return this.customerServiceImpl.group_byCustomerByEmployeeId();
    }

    @GetMapping("/get_employee_all_credits")
    public Response<Double> getAllEmployeeCredit() {
        return this.customerServiceImpl.getAllEmployeeCredit();
    }

    @Override
    @GetMapping("/filter/list")
    public Response<List<CustomerDto>> getFilterByQuery(@RequestParam(value = "id", required = false) Integer id,
                                                        @RequestParam(value = "employee_id", required = false) Integer employeeId,
                                                        @RequestParam(value = "firstname", required = false) String firstName,
                                                        @RequestParam(value = "lastname", required = false) String lastName,
                                                        @RequestParam(value = "email", required = false) String email,
                                                        @RequestParam(value = "phone", required = false) String phone,
                                                        @RequestParam(value = "address1", required = false) String address1,
                                                        @RequestParam(value = "address2", required = false) String address2,
                                                        @RequestParam(value = "city", required = false) String city,
                                                        @RequestParam(value = "state", required = false) String state,
                                                        @RequestParam(value = "zip_code", required = false) String zipCode,
                                                        @RequestParam(value = "country", required = false) String country,
                                                        @RequestParam(value = "credit_limit", required = false) Double creditLimit,
                                                        @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        return this.customerServiceImpl.getFilterByQuery(id, employeeId, firstName,
                lastName, email, phone, address1, address2,
                city, state, zipCode, country, creditLimit, page, size);
    }
}
