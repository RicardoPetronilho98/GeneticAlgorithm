package geneticalgorithms;

public class GeneticalAlgorithmTeste {

    /* Como organizar a lista de forma a criar 2 grupos em que a soma dos elementos do grupo 1 seja X e o produto
       dos elementos do grupo 2 seja Y?
       nota: não interessa a dimensão de cada grupo, exemplo: pode haver 6 elementos no grupo 1 e 4 no grupo 2
    */
    private static final int[] list = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    /* contexto: array que representa a massa (Kg) das malas de 20 pessoas para um voo

       Como organizar as malas de forma a criar 2 grupos de malas em que a soma dos elementos dos grupos seja o mesmo?
       nota: não interessa a dimensão de cada grupo, exemplo: pode haver 3 malas no grupo 1 e 17 no grupo 2
    */
    private static final int[] kilos = new int[] {403, 905, 499, 22, 399, 604, 528, 103, 708, 179, 949, 47, 828, 291, 832, 292, 472, 515, 674, 376};

    private static final int populationLen = 250;
    private static final double p_c = 0.6d; // crossover
    private static final double p_m = 0.002d; // mutação

    public static void main(String[] args){


        try {

            Fitness fitness = new Fitness() {
                @Override
                public double fitness(String chromosome) {

                    final int ideal_sum = 38;
                    final int ideal_prod = 210;

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

            Fitness fitness2 = new Fitness() {
                @Override
                public double fitness(String chromosome) {

                    int chromosome_sum1 = 0;
                    int chromosome_sum2 = 0;

                    for(int i = 0; i < chromosome.length(); i++) {
                        char c = chromosome.charAt(i);
                        if (c == '0') chromosome_sum1 += kilos[i];
                        else chromosome_sum2 += kilos[i];
                    }

                    double score = Math.abs(chromosome_sum1 - chromosome_sum2);

                    return 1 / (score + 1);
                }
            };

            String res = GeneticAlgorithm.findResult(populationLen, list.length, fitness, p_c, p_m);
            System.out.println("\nres = " + res);

            res = GeneticAlgorithm.findResult(populationLen, kilos.length, fitness2, p_c, p_m);
            System.out.println("\nres = " + res);

            int grupo_1 = 0;
            int grupo_2 = 0;

            for(int i = 0; i < res.length(); i++){
                if (res.charAt(i) == '0') grupo_1 += kilos[i];
                else grupo_2 += kilos[i];
            }

            System.out.println(grupo_1 == grupo_2);

        }catch (ChromosomesDifferentLengthsException e){
            e.printStackTrace();

        } catch (InvalidProbabilityException e){
            e.printStackTrace();

        } catch (InvalidChromosomeLengthException e) {
            e.printStackTrace();

        } catch (InvalidPopulationLengthException e){
            e.printStackTrace();

        } catch (ProblemHasNoResultException e){
            e.printStackTrace();
        }
    }
}
