package br.dev.pauloroberto.algafood.clientjava;

import br.dev.pauloroberto.algafood.clientjava.api.ClientApiException;
import br.dev.pauloroberto.algafood.clientjava.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {
    public static void main(String[] args) {
        try {
            RestauranteClient restauranteClient = new RestauranteClient(
                    "http://localhost:8080",
                    new RestTemplate()
            );

            restauranteClient.listar().forEach(System.out::println);
//            System.out.println(restauranteClient.buscar(1L));

        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getDetail());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }

}
