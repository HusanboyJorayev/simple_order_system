package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.ProductLineDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.ProductLineService;
import org.example.simple_order_sytem.service.impl.ProductLineServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/productLine/")
public class ProductLineController implements ProductLineService {
    public final ProductLineServiceImpl productLineServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<ProductLineDto> create(@RequestBody ProductLineDto productLineDto) {
        return this.productLineServiceImpl.create(productLineDto);
    }

    @Override
    @GetMapping("/get")
    public Response<ProductLineDto> get(@RequestParam(value = "id") Integer id) {
        return this.productLineServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<ProductLineDto> update(@RequestBody ProductLineDto productLineDto,
                                           @RequestParam(value = "id") Integer id) {
        return this.productLineServiceImpl.update(productLineDto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<ProductLineDto> delete(@RequestParam(value = "id") Integer id) {
        return this.productLineServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<ProductLineDto>> getAll() {
        return this.productLineServiceImpl.getAll();
    }

    @Override
    @GetMapping("/get_page")
    public Response<Page<ProductLineDto>> getPAge(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return this.productLineServiceImpl.getPAge(page, size);
    }

    @Override
    @GetMapping("/get_filter")
    public Response<List<ProductLineDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                    @RequestParam(value = "description", required = false) String description,
                                                    @RequestParam(value = "image", required = false) String image) {
        return this.productLineServiceImpl.getFilter(id, description, image);
    }
}
