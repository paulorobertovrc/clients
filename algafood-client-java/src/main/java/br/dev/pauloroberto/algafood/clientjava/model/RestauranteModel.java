package br.dev.pauloroberto.algafood.clientjava.model;

import lombok.Data;

@Data
public class RestauranteModel {
    private Long id;
    private String nome;
    private String taxaFrete;
    private Boolean ativo;
    private Boolean aberto;
    private CozinhaModel cozinha;
    private EnderecoModel endereco;
}
