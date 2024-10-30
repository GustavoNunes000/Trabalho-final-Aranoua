package service;

import dto.afiliacao.AfiliacaoCreateDTO;
import dto.afiliacao.AfiliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AfiliacaoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAfiliacao {
    @Autowired
    static AfiliacaoRepository afiliacaoRepository;

    public static List<AfiliacaoDTO> list(){
        List<AfiliacaoDTO> afiliacaoDTOS = new ArrayList<>();
        return afiliacaoDTOS;
    }
    public static AfiliacaoDTO read(Long referencia){
        ArrayList<AfiliacaoDTO> afiliacaoDTOsList = new ArrayList<>();

        return afiliacaoDTOsList.stream()
                .findFirst()
                .orElse(null);
    }

    public static AfiliacaoDTO create(AfiliacaoCreateDTO body) {
        AfiliacaoDTO afiliacao = body.build();

        AfiliacaoDTO savedAfiliacao = afiliacaoRepository.save(afiliacao);
        return new AfiliacaoDTO();
    }


}
