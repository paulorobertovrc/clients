package br.dev.pauloroberto.algafood.clientjava.api;

import br.dev.pauloroberto.algafood.clientjava.model.RestauranteModel;
import br.dev.pauloroberto.algafood.clientjava.model.RestauranteResumoModel;
import br.dev.pauloroberto.algafood.clientjava.model.input.RestauranteInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class RestauranteClient {
    private static final String RESOURCE_PATH = "/restaurantes";

    private String url;
    private RestTemplate restTemplate;

    public List<RestauranteResumoModel> listar() {
        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            return List.of(Objects.requireNonNull(
                    restTemplate.getForObject(resourceUri, RestauranteResumoModel[].class)
            ));
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

    public RestauranteResumoModel buscar(Long id) {
        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH + "/" + id);

            return Objects.requireNonNull(
                    restTemplate.getForObject(resourceUri, RestauranteResumoModel.class)
            );
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

    public RestauranteModel adicionar(RestauranteInput restaurante) {
        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            return restTemplate.postForObject(resourceUri, restaurante, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

}
