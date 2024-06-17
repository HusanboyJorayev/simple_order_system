package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.PaymentDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.PaymentService;
import org.example.simple_order_sytem.service.impl.PaymentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment/")
public class PaymentController implements PaymentService {
    public final PaymentServiceImpl paymentServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<PaymentDto> create(@RequestBody PaymentDto paymentDto) {
        return this.paymentServiceImpl.create(paymentDto);
    }

    @Override
    @GetMapping("/get")
    public Response<PaymentDto> get(@RequestParam("id") Integer id) {
        return this.paymentServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<PaymentDto> update(@RequestBody PaymentDto paymentDto, @RequestParam("id") Integer id) {
        return this.paymentServiceImpl.update(paymentDto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<PaymentDto> delete(@RequestParam("id") Integer id) {
        return this.paymentServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<PaymentDto>> getAll() {
        return this.paymentServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPage")
    public Response<Page<PaymentDto>> getPAge(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return this.paymentServiceImpl.getPAge(page, size);
    }

    @Override
    @GetMapping("/getFilter")
    public Response<List<PaymentDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                @RequestParam(value = "customer id", required = false) Integer customerId,
                                                @RequestParam(value = "amount", required = false) Double amount) {
        return this.paymentServiceImpl.getFilter(id, customerId, amount);
    }
}
