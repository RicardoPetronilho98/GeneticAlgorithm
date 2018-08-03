package geneticalgorithms;

public class GeneticAlgorithmTeste {

    /* Como organizar a lista de forma a criar 2 grupos em que a soma dos elementos do grupo 1 seja X e o produto
       dos elementos do grupo 2 seja Y?
       nota: não interessa a dimensão de cada grupo, exemplo: pode haver 6 elementos no grupo 1 e 4 no grupo 2
    */
    private static final int[] list = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    /* contexto: array que representa a massa (Kg) das malas de 1000 pessoas para um voo

       Como organizar as malas de forma a criar 2 grupos de malas em que a soma dos elementos dos grupos seja o mesmo?
       nota: não interessa a dimensão de cada grupo, exemplo: pode haver 3 malas no grupo 1 e 17 no grupo 2
    */
    private static final int[] kilos = new int[] { 329, 178, 921, 497, 865, 203, 568, 83, 977, 512, 91, 632, 369, 287, 97, 90, 876, 120, 361, 397, 643, 834, 709, 220, 262, 584, 16, 17, 878, 832, 809, 243, 297, 499, 980, 114, 867, 464, 865, 654, 534, 332, 636, 669, 926, 692, 504, 380, 404, 739, 968, 533, 508, 222, 692, 350, 733, 37, 332, 954, 300, 524, 980, 577, 909, 896, 900, 691, 72, 837, 523, 57, 204, 995, 296, 556, 787, 786, 572, 559, 561, 82, 967, 255, 633, 45, 125, 900, 942, 552, 965, 903, 611, 300, 406, 635, 929, 371, 206, 920, 99, 374, 753, 826, 294, 692, 778, 608, 690, 916, 257, 985, 671, 387, 617, 801, 542, 705, 685, 448, 606, 702, 982, 396, 984, 138, 651, 277, 863, 130, 934, 22, 343, 79, 431, 300, 185, 968, 736, 598, 40, 687, 91, 404, 403, 897, 251, 532, 151, 847, 190, 512, 128, 434, 108, 612, 605, 572, 576, 731, 141, 17, 175, 393, 537, 819, 529, 826, 856, 581, 195, 305, 729, 575, 743, 398, 256, 625, 73, 279, 752, 259, 318, 591, 841, 96, 411, 875, 623, 907, 7, 913, 727, 122, 7, 860, 214, 735, 500, 650, 247, 262, 786, 190, 369, 568, 951, 737, 515, 853, 198, 260, 109, 57, 768, 832, 932, 293, 919, 198, 649, 134, 982, 987, 688, 163, 975, 851, 880, 432, 42, 735, 91, 7, 855, 137, 699, 678, 705, 955, 191, 787, 575, 303, 881, 524, 560, 177, 590, 412, 523, 513, 649, 696, 488, 693, 740, 95, 269, 355, 212, 607, 433, 52, 871, 765, 607, 980, 941, 364, 368, 182, 24, 647, 328, 949, 810, 697, 547, 765, 522, 633, 515, 175, 90, 820, 257, 563, 151, 258, 389, 496, 30, 771, 220, 383, 440, 514, 670, 574, 138, 183, 880, 552, 291, 667, 276, 657, 874, 420, 51, 360, 708, 129, 173, 989, 999, 551, 357, 145, 975, 169, 851, 605, 754, 150, 775, 523, 929, 285, 215, 449, 573, 755, 965, 404, 316, 437, 216, 256, 735, 161, 670, 909, 585, 814, 563, 558, 950, 37, 296, 876, 117, 431, 42, 559, 52, 277, 694, 540, 318, 855, 143, 497, 348, 140, 171, 221, 325, 192, 40, 566, 755, 876, 662, 602, 388, 54, 401, 455, 225, 689, 518, 366, 727, 240, 111, 961, 559, 532, 925, 902, 132, 270, 945, 899, 433, 405, 343, 25, 884, 40, 911, 894, 51, 224, 337, 226, 890, 29, 189, 771, 565, 969, 708, 412, 784, 769, 118, 184, 441, 247, 41, 4, 958, 813, 282, 587, 905, 84, 796, 323, 501, 554, 26, 892, 251, 186, 490, 913, 166, 589, 337, 543, 815, 263, 893, 346, 173, 549, 754, 367, 372, 474, 68, 460, 41, 552, 406, 844, 992, 875, 981, 39, 811, 697, 981, 933, 927, 678, 725, 779, 108, 188, 873, 982, 537, 632, 60, 349, 609, 512, 976, 163, 14, 368, 340, 785, 344, 51, 424, 683, 74, 647, 224, 241, 162, 249, 539, 957, 203, 216, 647, 876, 913, 885, 602, 805, 312, 408, 759, 399, 584, 494, 668, 200, 313, 830, 548, 663, 40, 891, 526, 171, 963, 627, 280, 252, 143, 87, 585, 375, 515, 283, 410, 168, 960, 133, 432, 720, 944, 194, 618, 541, 95, 452, 457, 936, 595, 816, 944, 554, 172, 467, 153, 832, 431, 837, 450, 102, 917, 142, 26, 912, 419, 44, 316, 467, 21, 695, 399, 194, 220, 131, 607, 844, 106, 733, 564, 778, 14, 849, 324, 358, 928, 488, 568, 1000, 962, 823, 808, 466, 728, 682, 220, 35, 438, 167, 352, 153, 565, 639, 730, 439, 66, 264, 763, 318, 611, 357, 560, 152, 375, 615, 749, 351, 313, 156, 765, 828, 807, 526, 160, 652, 341, 623, 641, 733, 202, 232, 778, 558, 980, 1, 734, 324, 357, 706, 355, 73, 145, 408, 643, 622, 498, 692, 933, 66, 962, 53, 5, 27, 324, 206, 644, 856, 944, 234, 909, 937, 545, 603, 958, 670, 5, 97, 543, 317, 424, 384, 506, 295, 709, 491, 686, 137, 539, 317, 194, 329, 951, 646, 363, 894, 796, 442, 474, 90, 691, 853, 877, 772, 32, 291, 640, 205, 972, 692, 624, 837, 169, 860, 324, 710, 29, 481, 704, 845, 437, 102, 7, 177, 269, 852, 116, 316, 298, 607, 213, 214, 589, 247, 717, 673, 388, 229, 837, 464, 254, 757, 94, 936, 26, 92, 781, 752, 60, 158, 731, 45, 390, 74, 768, 406, 815, 634, 740, 583, 459, 594, 649, 880, 783, 153, 665, 221, 919, 829, 983, 681, 334, 961, 556, 371, 968, 458, 337, 443, 871, 727, 932, 582, 617, 510, 817, 292, 474, 596, 38, 651, 387, 772, 155, 508, 791, 993, 820, 380, 334, 359, 628, 685, 864, 523, 392, 666, 879, 586, 781, 162, 897, 159, 206, 795, 449, 254, 422, 360, 249, 502, 351, 341, 14, 676, 637, 655, 768, 306, 11, 136, 605, 902, 471, 232, 921, 263, 515, 624, 667, 458, 811, 768, 47, 946, 943, 265, 660, 182, 916, 132, 937, 309, 75, 560, 257, 980, 277, 59, 783, 160, 193, 864, 872, 431, 748, 55, 724, 369, 940, 706, 824, 724, 433, 91, 236, 805, 808, 987, 812, 338, 550, 301, 213, 772, 317, 233, 421, 378, 910, 845, 303, 832, 379, 486, 746, 222, 967, 644, 854, 189, 565, 331, 298, 829, 386, 942, 884, 490, 40, 822, 910, 29, 151, 696, 793, 399, 978, 196, 305, 400, 900, 457, 869, 45, 157, 998, 361, 327, 581, 580, 914, 320, 603, 752, 109, 49, 850, 964, 436, 439, 417, 725, 366, 893, 333, 775, 122, 794, 686, 388, 832, 722, 899, 524, 92, 115, 866, 349, 1000, 686, 120, 468, 652, 197, 753, 852, 517, 628, 837, 547, 727, 592, 736, 365, 448, 452, 188, 55, 427, 642, 140, 829, 510, 534, 743, 328, 488, 374, 887, 543, 698, 406, 750, 894, 345, 689, 140, 868, 45, 665, 392, 734, 194, 95, 432, 57, 680, 166, 198, 229 };

    // variáveis de controlo da evolução
    private static final double p_c = 0.6d; // crossover
    private static final double p_m = 0.02d; // mutação

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

            Fitness fitness3 = new Fitness() {
                @Override
                public double fitness(String chromosome) {

                   return 0;
                }
            };

            String res;

            //res = GeneticAlgorithm.findResult(1000, list.length, fitness, p_c, p_m);
            //System.out.println("\nres = " + res);

            res = GeneticAlgorithm.findResult(100, kilos.length, fitness2, p_c, p_m);
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
            System.err.println("\nNão foi encontrado o resultado correto/ ideal para o problema. Tente correr de novo a evolução!");
        }
    }
}
