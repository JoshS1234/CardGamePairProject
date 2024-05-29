package org.example.ASCIIArt;

public class DrawDealer {

    public static final String colourReset = "\u001b[0m";

    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[33m";
    public static final String blackBG = "\u001B[40m";
    public static final String whiteBG = "\u001B[47m";

    public static void drawDealer() {
        System.out.println(black + blackBG + "                             " + whiteBG + "##       %%------------%%       ##" + blackBG + "                           " + colourReset);
        System.out.println(red + blackBG + "   ****      *   *          " + whiteBG + black + "##       %%--------------%%       ##" + blackBG + red + "           *       ****   " + colourReset);
        System.out.println(red + blackBG + "   *         **  *        " + whiteBG + black +"##      %%%%%%:  %%%%%  :%%%%%%%    ##" + blackBG + red + "          * *      *  *   " + colourReset);
        System.out.println(red + blackBG + "   ****      * * *       " + whiteBG + black +"##     %%%%%%%:    %%%   :%%%%%%%%     ##" + blackBG + red + "       *   *     ****   " + colourReset);
        System.out.println(red + blackBG + "      *      *  **      " + whiteBG + black +"##      %%%%%%%%:    %   :%%%%%%%%%      ##" + blackBG + red + "      * * *     *      " + colourReset);
        System.out.println(red + blackBG + "   ****      *   *     " + whiteBG + black +"##       %%%%%%%%:        :%%%%%%%%%       ##" + blackBG + red + "     *   *     *      " + colourReset);
        System.out.println(black + blackBG + "                     " + whiteBG + "##         @@@@@@@@@@@#---#@@@@@@@@@@@         ##" + blackBG + "                    " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@#---#@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@@%%@@@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@@%%@@@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@@%%@@@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@@%%@@@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + blackBG + "                    " + whiteBG + "##          @@@@@@@@@@@@%%@@@@@@@@@@@@@          ##" + blackBG + "                   " + colourReset);
        System.out.println(black + whiteBG + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + colourReset);
    }
}
