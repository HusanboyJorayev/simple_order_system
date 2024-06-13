package org.example.simple_order_sytem.exam;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DelegateService {
    @Delegate
    private final UserService userService;
}
