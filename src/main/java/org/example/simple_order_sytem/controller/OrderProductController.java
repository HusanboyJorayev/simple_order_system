package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OrderProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.OrderProductService;
import org.example.simple_order_sytem.service.impl.OrderProductServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order_product/")
public class OrderProductController implements OrderProductService {
    private final OrderProductServiceImp orderProductServiceImp;

    @Override
    @PostMapping("/create")
    public Response<OrderProductDto> create(@RequestBody OrderProductDto dto) {
        return this.orderProductServiceImp.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<OrderProductDto> get(@RequestParam("id") Integer id) {
        return this.orderProductServiceImp.get(id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<OrderProductDto> delete(@RequestParam("id") Integer id) {
        return this.orderProductServiceImp.delete(id);
    }

    @Override
    @PutMapping("/update")
    public Response<OrderProductDto> update(@RequestBody OrderProductDto dto,
                                            @RequestParam("id") Integer id) {
        return this.orderProductServiceImp.update(dto, id);
    }

    @Override
    @GetMapping("/get_page")
    public Response<Page<OrderProductDto>> getPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.orderProductServiceImp.getPage(page, size);
    }

    @Override
    @GetMapping("/get_all")
    public Response<List<OrderProductDto>> getAll() {
        return this.orderProductServiceImp.getAll();
    }

    @Override
    @GetMapping("/get_filter")
    public Response<List<OrderProductDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                     @RequestParam(value = "orderId", required = false) Integer orderId,
                                                     @RequestParam(value = "productId", required = false) Integer productId,
                                                     @RequestParam(value = "quantity", required = false) Integer quantity,
                                                     @RequestParam(value = "price", required = false) Double price) {
        return this.orderProductServiceImp.getFilter(id, orderId, productId, quantity, price);
    }

}
