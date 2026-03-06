package com.Challenge.demo.Controller;

import com.Challenge.demo.domain.topico.DatosActualizarTopico;
import com.Challenge.demo.domain.topico.DatosListadoTopico;
import com.Challenge.demo.domain.topico.DatosRegistroTopico;
import com.Challenge.demo.domain.topico.Topico;
import com.Challenge.demo.domain.topico.TopicoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.status(409).body("Ya existe un tópico con el mismo título y mensaje.");
        }
        var topico = repository.save(new Topico(datos));
        return ResponseEntity.status(201).body(new DatosListadoTopico(topico));
    }
    @GetMapping
    public List<DatosListadoTopico> listar() {
        Pageable primerosDiez = PageRequest.of(0, 10, Sort.by("fechaCreacion").ascending());
        return repository.findAllByOrderByFechaCreacionAsc(primerosDiez)
                .stream()
                .map(DatosListadoTopico::new)
                .toList();
    }
    @GetMapping("/{id}")
    public DatosListadoTopico detallar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return new DatosListadoTopico(topico);
    }


    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarInformacion(datos);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        // Usamos findById para obtener el Optional que sugiere el Trello
        Optional<Topico> topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarInformacion(datos);
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}