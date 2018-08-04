package ga;

public interface Fitness {

    /**
     * Dado um cromossoma retorna o corespondente fitness para o problema contextualizado.
     *
     * @param chromosome cromossoma
     * @return fitness
     */
    double fitness(String chromosome);
}
