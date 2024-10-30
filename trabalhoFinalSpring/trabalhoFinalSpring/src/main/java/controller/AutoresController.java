package controller;

import dto.autores.AutoresCreateDTO;
import dto.autores.AutoresDTO;
import model.Autores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.ServiceAutores;

import java.util.Arrays;
import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/api/autores")
public class AutoresController {
    @Autowired
    ServiceAutores serviceAutores;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Autores>> list() {
        List<Autores> autores = ServiceAutores.list();
        return ResponseEntity.ok().body(autores);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody AutoresCreateDTO body) throws NoSuchMethodException {
        AutoresDTO autores = ServiceAutores.create(body);

        if (autores == null || autores.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = createUri(autores.getId());
        EntityModel<AutoresDTO> autoresDTOEntityModel = createEntityModel(autores);

        return ResponseEntity.created(uri).body(autoresDTOEntityModel);
    }

    private URI createUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    private EntityModel<AutoresDTO> createEntityModel(AutoresDTO autores) throws NoSuchMethodException {
        return EntityModel.of(autores)
                .add(createLink("list", AutoresController.class, "list"),
                        createLink("read", AutoresController.class, "read", autores.getId()),
                        createLink("update", AutoresController.class, "update", autores.getId(), list().getBody()),
                        createLink("delete", AutoresController.class, "delete", autores.getId()));
    }

    private Link createLink(String rel, Class<?> controller, String methodName, Object... args) throws NoSuchMethodException {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(controller).getClass().getMethod(methodName, getParameterTypes(args))
        ).withRel(rel);
    }

    private Class<?>[] getParameterTypes(Object... args) {
        return Arrays.stream(args)
                .map(arg -> arg != null ? arg.getClass() : Object.class)
                .toArray(Class<?>[]::new);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoresDTO> update(@PathVariable long id, @RequestBody AutoresCreateDTO body){
        return ResponseEntity.ok().body(ServiceAutores.update(id, body));
    }

}
