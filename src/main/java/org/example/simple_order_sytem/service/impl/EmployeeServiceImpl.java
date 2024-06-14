package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.EmployeeDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.Employee;
import org.example.simple_order_sytem.filter.EmployeeFilter;
import org.example.simple_order_sytem.mapper.EmployeeMapper;
import org.example.simple_order_sytem.repository.EmployeeRepository;
import org.example.simple_order_sytem.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Response<EmployeeDto> create(EmployeeDto dto) {
        Employee entity = this.employeeMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        this.employeeRepository.save(entity);
        return Response.<EmployeeDto>builder()
                .message("Ok")
                .status(HttpStatus.CREATED)
                .data(this.employeeMapper.toDto(entity))
                .build();
    }

    @Override
    public Response<EmployeeDto> get(Integer id) {
        return this.employeeRepository.findById(id)
                .map(employee -> Response.<EmployeeDto>builder()
                        .message("ok")
                        .status(HttpStatus.OK)
                        .data(this.employeeMapper.toDto(employee))
                        .build())
                .orElse(Response.<EmployeeDto>builder()
                        .message("Employee is not found")
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @Override
    public Response<EmployeeDto> update(EmployeeDto dto, Integer id) {
        return this.employeeRepository.findById(id)
                .map(employee -> {
                    employee.setUpdatedAt(LocalDateTime.now());
                    this.employeeMapper.update(employee, dto);
                    this.employeeRepository.save(employee);
                    return Response.<EmployeeDto>builder()
                            .message("ok")
                            .status(HttpStatus.OK)
                            .data(this.employeeMapper.toDto(employee))
                            .build();
                })
                .orElse(Response.<EmployeeDto>builder()
                        .message("Employee is not found")
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @Override
    public Response<EmployeeDto> delete(Integer id) {
        return this.employeeRepository.findById(id)
                .map(employee -> {
                    employee.setUpdatedAt(LocalDateTime.now());
                    this.employeeRepository.delete(employee);
                    return Response.<EmployeeDto>builder()
                            .message("ok")
                            .status(HttpStatus.OK)
                            .data(this.employeeMapper.toDto(employee))
                            .build();
                })
                .orElse(Response.<EmployeeDto>builder()
                        .message("Employee is not found")
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @Override
    public Response<List<EmployeeDto>> getAll() {
        List<Employee> employeeList = this.employeeRepository.findAll();
        List<EmployeeDto> dtoList = employeeList.stream().map(this.employeeMapper::toDto).toList();
        return Response.<List<EmployeeDto>>builder()
                .message("Ok")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }

    @Override
    public Response<Page<EmployeeDto>> getPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EmployeeDto> map = this.employeeRepository.findAll(pageRequest)
                .map(this.employeeMapper::toDto);
        return Response.<Page<EmployeeDto>>builder()
                .message("OK")
                .status(HttpStatus.OK)
                .data(map)
                .build();
    }

    @Override
    public Response<List<EmployeeDto>> getFilter(Integer id, Integer officeId, Integer reportTo,
                                                 String lastname, String firstname, String extension,
                                                 String email, String jobTitle) {
        Specification<Employee> specification = new EmployeeFilter(id, officeId, reportTo, lastname, firstname, extension, email, jobTitle);
        List<EmployeeDto> dtoList = this.employeeRepository.findAll(specification)
                .stream().map(this.employeeMapper::toDto).toList();
        return Response.<List<EmployeeDto>>builder()
                .message("Ok")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }
}
