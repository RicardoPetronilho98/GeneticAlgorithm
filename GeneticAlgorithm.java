package geneticalgorithms;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GeneticAlgorithm {

    private static final int MAX_ATTEMPS = 75;

    /**
     * Cria um cromossoma com uma dimensão dada.
     *
     * @param length dimensão do cromossoma
     * @return o novo cromossoma criado
     * @throws InvalidChromosomeLengthException dimensão - length - do cromossoma é inválida
     */
    private static String generate(int length) throws InvalidChromosomeLengthException {

        if (length <= 0) throw new InvalidChromosomeLengthException(Integer.toString(length));

        Random random = new Random();
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < length; i++){
            boolean bool = random.nextBoolean();
            if (bool) res.append('1');
            else res.append('0');
        }

        return res.toString();
    }

    /**
     * Cria um novo cromossoma através da ocorrência de mutações em cada posição do cromossoma dado.
     *
     * @param chromosome cromossoma
     * @param p probabilidade de ocorrer a mutação em cada posição do cromossoma (0 <= p <= 1)
     * @return o cromossoma mutado
     * @throws InvalidProbabilityException probabilidade - p - dada é inválida
     */
    private static String mutate(String chromosome, double p) throws InvalidProbabilityException {

        if (p < 0 || p > 1) throw new InvalidProbabilityException(Double.toString(p));

        StringBuilder res = new StringBuilder();

        for(int i = 0; i < chromosome.length(); i++){
            char c = chromosome.charAt(i);
            if (Math.random() <= p){
                if (c == '1') res.append('0');
                else res.append('1');
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
    private static String[] crossover(String chromosome1, String chromosome2, int index, double p) throws InvalidProbabilityException, ChromosomesDifferentLengthsException, IndexOutOfBoundsException {

        if (p < 0 || p > 1) throw new InvalidProbabilityException(Double.toString(p));
        else if (chromosome1.length() != chromosome2.length()) throw new ChromosomesDifferentLengthsException();
        else if (index < 0 || index >= chromosome1.length()) throw new IndexOutOfBoundsException(Integer.toString(index));

        if (Math.random() < p) {
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

        else return null;
    }

    /**
     * Dada uma população de cromossomas, cria um conjunto de pares com cada cromossoma e a sua correpondente fitness.
     *
     * @param population conjunto de cromossomas
     * @param fitness interface que implementa o método fitness()
     * @return o conjunto de (cromossoma, fitness)
     */
    private static Collection<Pair<String, Double>> mapPopulationFitness(Collection<String> population, Fitness fitness){
        Collection<Pair<String, Double>> res = new ArrayList<>(population.size());
        population.forEach(chromosome -> res.add(new Pair<>(chromosome, fitness.fitness(chromosome))));
        return res;
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
    private static Collection<String> populate(int populationLen, int chromosomeLen) throws InvalidPopulationLengthException, InvalidChromosomeLengthException{

        if (populationLen <= 0) throw new InvalidPopulationLengthException(Integer.toString(populationLen));

        Collection<String> res = new ArrayList<>();
        for(int i = 0; i < populationLen; i++) res.add(generate(chromosomeLen));
        return res;
    }

    /**
     * Retorna um cromossoma possível como resposta ao problema contextualizado.
     *
     * @param fitnesses conjunto de pares (cromossoma, fitness)
     * @return o possível cromossoma como resposta
     */
    private static String rouletteWheelSelection(Collection<Pair<String, Double>> fitnesses){
        final double p = Math.random();
        String res = null;
        double ac = 0d;
        for(Pair<String, Double> pair: fitnesses) {
            ac += pair.getValue();
            if (ac >= p) {
                res = pair.getKey();
                break;
            }
        }
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
     * @return o resultado correto para o problema contextualziado
     * @throws InvalidPopulationLengthException a dimensão da população é inválida
     * @throws InvalidChromosomeLengthException a dimensão do cromossoma é inválida
     * @throws InvalidProbabilityException a probabilidade é inválida
     * @throws ChromosomesDifferentLengthsException os cromossomas têm diferentes dimensões
     */
    public static String findResult(int populationLen, int chromosomeLen, Fitness fitness, double p_c, double p_m) throws InvalidPopulationLengthException, InvalidChromosomeLengthException, InvalidProbabilityException, ChromosomesDifferentLengthsException, ProblemHasNoResultException {

        String res = null;
        int populationCount = 0;

        do {
            Collection<String> population = GeneticAlgorithm.populate(populationLen, chromosomeLen);
            Collection<String> new_population = new ArrayList<>();
            int count = 0; // dimensão da nova população

            while (count < populationLen) {

                if (populationCount > MAX_ATTEMPS) throw new ProblemHasNoResultException();

                String chromosome_1 = GeneticAlgorithm.rouletteWheelSelection(GeneticAlgorithm.mapPopulationFitness(population, fitness));
                if (chromosome_1 == null) continue;
                population.remove(chromosome_1); // para o chromosome_2 não ser igual ao 1

                String chromosome_2 = GeneticAlgorithm.rouletteWheelSelection(GeneticAlgorithm.mapPopulationFitness(population, fitness));
                population.add(chromosome_1); // para não aldrabar a população
                if (chromosome_2 == null) continue;

                String[] new_chromosomes = GeneticAlgorithm.crossover(chromosome_1, chromosome_2, new Random().nextInt(chromosome_1.length()), p_c);
                if (new_chromosomes == null) continue;

                new_chromosomes[0] = GeneticAlgorithm.mutate(new_chromosomes[0], p_m);
                new_chromosomes[1] = GeneticAlgorithm.mutate(new_chromosomes[1], p_m);

                new_population.add(new_chromosomes[0]);
                new_population.add(new_chromosomes[1]);

                population = new_population;

                count += 2;
            }

            for (String chromosome : population)
                if (fitness.fitness(chromosome) == 1)
                    res = chromosome;

            populationCount++;

        } while (res == null);

        return res;
    }
}
