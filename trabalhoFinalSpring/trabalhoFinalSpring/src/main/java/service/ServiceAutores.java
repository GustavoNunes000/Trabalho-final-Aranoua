package service;

import dto.autores.AutoresCreateDTO;
import dto.autores.AutoresDTO;
import jakarta.persistence.EntityNotFoundException;
import model.Autores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AutoresRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAutores {
    @Autowired
    static AutoresRepository autoresRepository;

    public static List<Autores> list() {
        List<Autores> autores = new ArrayList<Autores>();
        return autores;
    }

    public AutoresDTO readAutores(Long id) {
        return autoresRepository
                .findById(id)
                .map(AutoresDTO::new)
                .orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado ID: " +  id));
    }

    public static AutoresDTO create(AutoresCreateDTO body) {
        return new AutoresDTO(autoresRepository.save(body.build()));
    }

    public static AutoresDTO update(Long id, AutoresCreateDTO body) {
        AutoresDTO autoresDTO = (AutoresDTO) autoresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado ID: " + id));

        try {
            autoresDTO.setNome(body.getNome());
            autoresDTO.setAfiliação(body.getAfiliacao());

            return new AutoresDTO(autoresRepository.save(autoresDTO));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public class ObjectNotFoundException extends RuntimeException {
        public ObjectNotFoundException(String message) {
            super(message);
        }
    }

}
