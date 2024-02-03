package daniel.marina.proyectoparadweservidor.controllers;

import daniel.marina.proyectoparadweservidor.dto.department.DepartmentRequestDto;
import daniel.marina.proyectoparadweservidor.dto.department.DepartmentResponseDto;
import daniel.marina.proyectoparadweservidor.mappers.DepartmentMapper;
import daniel.marina.proyectoparadweservidor.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company/departments")
@Slf4j
public class DepartmentController {
    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @Autowired
    public DepartmentController(DepartmentService service, DepartmentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<DepartmentResponseDto> getByNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(mapper.toDto(service.findByNumber(number)));

    }@GetMapping("/name/{name}")
    public ResponseEntity<List<DepartmentResponseDto>> getAllByNameLike(@PathVariable String name) {
        return ResponseEntity.ok(mapper.toDto(service.findByNameLike(name)));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> post(@RequestBody DepartmentRequestDto requestDto) {
        return ResponseEntity.created(null)
                .body(mapper.toDto(
                        service.create(mapper.toModel(requestDto))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDto> put(@PathVariable UUID id, @RequestBody DepartmentRequestDto requestDto) {
        return ResponseEntity.ok(
                mapper.toDto(service.update(
                        id,
                        mapper.toModel(requestDto)
                ))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DepartmentResponseDto> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<DepartmentResponseDto> patch(@PathVariable UUID id, @RequestBody DepartmentRequestDto requestDto) {
        return ResponseEntity.ok(
                mapper.toDto(service.patch(
                        id,
                        mapper.toModel(requestDto)
                ))
        );
    }
}
