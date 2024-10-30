package controller;

import dto.afiliacao.AfiliacaoCreateDTO;
import dto.afiliacao.AfiliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.ServiceAfiliacao;

import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/api/afiliacao")
public class AfiliacaoController {
    @Autowired
    ServiceAfiliacao serviceAfiliacao;

    @GetMapping(value = "/{îd}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AfiliacaoDTO> read(@PathVariable long id){
        AfiliacaoDTO afiliacaoDTOS = ServiceAfiliacao.read(id);
        return ResponseEntity.ok().body(afiliacaoDTOS);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody AfiliacaoCreateDTO body) throws NoSuchMethodException {
        AfiliacaoDTO afiliacaoDTO = ServiceAfiliacao.create(body);

        if (afiliacaoDTO == null || afiliacaoDTO.getReferencia() == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = createUri(Long.valueOf(afiliacaoDTO.getReferencia()));
        EntityModel<AfiliacaoDTO> afiliacaoDTOEntityModel = createEntityModel(afiliacaoDTO, body);

        return ResponseEntity.created(uri).body(afiliacaoDTOEntityModel);
    }

    private URI createUri(Long referencia) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{referencia}")
                .buildAndExpand(referencia)
                .toUri();
    }

    private EntityModel<AfiliacaoDTO> createEntityModel(AfiliacaoDTO afiliacaoDTO, AfiliacaoCreateDTO body) throws NoSuchMethodException {
        return EntityModel.of(afiliacaoDTO)
                .add(createLink("list", AfiliacaoController.class, "list"),
                        createLink("read", AfiliacaoController.class, "read", afiliacaoDTO.getReferencia()),
                        createLink("update", AfiliacaoController.class, "update", afiliacaoDTO.getReferencia(), body),
                        createLink("delete", AfiliacaoController.class, "delete", afiliacaoDTO.getReferencia()));
    }

    private Link createLink(String rel, Class<?> controller, String methodName, Object... args) throws NoSuchMethodException {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(controller).getClass().getMethod(methodName, getParameterTypes(args))
        ).withRel(rel);
    }

    private Class<?>[] getParameterTypes(Object... args) {
        return Arrays.stream(args)
                .map(arg -> arg.getClass())
                .toArray(Class<?>[]::new);
    }

}
