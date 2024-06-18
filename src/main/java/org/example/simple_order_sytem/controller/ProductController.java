package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.ProductService;
import org.example.simple_order_sytem.service.impl.ProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product/")
public class ProductController implements ProductService {
    public final ProductServiceImpl productServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<ProductDto> create(@RequestBody ProductDto productLineDto) {
        return this.productServiceImpl.create(productLineDto);
    }

    @Override
    @GetMapping("/get")
    public Response<ProductDto> get(@RequestParam("id") Integer id) {
        return this.productServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<ProductDto> update(@RequestBody ProductDto productLineDto, @RequestParam("id") Integer id) {
        return this.productServiceImpl.update(productLineDto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<ProductDto> delete(@RequestParam("id") Integer id) {
        return this.productServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<ProductDto>> getAll() {
        return this.productServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPage")
    public Response<Page<ProductDto>> getPAge(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.productServiceImpl.getPAge(page, size);
    }

    @Override
    @GetMapping("/getFilter")
    public Response<List<ProductDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                @RequestParam(value = "product line id", required = false) Integer productLineId,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "scale", required = false) Integer scale,
                                                @RequestParam(value = "vendor", required = false) String vendor,
                                                @RequestParam(value = "PDT description", required = false) String PDTDescription,
                                                @RequestParam(value = "QtylnStock", required = false) Integer QtylnStock,
                                                @RequestParam(value = "by price", required = false) Double byPrice,
                                                @RequestParam(value = "MSRP", required = false) String MSRP) {
        return this.productServiceImpl.getFilter(id, productLineId, name, scale, vendor, PDTDescription, QtylnStock, byPrice, MSRP);
    }

    @Override
    @GetMapping("/group_by_productLineId")
    public Response<Map<Integer, List<ProductDto>>> groupByProductLineId() {
        return this.productServiceImpl.groupByProductLineId();
    }

    @Override
    @GetMapping("/getWithOrderProduct")
    public Response<ProductDto> getWithOrderProduct(@RequestParam("id") Integer id) {
        return this.productServiceImpl.getWithOrderProduct(id);
    }
}
