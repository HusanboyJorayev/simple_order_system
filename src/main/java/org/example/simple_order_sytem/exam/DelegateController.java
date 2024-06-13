package org.example.simple_order_sytem.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class DelegateController {
    private final DelegateService delegateService;

    @GetMapping("/getText")
    public String getText(@RequestParam String text) {
        return delegateService.gettext(text);
    }

    @GetMapping("/getSumma")
    public Integer sum(@RequestParam Integer a, @RequestParam Integer b) {
        return delegateService.summa(a, b);
    }
}
