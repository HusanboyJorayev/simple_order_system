package org.example.simple_order_sytem.cache;


import lombok.SneakyThrows;
import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.entity.Customer;
import org.example.simple_order_sytem.mapper.CustomerMapper;
import org.example.simple_order_sytem.repository.CustomerRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/customers/cache/")
public class SimpleCache {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CacheManager cacheManager;
    private final Cache cache;



    public SimpleCache(CustomerRepository customerRepository, CustomerMapper customerMapper,
                       CacheManager cacheManager) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.cacheManager = cacheManager;
        this.cache = cacheManager.getCache("customers");
    }

    @SneakyThrows
    @Cacheable(value = "customers", key = "#id")
    @GetMapping("/get")
    public CustomerDto getCustomer(@RequestParam("id") Integer id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerDto dto = this.customerMapper.toDto(customer);
        TimeUnit.SECONDS.sleep(2);
        return dto;
    }

    @GetMapping("/get-all")
    @Cacheable(value = "customers", key = "#root.methodName")
    public List<CustomerDto> getAll() {
        return this.customerRepository.findAll()
                .stream().map(this.customerMapper::toDto)
                .toList();
    }
}
