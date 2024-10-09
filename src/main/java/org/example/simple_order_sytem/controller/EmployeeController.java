package org.example.simple_order_sytem.controller;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.EmployeeDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.service.EmployeeService;
import org.example.simple_order_sytem.service.impl.EmployeeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee/")
public class EmployeeController implements EmployeeService {
    private final EmployeeServiceImpl employeeServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        return this.employeeServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<EmployeeDto> get(@RequestParam("id") Integer id) {
        return this.employeeServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public Response<EmployeeDto> update(@RequestParam EmployeeDto dto, @RequestParam("id") Integer id) {
        return this.employeeServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<EmployeeDto> delete(@RequestParam("id") Integer id) {
        return this.employeeServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<EmployeeDto>> getAll() {
        return this.employeeServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getPage")
    public Response<Page<EmployeeDto>> getPage(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size) {
        return this.employeeServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/get_filter")
    public Response<List<EmployeeDto>> getFilter(@RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam(value = "office_id", required = false) Integer officeId,
                                                 @RequestParam(value = "report_to", required = false) Integer reportTo,
                                                 @RequestParam(value = "firstname", required = false) String lastname,
                                                 @RequestParam(value = "lastname", required = false) String firstname,
                                                 @RequestParam(value = "extension", required = false) String extension,
                                                 @RequestParam(value = "email", required = false) String email,
                                                 @RequestParam(value = "jobTitle", required = false) String jobTitle) {
        return this.employeeServiceImpl.getFilter(id, officeId, reportTo, lastname, firstname, extension, email, jobTitle);
    }

    @Override
    @GetMapping("/list/filter")
    public Response<List<EmployeeDto>> getFilterByQuery(@RequestParam(value = "id", required = false) Integer id,
                                                        @RequestParam(value = "office_id", required = false) Integer officeId,
                                                        @RequestParam(value = "report_to", required = false) Integer reportTo,
                                                        @RequestParam(value = "lastname", required = false) String lastname,
                                                        @RequestParam(value = "firstname", required = false) String firstname,
                                                        @RequestParam(value = "extension", required = false) String extension,
                                                        @RequestParam(value = "email", required = false) String email,
                                                        @RequestParam(value = "jobTitle", required = false) String jobTitle) {
        return this.employeeServiceImpl.getFilterByQuery(id, officeId, reportTo, lastname, firstname, extension, email, jobTitle);
    }
}
