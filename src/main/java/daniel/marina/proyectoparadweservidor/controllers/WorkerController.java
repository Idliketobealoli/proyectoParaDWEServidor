package daniel.marina.proyectoparadweservidor.controllers;


import daniel.marina.proyectoparadweservidor.dto.worker.WorkerRequestDto;
import daniel.marina.proyectoparadweservidor.dto.worker.WorkerResponseDto;
import daniel.marina.proyectoparadweservidor.mappers.WorkerMapper;
import daniel.marina.proyectoparadweservidor.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company/workers")
@Slf4j
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService service;
    private final WorkerMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<WorkerResponseDto>> getAll() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<WorkerResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<WorkerResponseDto>> getAllByDepartment(@PathVariable UUID departmentId) {
        return ResponseEntity.ok(mapper.toDto(service.findByDepartment(departmentId)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<WorkerResponseDto>> getAllByNameLike(@PathVariable String name) {
        return ResponseEntity.ok(mapper.toDto(service.findByNameLike(name)));
    }

    @PostMapping("/create")
    public ResponseEntity<WorkerResponseDto> post(@RequestBody WorkerRequestDto requestDto) {
        return ResponseEntity.created(null)
                .body(mapper.toDto(
                        service.create(mapper.toModel(requestDto))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WorkerResponseDto> put(@PathVariable UUID id, @RequestBody WorkerRequestDto requestDto) {
        return ResponseEntity.ok(
                mapper.toDto(service.update(
                        id,
                        mapper.toModel(requestDto)
                ))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<WorkerResponseDto> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<WorkerResponseDto> patch(@PathVariable UUID id, @RequestBody WorkerRequestDto requestDto) {
        return ResponseEntity.ok(
                mapper.toDto(service.patch(
                        id,
                        mapper.toModel(requestDto)
                ))
        );
    }
}