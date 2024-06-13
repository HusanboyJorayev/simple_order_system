package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.CustomerService;
import org.example.simple_order_sytem.service.impl.CustomerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer/")
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
        return this.customerServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPage")
    public Response<Page<CustomerDto>> getPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.customerServiceImpl.getPage(page, size);
    }
}
