package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OfficeDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.OfficeService;
import org.example.simple_order_sytem.service.impl.OfficeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/office/")
public class OfficeController implements OfficeService {
    private final OfficeServiceImpl officeServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<OfficeDto> create(@RequestBody OfficeDto dto) {
        return this.officeServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<OfficeDto> get(@RequestParam("id") Integer id) {
        return this.officeServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<OfficeDto> update(@RequestBody OfficeDto dto, @RequestParam("id") Integer id) {
        return this.officeServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<OfficeDto> delete(@RequestParam("id") Integer id) {
        return this.officeServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<OfficeDto>> getAll() {
        return this.officeServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPAge")
    public Response<Page<OfficeDto>> getPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.officeServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/getFilter")
    public Response<List<OfficeDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "address1", required = false) String address1,
                                               @RequestParam(value = "address2", required = false) String address2,
                                               @RequestParam(value = "state", required = false) String state,
                                               @RequestParam(value = "country", required = false) String country,
                                               @RequestParam(value = "postCode", required = false) Integer postCode,
                                               @RequestParam(value = "territory", required = false) String territory) {
        return this.officeServiceImpl.getFilter(id, city, phone, address1, address2, state, country, postCode, territory);
    }
}
