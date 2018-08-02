package geneticalgorithms;

public class GeneticalAlgorithmTeste {

    private static final int[] list = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int ideal_sum = 38;
    private static final int ideal_prod = 210;

    private static final int populationLen = 250;
    private static final double p_c = 0.6d; // crossover
    private static final double p_m = 0.002d; // mutação

    public static void main(String[] args){
        try {

            Fitness fitness = new Fitness() {
                @Override
                public double fitness(String chromosome) {

                    int chromosome_sum = 0;
                    int chromosome_prod = 1;

                    for(int i = 0; i < chromosome.length(); i++) {
                        char c = chromosome.charAt(i);
                        if (c == '0') chromosome_sum += list[i];
                        else chromosome_prod *= list[i];
                    }

                    // distancia ao valor pretendido (quanto menor -> melhor)
                    double score = Math.sqrt( Math.pow(chromosome_sum - ideal_sum, 2) + Math.pow(chromosome_prod - ideal_prod, 2) );

                    // quanto mais proximo de 1 -> melhor
                    return 1 / (score + 1);
                }
            };

            String res = GeneticAlgorithm.findResult(populationLen, list.length, fitness, p_c, p_m);
            System.out.println("\nres = " + res);

        }catch (ChromosomesDifferentLengthsException e){
            e.printStackTrace();

        } catch (InvalidProbabilityException e){
            e.printStackTrace();

        } catch (InvalidChromosomeLengthException e) {
            e.printStackTrace();

        } catch (InvalidPopulationLengthException e){
            e.printStackTrace();
        }
    }
}
