package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum TipoUsuario implements Serializable {
    MASTER("Master", new String[]{"consultaUsuarios.xhtml", "cadastroUsuarios.xhtml", "consultaFerramentas.xhtml", "consultaEmpresas.xhtml", "consultaClientes.xhtml"}),
    FUNCIONARIO("Funcionario", new String[]{}),
    CLIENTE("Cliente", new String[]{});

    private final String descricao;
    private final String[] paginas;

    private TipoUsuario(String descricao, String[] paginas) {
        this.descricao = descricao;
        this.paginas = paginas;
    }

    public String[] getPaginas() {
        return paginas;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoUsuario fromDescricao(String descricao) {
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.getDescricao().equals(descricao)) {
                return tipoUsuario;
            }
        }
        throw new UnsupportedOperationException("O tipo usuário " + descricao + " não é suportado");
    }
}
