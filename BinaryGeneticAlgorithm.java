package geneticalgorithms;

import java.util.*;

public class BinaryGeneticAlgorithm extends GeneticAlgorithm{

    private static final String alphabet = "01";

    /**
     * Cria um cromossoma aleatóio com uma dimensão dada.
     *
     * @param length dimensão do cromossoma
     * @return o novo cromossoma criado
     * @throws InvalidChromosomeLengthException dimensão - length - do cromossoma é inválida
     */
    protected static String generate(int length) throws InvalidChromosomeLengthException {
        return generate(length, alphabet);
    }

    /**
     * Cria um novo cromossoma através da ocorrência de mutações em cada posição do cromossoma dado.
     *
     * @param chromosome cromossoma
     * @param p probabilidade de ocorrer a mutação em cada posição do cromossoma (0 <= p <= 1)
     * @return o cromossoma mutado
     * @throws InvalidProbabilityException probabilidade - p - dada é inválida
     */
    protected static String mutate(String chromosome, double p) throws InvalidProbabilityException {
        return mutate(chromosome, p, alphabet);
    }

    /**
     * Cria uma população com uma determinada dimensão em que cada cromossoma tem uma determinada dimensão.
     *
     * @param populationLen dimensão da população
     * @param chromosomeLen dimensão de cada cromossoma
     * @return a população (conjunto de cromossomas)
     * @throws InvalidPopulationLengthException dimensão da população é inválida
     * @throws InvalidChromosomeLengthException dimensção de cada cromossoma é inválida
     */
    protected static List<String> populate(int populationLen, int chromosomeLen) throws InvalidPopulationLengthException, InvalidChromosomeLengthException{
        return populate(populationLen, chromosomeLen, alphabet);
    }

    protected static List<String> evolve(List<String> population, Fitness fitness, double p_c, double p_m) throws InvalidProbabilityException, ChromosomesDifferentLengthsException {
        return evolve(population, fitness, p_c, p_m, alphabet);
    }

    /**
     * Devolve o resultado para o problema contextualizado através da interface Fitness.
     * Este método simula a evolução genética da natureza até ser encontrado o resultado esperado.
     *
     * @param populationLen dimensão da população
     * @param chromosomeLen dimensão de cada cromossoma
     * @param fitness utilizado para avaliar cada cromossoma
     * @param p_c probabilidade de crossover
     * @param p_m probabilidade de mutação
     * @return o resultado correto para o problema contextualziado
     * @throws InvalidPopulationLengthException a dimensão da população é inválida
     * @throws InvalidChromosomeLengthException a dimensão do cromossoma é inválida
     * @throws InvalidProbabilityException a probabilidade é inválida
     * @throws ChromosomesDifferentLengthsException os cromossomas têm diferentes dimensões
     */
    public static String findResult(int populationLen, int chromosomeLen, Fitness fitness, double p_c, double p_m) throws InvalidPopulationLengthException, InvalidChromosomeLengthException, InvalidProbabilityException, ChromosomesDifferentLengthsException {
        return findResult(populationLen, chromosomeLen, fitness, p_c, p_m, alphabet);
    }
}
