package dto.afiliacao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AfiliacaoCreateDTO {
    private String nome;
    private String sigla;
    private String referencia;

    public AfiliacaoDTO build(){
        AfiliacaoDTO afiliacaoDTO = new AfiliacaoDTO();
        afiliacaoDTO.setNome(nome);
        afiliacaoDTO.setSigla(sigla);
        afiliacaoDTO.setReferencia(referencia);
        return afiliacaoDTO;
    }
}
