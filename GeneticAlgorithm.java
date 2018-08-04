package geneticalgorithms;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    /**
     * Cria um cromossoma aleatóio.
     *
     * @param length dimensão do cromossoma
     * @param alphabet alfabeto
     * @return o novo cromossoma criado
     * @throws InvalidChromosomeLengthException dimensão - length - do cromossoma é inválida
     */
    protected static String generate(int length, String alphabet) throws InvalidChromosomeLengthException {

        if (length <= 0) throw new InvalidChromosomeLengthException(Integer.toString(length));

        Random rand = new Random();
        StringBuilder res = new StringBuilder();
        int index;

        for(int i = 0; i < length; i++){
            index = rand.nextInt(alphabet.length());
            res.append(alphabet.charAt(index));
        }

        return res.toString();
    }

    /**
     * Cria um novo cromossoma através da ocorrência de mutações em cada posição do cromossoma dado.
     *
     * @param chromosome cromossoma
     * @param p probabilidade de ocorrer a mutação em cada posição do cromossoma (0 <= p <= 1)
     * @param alphabet alfabeto
     * @return o cromossoma mutado
     * @throws InvalidProbabilityException probabilidade - p - dada é inválida
     */
    protected static String mutate(String chromosome, double p, String alphabet) throws InvalidProbabilityException {

        if (p < 0 || p > 1) throw new InvalidProbabilityException(Double.toString(p));

        StringBuilder res = new StringBuilder();
        Random rand = new Random();
        int index;

        for(int i = 0; i < chromosome.length(); i++){
            char c = chromosome.charAt(i);
            if (rand.nextDouble() <= p){
                index = rand.nextInt(alphabet.length());
                res.append(alphabet.charAt(index));
            }
            else res.append(c);
        }

        return res.toString();
    }

    /**
     * Troca uma parte de um cromossoma com outro a partir de uma determinada posição dada.
     *
     * @param chromosome1 cromossoma 1
     * @param chromosome2 cromossoma 2
     * @param index posição a partir da qual ocorre a troca
     * @return os dois novos cromossomas resultantes da crossover
     * @throws IndexOutOfBoundsException índice - index - dado é inválido
     * @throws ChromosomesDifferentLengthsException dimensão de ambos os cromossomas são diferentes
     */
    protected static String[] crossover(String chromosome1, String chromosome2, int index) throws ChromosomesDifferentLengthsException, IndexOutOfBoundsException {

        if (chromosome1.length() != chromosome2.length()) throw new ChromosomesDifferentLengthsException();
        else if (index < 0 || index >= chromosome1.length()) throw new IndexOutOfBoundsException(Integer.toString(index));

        StringBuilder res1 = new StringBuilder();
        StringBuilder res2 = new StringBuilder();

        // primeira parte de ambos os cromossomas mantêm-se iguais
        for (int i = 0; i < index; i++) {
            res1.append(chromosome1.charAt(i));
            res2.append(chromosome2.charAt(i));
        }

        // segunda parte de ambos os cromossomas são trocados
        for (int i = index; i < chromosome1.length(); i++) {
            res1.append(chromosome2.charAt(i));
            res2.append(chromosome1.charAt(i));
        }

        return new String[]{res1.toString(), res2.toString()};
    }


    /**
     * Cria uma população com uma determinada dimensão em que cada cromossoma tem uma determinada dimensão.
     *
     * @param populationLen dimensão da população
     * @param chromosomeLen dimensão de cada cromossoma
     * @param alphabet alfabeto
     * @return a população (conjunto de cromossomas)
     * @throws InvalidPopulationLengthException dimensão da população é inválida
     * @throws InvalidChromosomeLengthException dimensção de cada cromossoma é inválida
     */
    protected static List<String> populate(int populationLen, int chromosomeLen, String alphabet) throws InvalidPopulationLengthException, InvalidChromosomeLengthException{

        if (populationLen <= 0) throw new InvalidPopulationLengthException(Integer.toString(populationLen));

        List<String> res = new ArrayList<>(populationLen);
        for(int i = 0; i < populationLen; i++) res.add(generate(chromosomeLen, alphabet));
        return res;
    }

    /**
     * Dada uma população de cromossomas, cria um conjunto de pares com cada cromossoma e a sua correpondente fitness.
     *
     * @param population conjunto de cromossomas
     * @param fitness interface que implementa o método fitness()
     * @return o conjunto de (cromossoma, fitness)
     */
    protected static Collection<Pair<String, Double>> mapPopulationFitness(List<String> population, Fitness fitness){
        Collection<Pair<String, Double>> res = new ArrayList<>(population.size());
        population.forEach(chromosome -> res.add(new Pair<>(chromosome, fitness.fitness(chromosome))));
        return res;
    }

    /**
     * Retorna o melhor cromossoma possível como resposta ao problema contextualizado.
     *
     * @param population população
     * @return o possível cromossoma como resposta
     */
    protected static String[] selection(List<String> population, Fitness fitness){

        String[] parents = new String[2];

        Collection<Pair<String, Double>> fitnesses = mapPopulationFitness(population, fitness);

        double max = 0d;
        Pair<String, Double> maxPair = null;

        for (Pair<String, Double> pair: fitnesses) {
            if (pair.getValue() > max) {
                maxPair = pair;
                max = maxPair.getValue();
            }
        }

        parents[0] = maxPair.getKey();

        fitnesses.remove(maxPair);
        max = 0d;

        for (Pair<String, Double> pair: fitnesses) {
            if (pair.getValue() > max) {
                maxPair = pair;
                max = maxPair.getValue();
            }
        }

        parents[1] = maxPair.getKey();

        return parents;
    }

    protected static List<String> evolve(List<String> population, Fitness fitness, double p_c, double p_m, String alphabet) throws InvalidProbabilityException, ChromosomesDifferentLengthsException {

        List<String> new_population = new ArrayList<>(population.size());
        String[] new_chromosomes;

        while (new_population.size() < population.size()){

            new_chromosomes = selection(population, fitness); // SELEÇÃO

            if (p_c < Math.random()) { // CROSSOVER
                new_chromosomes = crossover(new_chromosomes[0], new_chromosomes[1], new Random().nextInt(new_chromosomes[0].length()));
            }

            // MUTAÇÃO
            new_population.add(mutate(new_chromosomes[0], p_m, alphabet));
            new_population.add(mutate(new_chromosomes[1], p_m, alphabet));
        }

        return new_population;
    }

    protected static String hasFoundResult(List<String> population, Fitness fitness){

        String res = null;

        for (String chromosome: population)
            if (fitness.fitness(chromosome) == 1)
                res = chromosome;

        return res;
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
     * @param alphabet alfabeto
     * @return o resultado correto para o problema contextualziado
     * @throws InvalidPopulationLengthException a dimensão da população é inválida
     * @throws InvalidChromosomeLengthException a dimensão do cromossoma é inválida
     * @throws InvalidProbabilityException a probabilidade é inválida
     * @throws ChromosomesDifferentLengthsException os cromossomas têm diferentes dimensões
     */
    public static String findResult(int populationLen, int chromosomeLen, Fitness fitness, double p_c, double p_m, String alphabet) throws InvalidPopulationLengthException, InvalidChromosomeLengthException, InvalidProbabilityException, ChromosomesDifferentLengthsException {

        long before = System.currentTimeMillis();

        String res;
        int genCount = 0;
        List<String> population = populate(populationLen, chromosomeLen, alphabet); // população inicial

        while ( (res = hasFoundResult(population, fitness)) == null ) {
            if (genCount % 50 == 0 && genCount != 0) System.err.println("\n(evolving) geração nº => " + genCount);
            population = evolve(population, fitness, p_c, p_m, alphabet); // evolução da população
            genCount++;
        }

        long after = System.currentTimeMillis();

        System.out.println("\ngeração nº => " + genCount + " | millis => " + (after - before) );

        return res;
    }
}
