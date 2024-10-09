package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.EmployeeDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {
    Response<EmployeeDto> create(EmployeeDto dto);

    Response<EmployeeDto> get(Integer id);

    Response<EmployeeDto> update(EmployeeDto dto, Integer id);

    Response<EmployeeDto> delete(Integer id);

    Response<List<EmployeeDto>> getAll();

    Response<Page<EmployeeDto>> getPage(Integer page, Integer size);

    Response<List<EmployeeDto>> getFilter(
            Integer id, Integer officeId,
            Integer reportTo,
            String lastname,
            String firstname,
            String extension,
            String email, String jobTitle);

    Response<List<EmployeeDto>> getFilterByQuery(
            Integer id, Integer officeId,
            Integer reportTo,
            String lastname,
            String firstname,
            String extension,
            String email, String jobTitle);
}
