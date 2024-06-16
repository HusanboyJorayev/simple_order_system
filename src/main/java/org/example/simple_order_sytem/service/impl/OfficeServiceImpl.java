package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OfficeDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.Office;
import org.example.simple_order_sytem.filter.OfficeFilter;
import org.example.simple_order_sytem.mapper.OfficeMapper;
import org.example.simple_order_sytem.repository.OfficeRepository;
import org.example.simple_order_sytem.service.OfficeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;
    private final OfficeMapper officeMapper;

    @Override
    public Response<OfficeDto> create(OfficeDto dto) {
        Office office = this.officeMapper.toOffice(dto);
        office.setCreatedAt(LocalDateTime.now());
        this.officeRepository.save(office);
        return Response.<OfficeDto>builder()
                .message("Successfully created office")
                .status(HttpStatus.CREATED)
                .data(this.officeMapper.toDto(office))
                .build();
    }

    @Override
    public Response<OfficeDto> get(Integer id) {
        Optional<Office> optional = this.officeRepository.findById(id);
        if (optional.isPresent()) {
            Office office = optional.get();
            return Response.<OfficeDto>builder()
                    .message("Successfully retrieved office")
                    .data(this.officeMapper.toDto(office))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<OfficeDto>builder()
                .message("Office not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<OfficeDto> update(OfficeDto dto, Integer id) {
        Optional<Office> optional = this.officeRepository.findById(id);
        if (optional.isPresent()) {
            Office office = optional.get();
            office.setUpdatedAt(LocalDateTime.now());
            this.officeMapper.update(dto, office);
            this.officeRepository.save(office);
            return Response.<OfficeDto>builder()
                    .message("Successfully updated office")
                    .status(HttpStatus.OK)
                    .data(this.officeMapper.toDto(office))
                    .build();
        }
        return Response.<OfficeDto>builder()
                .message("Office not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<OfficeDto> delete(Integer id) {
        Optional<Office> optional = this.officeRepository.findById(id);
        if (optional.isPresent()) {
            Office office = optional.get();
            office.setDeletedAt(LocalDateTime.now());
            this.officeRepository.delete(office);
            return Response.<OfficeDto>builder()
                    .message("Successfully deleted office")
                    .status(HttpStatus.OK)
                    .data(this.officeMapper.toDto(office))
                    .build();
        }
        return Response.<OfficeDto>builder()
                .message("Office not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<OfficeDto>> getAll() {
        List<Office> officeList = this.officeRepository.findAll();
        if (!officeList.isEmpty()) {
            return Response.<List<OfficeDto>>builder()
                    .message("Successfully retrieved office")
                    .status(HttpStatus.OK)
                    .data(officeList.stream().map(this.officeMapper::toDto).toList())
                    .build();
        }
        return Response.<List<OfficeDto>>builder()
                .message("Office not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<Page<OfficeDto>> getPage(Integer page, Integer size) {
        Page<Office> officePage = this.officeRepository.findAll(PageRequest.of(page, size));
        if (!officePage.isEmpty()) {
            return Response.<Page<OfficeDto>>builder()
                    .message("Successfully retrieved office")
                    .status(HttpStatus.OK)
                    .data(officePage.map(this.officeMapper::toDto))
                    .build();
        }
        return Response.<Page<OfficeDto>>builder()
                .message("Office not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<OfficeDto>> getFilter(Integer id,
                                               String city,
                                               String phone,
                                               String address1,
                                               String address2,
                                               String state,
                                               String country,
                                               Integer postCode,
                                               String territory) {
        Specification<Office> specification = new OfficeFilter(id, city, phone, address1, address2, state, country, postCode, territory);
        List<OfficeDto> dtoList = this.officeRepository.findAll(specification)
                .stream().map(this.officeMapper::toDto).toList();
        return Response.<List<OfficeDto>>builder()
                .message("Successfully retrieved office")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }
}
