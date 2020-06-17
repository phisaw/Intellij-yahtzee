import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dice9 {

    // scoreCard for all the categories
    int[][]scoreCard;
    boolean[] categoryBool = { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };
    // ser till att om kategorin �r redan ett resultat i den kategorin.

    // constants for values in scoreBoard
    int ones = 1;
    int twos = 2;
    int threes =3;
    int fours = 4;
    int fives = 5;
    int sixes = 6;

    int PAIR = 7;
    int TWO_PAIR = 8;
    int THREE_OF_A_KIND = 9;
    int FOUR_OF_A_KIND = 10;
    int FULL_HOUSE = 11;
    int SMALL_STRAIGHT = 12;
    int LARGE_STRAIGHT = 13;
    int CHANCE = 14;
    int Yatzy = 15;
    //

    Random random;
    int diceValue;
    int[] aDice;
    int rollPerTurn = 0;
    int numToSave;
    int nPlayer;
    int player;
    //
    int y, w;
    int rerollb = 3;
    int rerolla = 0;
    //

    int numOfCategories = 15;

    public void play() {
        System.out.println("Välkommen till Yatzy\n");

        do {
            try {
                nPlayer = Integer.parseInt(input("Ange antal spelare: ").trim());
                if (nPlayer <1){
                    System.out.println("Det kan inte vara färre än 1 spelare");
                }
                else if (nPlayer >4){
                    System.out.println("Det kan inte vara fler än 4 spelare");
                }
            }
            catch (Exception e){
                System.out.println("fel inmatning, ange siffror mellan 1-4");
            }
        }while (nPlayer != 1 && nPlayer != 2 && nPlayer != 3 && nPlayer != 4);




        scoreCard = new int[numOfCategories][nPlayer];

        for (int[] row: scoreCard){
            Arrays.fill(row, 100);
        }

//        for (int i = 0; i < 15; i++) {
//            scoreCard[numOfCategories-1][i] = 100;
//        }

        //Game starts, with round one and player number one.
        for (int i = 0; i < numOfCategories; i++) {

            for (int player =1; player <=nPlayer; player++) {
                System.out.println();
                System.out.println("Rond Nr: "+(i + 1));
                System.out.println("Spelare: "+player);
                System.out.println();
                rollAll();

                for (int j = 0; j < 2; j++) {
                    reRollDices();
                }
                selectCategory(player-1);
                System.out.println("Slut på rundan.\n");
                if (checkCategory(player)) {
                    System.out.println("Spelet över!");
                    System.out.println("++++++++++++++++++++++++++++++");
                    System.out.println("Du får antalet poäng : " + allScore(player));
                    System.exit(0);
                }
            }


        }
    }

    public Dice9() {
        diceValue = 0;
        random = new Random();
    }

    public int rollDice() {
        diceValue = random.nextInt(6) + 1;
        return diceValue;
    }

    public void selectCategory(int player) {

        System.out.println("\n------------");
        System.out.println("Spelare: " +(player+1));
        System.out.println("------------");

        if (scoreCard[0][player] == 100) {
            System.out.println("1. Ettor :");
        }
        else
            System.out.println("1. Ettor :" + scoreCard[0][player]);

        if (scoreCard[1][player] == 100) {
            System.out.println("1. Tvåor :");
        }
        else
            System.out.println("2. Tvåor :" + scoreCard[1][player]);

        if (scoreCard[2][player] == 100) {
            System.out.println("3. Treor :");
        }
        else
            System.out.println("3. Treor :" + scoreCard[2][player]);

        if (scoreCard[3][player] == 100) {
            System.out.println("4. Fyror :");
        }
        else
            System.out.println("4. Fyror :" + scoreCard[3][player]);

        if (scoreCard[4][player] == 100) {
            System.out.println("5. Femor :");
        }
        else
            System.out.println("5. Femor :" + scoreCard[4][player]);

        if (scoreCard[5][player] == 100) {
            System.out.println("6. Sexor :");
        }
        else
            System.out.println("6. Sexor :" + scoreCard[5][player]);


        System.out.println();
        if (scoreCard[6][player] == 100) {
            System.out.println("7. Par :");
        }
        else
            System.out.println("7. Par :" + scoreCard[6][player]);

        if (scoreCard[7][player] == 100) {
            System.out.println("8. Två Par :");
        }
        else
            System.out.println("8. Två Par :" + scoreCard[7][player]);

        if (scoreCard[8][player] == 100) {
            System.out.println("9. Triss :");
        }
        else
            System.out.println("9. Triss :" + scoreCard[8][player]);

        if (scoreCard[9][player] == 100) {
            System.out.println("10. Fyrtal:");
        }
        else
            System.out.println("10. Fyrtal:" + scoreCard[9][player]);

        if (scoreCard[10][player] == 100) {
            System.out.println("11. Kåk :");
        }
        else
            System.out.println("11. Kåk :" + scoreCard[10][player]);

        if (scoreCard[11][player] == 100) {
            System.out.println("12. Liten Stege :");
        }
        else
            System.out.println("12. Liten Stege :" + scoreCard[11][player]);

        if (scoreCard[12][player] == 100) {
            System.out.println("13. Stor Stege :");
        }
        else
            System.out.println("13. Stor Stege :" + scoreCard[12][player]);

        if (scoreCard[13][player] == 100) {
            System.out.println("14. Chans :");
        }
        else
            System.out.println("14. Chans :" + scoreCard[13][player]);

        if (scoreCard[14][player] == 100) {
            System.out.println("15. Yatzy :");
        }
        else
            System.out.println("15. Yatzy :" + scoreCard[14][player]);

        System.out.println("------------");
        System.out.println();
        Arrays.sort(aDice);
        System.out.println(Arrays.toString(aDice));
        System.out.println();


        boolean same = false;
        int category = 0;
        do {

            do {
                try {
                    category = Integer.parseInt(input("Välj Kategori (1-15) att spara: ").trim());
                    if (category > numOfCategories) {
                        System.out.println("Du skrev in fel. För stor ! ");
                    }
                    if (category < 1) {
                        System.out.println("Du skrev in fel. För små ! ");
                    }
                } catch (Exception e) {
                    System.out.println("Error input ");
                    category = -1;
                }
            } while (category != 9 && category != 1 && category != 2 && category != 3 && category != 4 && category != 5
                    && category != 6 && category != 7 && category != 8 && category != 10 && category != 11 && category != 12 && category != 13 && category != 14 && category != 15 );

            if (categoryBool[category - 1] == true) { // man f�r inte v�lja samma category
                same = true;
                System.out.println("fel, samma! välj kategori (1-15) igen");

            } else {
                calculateCategoryScore(category, player);
                categoryBool[category - 1] = true;
                same = false;
            }

        } while (same);

    }

    private int allScore(int player) {
        int scores = 0;
        for (int n = 0; n < numOfCategories; n++) {
            scores += scoreCard[n][nPlayer];
        }
        return scores;
    }

    // ser till att om alla kategorin.
    public boolean checkCategory(int player) {
        boolean allChoose = false;
        for (int n = 0; n < numOfCategories; n++) {
            if (categoryBool[n] == false) {
                allChoose = false;
                break;
            } else {
                allChoose = true;
            }
        }
        return allChoose;
    }

    // r�knar ihop po�ng beroende p� vad man har valt f�r kategori
    private void calculateCategoryScore(int category, int player) {
        int score = 0;

        // kollar hur mycket po�ng man f�r antal t�rningar av samma
        if (category >= ones && category <= sixes) {
            for (int i = 0; i < aDice.length; i++) {
                if (aDice[i] == category) {
                    score += category;
                }
            }
        }

        else if (category == PAIR ) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        }

        else if (category == TWO_PAIR ) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        }

        else if (category == SMALL_STRAIGHT ) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        }

        else if (category == LARGE_STRAIGHT ) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        }

        else if (category == FULL_HOUSE ) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        }

        else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
            if (checkSpecialCategory(category)) {
                score = numToSave;
            } else {
                score = 0;
            }
        } else if (category == CHANCE) {
            for (int dice = 0; dice < aDice.length; dice++) {
                score += aDice[dice];
            }
        } else if(category == Yatzy){
            if(checkSpecialCategory(category)){
                score = 50;
            }
        }
        // sparar resultatet till r�tt kategori
        scoreCard[category - 1][player] = score;

        System.out.println("\n<Resultat>");
        System.out.println("------------");
        System.out.println("Spelare: " +(player+1));
        System.out.println("------------");

        if (scoreCard[0][player] == 100) {
            System.out.println("1. Ettor :");
        }
        else
            System.out.println("1. Ettor :" + scoreCard[0][player]);

        if (scoreCard[1][player] == 100) {
            System.out.println("1. Tvåor :");
        }
        else
            System.out.println("2. Tvåor :" + scoreCard[1][player]);

        if (scoreCard[2][player] == 100) {
            System.out.println("3. Treor :");
        }
        else
            System.out.println("3. Treor :" + scoreCard[2][player]);

        if (scoreCard[3][player] == 100) {
            System.out.println("4. Fyror :");
        }
        else
            System.out.println("4. Fyror :" + scoreCard[3][player]);

        if (scoreCard[4][player] == 100) {
            System.out.println("5. Femor :");
        }
        else
            System.out.println("5. Femor :" + scoreCard[4][player]);

        if (scoreCard[5][player] == 100) {
            System.out.println("6. Sexor :");
        }
        else
            System.out.println("6. Sexor :" + scoreCard[5][player]);


        System.out.println();
        if (scoreCard[6][player] == 100) {
            System.out.println("7. Par :");
        }
        else
            System.out.println("7. Par :" + scoreCard[6][player]);

        if (scoreCard[7][player] == 100) {
            System.out.println("8. Två Par :");
        }
        else
            System.out.println("8. Två Par :" + scoreCard[7][player]);

        if (scoreCard[8][player] == 100) {
            System.out.println("9. Triss :");
        }
        else
            System.out.println("9. Triss :" + scoreCard[8][player]);

        if (scoreCard[9][player] == 100) {
            System.out.println("10. Fyrtal:");
        }
        else
            System.out.println("10. Fyrtal:" + scoreCard[9][player]);

        if (scoreCard[10][player] == 100) {
            System.out.println("11. Kåk :");
        }
        else
            System.out.println("11. Kåk :" + scoreCard[10][player]);

        if (scoreCard[11][player] == 100) {
            System.out.println("12. Liten Stege :");
        }
        else
            System.out.println("12. Liten Stege :" + scoreCard[11][player]);

        if (scoreCard[12][player] == 100) {
            System.out.println("13. Stor Stege :");
        }
        else
            System.out.println("13. Stor Stege :" + scoreCard[12][player]);

        if (scoreCard[13][player] == 100) {
            System.out.println("14. Chans :");
        }
        else
            System.out.println("14. Chans :" + scoreCard[13][player]);

        if (scoreCard[14][player] == 100) {
            System.out.println("15. Yatzy :");
        }
        else
            System.out.println("15. Yatzy :" + scoreCard[14][player]);

        System.out.println("------------");

    }

    public boolean checkSpecialCategory(int category) {
        ArrayList<Integer> one = new ArrayList<Integer>();
        ArrayList<Integer> two = new ArrayList<Integer>();
        ArrayList<Integer> three = new ArrayList<Integer>();
        ArrayList<Integer> four = new ArrayList<Integer>();
        ArrayList<Integer> five = new ArrayList<Integer>();
        ArrayList<Integer> six = new ArrayList<Integer>();
        for (int dice = 0; dice < aDice.length; dice++) {

            if (aDice[dice] == 1){
                one.add(1);
            } else if (aDice[dice] == 2) {
                two.add(1);
            } else if (aDice[dice] == 3) {
                three.add(1);
            } else if (aDice[dice] == 4) {
                four.add(1);
            } else if (aDice[dice] == 5) {
                five.add(1);
            } else if (aDice[dice] == 6) {
                six.add(1);
            }
        }


        if (category == THREE_OF_A_KIND) {

            if (one.size() >= 3) {
                numToSave = 3;
                return true;
            } else if (two.size() >= 3) {
                numToSave = 6;
                return true;
            } else if (three.size() >= 3) {
                numToSave = 9;
                return true;
            } else if (four.size() >= 3) {
                numToSave = 12;
                return true;
            } else if (five.size() >= 3) {
                numToSave = 15;
                return true;
            } else if (six.size() >= 3) {
                numToSave = 18;
                return true;
            }

        }else if (category == PAIR) {

            if (six.size() >= 2) {
                numToSave = 12;
                return true;
            } else if (five.size() >= 2) {
                numToSave = 10;
                return true;
            } else if (four.size() >= 2) {
                numToSave = 8;
                return true;
            } else if (three.size() >= 2) {
                numToSave = 6;
                return true;
            } else if (two.size() >= 2) {
                numToSave = 4;
                return true;
            } else if (one.size() >= 2) {
                numToSave = 2;
                return true;
            }
        } else if (category == TWO_PAIR) {

            if (six.size() >= 2 && five.size() >= 2) {
                numToSave = 22;
                return true;
            }else if (six.size() >= 2 && four.size() >= 2) {
                numToSave = 20;
                return true;
            }else if (six.size() >= 2 && three.size() >= 2) {
                numToSave = 18;
                return true;
            }else if (six.size() >= 2 && two.size() >= 2) {
                numToSave = 16;
                return true;
            }else if (six.size() >= 2 && one.size() >= 2) {
                numToSave = 14;
                return true;
            }else if (five.size() >= 2 && four.size() >= 2) {
                numToSave = 18;
                return true;
            }else if (five.size() >= 2 && three.size() >= 2) {
                numToSave = 16;
                return true;
            }else if (five.size() >= 2 && two.size() >= 2) {
                numToSave = 14;
                return true;
            }else if (five.size() >= 2 && one.size() >= 2) {
                numToSave = 12;
                return true;
            } else if (four.size() >= 2 && three.size() >= 2) {
                numToSave = 14;
                return true;
            }else if (four.size() >= 2 && two.size() >= 2) {
                numToSave = 12;
                return true;
            }else if (four.size() >= 2 && one.size() >= 2) {
                numToSave = 10;
                return true;
            }else if (three.size() >= 2 && two.size() >= 2) {
                numToSave = 10;
                return true;
            }else if (three.size() >= 2 && one.size() >= 2) {
                numToSave = 8;
                return true;
            }else if (two.size() >= 2 && one.size() >= 2) {
                numToSave = 6;
                return true;
            }

        }else if (category == SMALL_STRAIGHT) {
            if (one.size() >= 1 && two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1) {
                numToSave = 15;
                return true;
            }
        }else if (category == LARGE_STRAIGHT) {
            if (two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1 && six.size() >= 1) {
                numToSave = 20;
                return true;
            }
        }else if (category == FOUR_OF_A_KIND) {

            if (one.size() >= 4){
                numToSave = 4;
                return true;
            }
            else if (two.size()>= 4) {
                numToSave = 8;
                return true;
            }
            else if (three.size()>= 4) {
                numToSave = 12;
                return true;
            }
            else if (four.size()>= 4) {
                numToSave = 16;
                return true;
            }
            else if (five.size()>= 4) {
                numToSave = 20;
                return true;
            }
            else if (six.size()>= 4) {
                numToSave = 24;
                return true;
            }
        }else if (category == FULL_HOUSE) {

            if (six.size() >= 3 && five.size() >= 2) {
                numToSave = 28;
                return true;
            }else if (six.size() >= 3 && four.size() >= 2) {
                numToSave = 26;
                return true;
            }else if (six.size() >= 3 && three.size() >= 2) {
                numToSave = 24;
                return true;
            }else if (six.size() >= 3 && two.size() >= 2) {
                numToSave = 22;
                return true;
            }else if (six.size() >= 3 && one.size() >= 2) {
                numToSave = 20;
                return true;
            }else if (five.size() >= 3 && six.size() >= 2) {
                numToSave = 27;
                return true;
            }else if (five.size() >= 3 && four.size() >= 2) {
                numToSave = 23;
                return true;
            }else if (five.size() >= 3 && three.size() >= 2) {
                numToSave = 21;
                return true;
            }else if (five.size() >= 3 && two.size() >= 2) {
                numToSave = 19;
                return true;
            }else if (five.size() >= 3 && one.size() >= 2) {
                numToSave = 17;
                return true;
            } else if (four.size() >= 3 && six.size() >= 2) {
                numToSave = 26;
                return true;
            }else if (four.size() >= 3 && five.size() >= 2) {
                numToSave = 22;
                return true;
            }else if (four.size() >= 3 && three.size() >= 2) {
                numToSave = 20;
                return true;
            }else if (four.size() >= 3 && two.size() >= 2) {
                numToSave = 18;
                return true;
            }else if (four.size() >= 3 && one.size() >= 2) {
                numToSave = 16;
                return true;
            }else if (three.size() >= 3 && six.size() >= 2) {
                numToSave = 21;
                return true;
            }else if (three.size() >= 3 && five.size() >= 2) {
                numToSave = 19;
                return true;
            }else if (three.size() >= 3 && four.size() >= 2) {
                numToSave = 17;
                return true;
            }else if (three.size() >= 3 && two.size() >= 2) {
                numToSave = 13;
                return true;
            }else if (three.size() >= 3 && one.size() >= 2) {
                numToSave = 11;
                return true;
            }else if (two.size() >= 3 && six.size() >= 2) {
                numToSave = 18;
                return true;
            }else if (two.size() >= 3 && five.size() >= 2) {
                numToSave = 16;
                return true;
            }else if (two.size() >= 3 && four.size() >= 2) {
                numToSave = 14;
                return true;
            }else if (two.size() >= 3 && three.size() >= 2) {
                numToSave = 12;
                return true;
            }else if (two.size() >= 3 && one.size() >= 2) {
                numToSave = 8;
                return true;
            }else if (one.size() >= 3 && six.size() >= 2) {
                numToSave = 15;
                return true;
            }else if (one.size() >= 3 && five.size() >= 2) {
                numToSave = 13;
                return true;
            }else if (one.size() >= 3 && four.size() >= 2) {
                numToSave = 11;
                return true;
            }else if (one.size() >= 3 && three.size() >= 2) {
                numToSave = 9;
                return true;
            }else if (one.size() >= 3 && two.size() >= 2) {
                numToSave = 7;
                return true;
            }

        }else if(category == Yatzy){
            if( one.size() ==5 || two.size() ==5|| three.size() ==5||
                    four.size() ==5|| five.size() ==5|| six.size() ==5){
                return true;
            }
            else if(category == CHANCE){
                return true;
            }
        }
        return false;
    }


    // rulla alla t�rningar
    public int[] rollAll() {
        aDice = new int[] { 0, 0, 0, 0, 0 };
        for (int i = 0; i < aDice.length; i++) {
            rollDice();
            aDice[i] = diceValue;
        }

        Arrays.sort(aDice);
        System.out.println(Arrays.toString(aDice));
        System.out.println();
        return aDice;
    }

    // v�lja hur m�nga som ska rullas
    public void reRollDices() {
        do {
            int rerolla;
            rerolla = inputInt("Hur många tärningar vill du kasta om? (0-5)");
            if (rerolla > 0 && rerolla < 6) {
                int[] reroll = new int[rerolla];
                rerollb = inputInt2("Vilka tärningar vill du kasta om? Välj tärningt,tryck \"Enter\": ");
                reroll[0] = rerollb;

                for (y = 1; y < rerolla; y++) {
                    boolean same = false;
                    do {
                        rerollb = inputInt("Vilka tärningar vill du kasta om?   V�lj en t�rning och tryck \"Enter\": ");
                        for (int z = 0; z < y; z++) {
                            if (rerollb == reroll[z]) {
                                same = true;
                                System.out.println("fel, samma!");
                                break;
                            } else {
                                same = false;
                            }
                        }
                    } while (same);

                    reroll[y] = rerollb;
                }

                for (w = 0; w < rerolla; w++) {

                    if (reroll[w] == 1) {
                        aDice[0] = rollDice();
                    }
                    if (reroll[w] == 2) {
                        aDice[1] = rollDice();
                    }
                    if (reroll[w] == 3) {
                        aDice[2] = rollDice();
                    }
                    if (reroll[w] == 4) {
                        aDice[3] = rollDice();
                    }
                    if (reroll[w] == 5) {
                        aDice[4] = rollDice();
                    }

                }

                rollPerTurn++;
                Arrays.sort(aDice);
                System.out.println(Arrays.toString(aDice));

            }

            if (rerolla >= 6) {
                throw new ArrayIndexOutOfBoundsException("Invalid input! Please choose a number between 1-5!");
            }

        } while ((rollPerTurn < 2) && (rerolla > 0));

    }

    static int inputInt(String Prompt) {
        int result = -1;
        do { // felhantering ---skriv inte in tecken utan 0-5
            try {
                result = Integer.parseInt(input(Prompt).trim());
                if (result > 5) {
                    System.out.println("Du skrev in fel. För stor! ");
                }
                if (result < 0) {
                    System.out.println("Du skrev in fel. För små! ");
                }
            } catch (Exception e) {
                System.out.println("Du skrev in fel. ");
                result = -1;
            }
        } while (result != 0 && result != 1 && result != 2 && result != 3 && result != 4 && result != 5);

        return result;
    }

    static int inputInt2(String Prompt) {
        int result = -1;
        do { // felhantering ---skriv inte in tecken utan 1-5
            try {
                result = Integer.parseInt(input(Prompt).trim());
                if (result > 5) {
                    System.out.println("Du skrev in fel. För stor! ");
                }
                if (result < 0) {
                    System.out.println("Du skrev in fel. För små! ");
                }
            } catch (Exception e) {
                System.out.println("Du skrev in fel. ");
                result = -1;
            }
        } while (result != 1 && result != 2 && result != 3 && result != 4 && result != 5);

        return result;
    }

    static String input(String prompt) {
        String inputLine = "";
        System.out.print(prompt);
        try {
            java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
            java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
            inputLine = inBuffer.readLine();
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        }
        return inputLine;
    }

}