package br.com.ifba.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
    }

    /**
     * Mapeia um objeto de origem (Input) para um objeto de destino (Output).
     * @param <Input> Tipo do objeto de origem.
     * @param <Output> Tipo da classe de destino.
     * @param object O objeto a ser mapeado.
     * @param clazz A classe para a qual o objeto será mapeado.
     * @return Uma nova instância da classe de destino com os dados do objeto de origem.
     */
    public <Input, Output> Output map(final Input object, final Class<Output> clazz) {
        /*
         * Configura a estratégia de mapeamento para ser mais estrita,
         * garantindo que apenas campos com nomes idênticos sejam mapeados.
         */
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true) // Ignora avisos de ambiguidade.
                .setMatchingStrategy(MatchingStrategies.STRICT) // Estratégia de correspondência estrita.
                .setFieldMatchingEnabled(true) // Permite o mapeamento por campos (não apenas por métodos get/set).
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); // Permite o acesso a campos privados.

        return MODEL_MAPPER.map(object, clazz);
    }

    /**
     * Mapeia uma lista de objetos de origem para uma lista de objetos de destino.
     * @param <Input> Tipo dos objetos na lista de origem.
     * @param <Output> Tipo da classe dos objetos de destino.
     * @param sourceList A lista de objetos a ser mapeada.
     * @param clazz A classe para a qual os objetos serão mapeados.
     * @return Uma lista de objetos do tipo de destino.
     */
    public <Input, Output> List<Output> mapAll(final List<Input> sourceList, final Class<Output> clazz) {
        /* Utiliza a Stream API para processar a lista e mapear cada item individualmente,
         coletando os resultados em uma nova lista. */
        return sourceList.stream()
                .map(source -> map(source, clazz))
                .collect(Collectors.toList());
    }
}