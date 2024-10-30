package dto.autores;

import lombok.Getter;
import lombok.Setter;
import model.Autores;

@Setter
@Getter
public class AutoresCreateDTO {
    private String nome;
    private String afiliacao;

    public Autores build(){
        Autores autores  = new Autores();
        autores.setNome(nome);
        autores.setAfiliação(afiliacao);
        return autores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }
}
