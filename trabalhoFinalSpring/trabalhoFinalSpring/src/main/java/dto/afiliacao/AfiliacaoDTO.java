package dto.afiliacao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AfiliacaoDTO {
    private String nome;
    private String sigla;
    private String referencia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public AfiliacaoDTO() {
        this.nome = getNome();
        this.sigla = getSigla();
        this.referencia = getReferencia();
    }
}
