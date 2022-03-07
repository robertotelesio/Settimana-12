package it.epicode.be.catalogolibri.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.service.AutoreService;


@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class AutoreController {

	@Autowired
    private AutoreService autoreService;

    @GetMapping(path = "/autore")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<Autore>> findAll() {
        List<Autore> findAll = autoreService.findAll();

        if (findAll != null) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "/autore/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Autore> findById(@PathVariable(required = true) Long id) {
        Optional<Autore> find = autoreService.findById(id);

        if (find.isPresent()) { 
            return new ResponseEntity<>(find.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(path = "/autore")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Autore> save(@Valid @RequestBody Autore autore, BindingResult result) {
        Autore save = autoreService.save(autore);
        return new ResponseEntity<>(save, HttpStatus.OK);

    }
@PutMapping(path = "/autore/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Autore> update(@PathVariable Long id,@Valid  @RequestBody Autore autore, BindingResult result) {
        Autore save = autoreService.update(id, autore);
        return new ResponseEntity<>(save, HttpStatus.OK);

    }

    @DeleteMapping(path = "/autore/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        autoreService.delete(id);
        return new ResponseEntity<>("Autore eliminato", HttpStatus.OK);

    }
}