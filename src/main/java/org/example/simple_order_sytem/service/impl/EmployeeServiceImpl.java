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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

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

    @Override
    public Response<List<EmployeeDto>> getFilterByQuery(Integer id,
                                                        Integer officeId,
                                                        Integer reportTo,
                                                        String lastname,
                                                        String firstname,
                                                        String extension,
                                                        String email,
                                                        String jobTitle) {

        String sql = "select id        as id,\n" +
                "       office_id as officeId,\n" +
                "       report_to as reportTo,\n" +
                "       lastname  as lastname,\n" +
                "       firstname as firstname,\n" +
                "       extension as extension,\n" +
                "       email     as email,\n" +
                "       job_title as jobTitle\n" +
                "from employee where id>0 ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (id != null) {
            sql += " and id=:id";
            params.addValue("id", id);
        }
        if (officeId != null) {
            sql += " and office_id=:officeId";
            params.addValue("officeId", officeId);
        }
        if (reportTo != null) {
            sql += " and report_to=:reportTo";
            params.addValue("reportTo", reportTo);
        }
        if (lastname != null) {
            sql += " and lower(lastname) like :lastname";
            params.addValue("lastname", "%" + lastname.toLowerCase() + "%");
        }
        if (firstname != null) {
            sql += " and lower(firstname) like :firstname";
            params.addValue("firstname", "%" + firstname.toLowerCase() + "%");
        }
        if (extension != null) {
            sql += " and lower(extension) like :extension";
            params.addValue("extension", "%" + extension.toLowerCase() + "%");
        }
        if (email != null) {
            sql += " and lower(email) like :email";
            params.addValue("email", "%" + email.toLowerCase() + "%");
        }
        if (jobTitle != null) {
            sql += " and lower(job_title) like :jobTitle";
            params.addValue("jobTitle", "%" + jobTitle.toLowerCase() + "%");
        }

        List<EmployeeDto> list = jdbcTemplate.query(sql, params, (rs, rowNumber) -> new EmployeeDto(
                rs.getInt("id"),
                rs.getInt("officeId"),
                rs.getInt("reportTo"),
                rs.getString("lastname"),
                rs.getString("firstname"),
                rs.getString("extension"),
                rs.getString("email"),
                rs.getString("jobTitle")
        ));
        return Response.<List<EmployeeDto>>builder()
                .message("Ok")
                .code(200)
                .data(list)
                .build();
    }
}
