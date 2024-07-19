package org.example.simple_order_sytem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OrderDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.OrderService;
import org.example.simple_order_sytem.service.impl.OrderServiceImpl;
import org.example.simple_order_sytem.status.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order/")
public class OrderController implements OrderService {
    public final OrderServiceImpl orderServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<OrderDto> create(@Valid @RequestBody OrderDto dto) {
        return this.orderServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<OrderDto> get(@RequestParam("id") Integer id) {
        return this.orderServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<OrderDto> update(@RequestBody OrderDto dto,
                                     @RequestParam("id") Integer id) {
        return this.orderServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<OrderDto> delete(@RequestParam("id") Integer id) {
        return this.orderServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<OrderDto>> getAll() {
        return this.orderServiceImpl.getAll();
    }

    @Override
    @GetMapping("/get_page")
    public Response<Page<OrderDto>> getPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.orderServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/get_filter")
    public Response<List<OrderDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                              @RequestParam(value = "customer_id", required = false) Integer customerId,
                                              @RequestParam(value = "order_id", required = false) Integer orderProductId,
                                              @RequestParam(value = "orderDAte", required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate,
                                              @RequestParam(value = "requiredDate", required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requiredDate,
                                              @RequestParam(value = "shippedDate", required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate shippedDate,
                                              @RequestParam(value = "status", required = false) OrderStatus status,
                                              @RequestParam(value = "comments", required = false) String comments) {
        return this.orderServiceImpl.getFilter(id, customerId, orderProductId, orderDate, requiredDate, shippedDate, status, comments);
    }

    @Override
    @GetMapping("/group_byOrderByStatus")
    public Response<Map<OrderStatus, List<OrderDto>>> group_byOrderByStatus() {
        return this.orderServiceImpl.group_byOrderByStatus();
    }

    @Override
    @GetMapping("/getWithOrderProduct")
    public Response<OrderDto> getWithOrderProduct(@RequestParam("id") Integer id) {
        return this.orderServiceImpl.getWithOrderProduct(id);
    }
}
