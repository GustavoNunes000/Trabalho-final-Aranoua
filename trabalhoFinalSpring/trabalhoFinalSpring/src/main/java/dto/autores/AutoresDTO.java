package dto.autores;

import lombok.Getter;
import lombok.Setter;
import model.Autores;

@Setter
@Getter
public class AutoresDTO extends Autores {
    private Long id;
    private String nome;
    private String afiliacao;

    public AutoresDTO(Autores autores){
        this.id = autores.getId();
        this.nome = autores.getNome();
        this.afiliacao = autores.getAfiliacao();

    }
}
