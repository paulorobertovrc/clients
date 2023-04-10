package br.dev.pauloroberto.algafood.clientjava;

import br.dev.pauloroberto.algafood.clientjava.api.ClientApiException;
import br.dev.pauloroberto.algafood.clientjava.api.RestauranteClient;
import br.dev.pauloroberto.algafood.clientjava.model.RestauranteModel;
import br.dev.pauloroberto.algafood.clientjava.model.input.CidadeIdInput;
import br.dev.pauloroberto.algafood.clientjava.model.input.CozinhaIdInput;
import br.dev.pauloroberto.algafood.clientjava.model.input.EnderecoInput;
import br.dev.pauloroberto.algafood.clientjava.model.input.RestauranteInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        try {
            RestauranteClient restauranteClient = new RestauranteClient(
                    "http://localhost:8080",
                    new RestTemplate()
            );

            CozinhaIdInput cozinha = new CozinhaIdInput();
            cozinha.setId(1L);

            CidadeIdInput cidade = new CidadeIdInput();
            cidade.setId(1L);

            EnderecoInput endereco = new EnderecoInput();
            endereco.setCidade(cidade);
            endereco.setCep("79950-000");
            endereco.setLogradouro("Rua Xyz");
            endereco.setNumero("300");
            endereco.setBairro("Centro");

            RestauranteInput restaurante = new RestauranteInput();
            restaurante.setNome("Restaurante XYZ");
            restaurante.setTaxaFrete(new BigDecimal("9.5"));
            restaurante.setCozinha(new CozinhaIdInput());
            restaurante.setCozinha(cozinha);
            restaurante.setEndereco(endereco);

            RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);

            System.out.println(restauranteModel);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getDetail());

                e.getProblem().getFields().forEach(object -> {
                    System.out.println(">> " + object.getName() + ": " + object.getUserMessage());
                });

            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
