{\rtf1\ansi\ansicpg1252\cocoartf2821
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww29200\viewh16020\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import java.util.concurrent.TimeUnit;\
import java.lang.Math;\
\
/**\
 * Represents a horse participating in the race.\
 */\
class Horse\
\{\
    private String horseName;\
    private String horseSymbol; // CHANGED to String\
    private int distanceTravelled;\
    private boolean hasFallen;\
    private double horseConfidence;\
\
    public Horse(String horseSymbol, String horseName, double horseConfidence)\
    \{\
        this.horseSymbol = horseSymbol;\
        this.horseName = horseName;\
        setConfidence(horseConfidence);\
        this.distanceTravelled = 0;\
        this.hasFallen = false;\
    \}\
\
    public void fall()\
    \{\
        this.hasFallen = true;\
    \}\
\
    public double getConfidence()\
    \{\
        return this.horseConfidence;\
    \}\
\
    public int getDistanceTravelled()\
    \{\
        return this.distanceTravelled;\
    \}\
\
    public String getName()\
    \{\
        return this.horseName;\
    \}\
\
    public String getSymbol()\
    \{\
        return this.horseSymbol;\
    \}\
\
    public void goBackToStart()\
    \{\
        this.distanceTravelled = 0;\
        this.hasFallen = false;\
    \}\
\
    public boolean hasFallen()\
    \{\
        return this.hasFallen;\
    \}\
\
    public void moveForward()\
    \{\
        this.distanceTravelled += 1;\
    \}\
\
    public void setConfidence(double newConfidence)\
    \{\
        if (newConfidence < 0.0) \{\
            this.horseConfidence = 0.0;\
        \} else if (newConfidence > 1.0) \{\
            this.horseConfidence = 1.0;\
        \} else \{\
            this.horseConfidence = newConfidence;\
        \}\
    \}\
\
    public void setSymbol(String newSymbol)\
    \{\
        this.horseSymbol = newSymbol;\
    \}\
\}\
\
/**\
 * A three-horse race simulation.\
 */\
class Race\
\{\
    private int raceLength;\
    private Horse lane1Horse;\
    private Horse lane2Horse;\
    private Horse lane3Horse;\
\
    public Race(int distance)\
    \{\
        raceLength = distance;\
        lane1Horse = null;\
        lane2Horse = null;\
        lane3Horse = null;\
    \}\
\
    public void addHorse(Horse theHorse, int laneNumber)\
    \{\
        if (laneNumber == 1)\
        \{\
            lane1Horse = theHorse;\
        \}\
        else if (laneNumber == 2)\
        \{\
            lane2Horse = theHorse;\
        \}\
        else if (laneNumber == 3)\
        \{\
            lane3Horse = theHorse;\
        \}\
        else\
        \{\
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane.");\
        \}\
    \}\
\
    public void startRace()\
    \{\
        boolean finished = false;\
        Horse winner = null;\
\
        if (lane1Horse != null) lane1Horse.goBackToStart();\
        if (lane2Horse != null) lane2Horse.goBackToStart();\
        if (lane3Horse != null) lane3Horse.goBackToStart();\
\
        while (!finished)\
        \{\
            if (lane1Horse != null) moveHorse(lane1Horse);\
            if (lane2Horse != null) moveHorse(lane2Horse);\
            if (lane3Horse != null) moveHorse(lane3Horse);\
\
            printRace();\
\
            if (lane1Horse != null && raceWonBy(lane1Horse)) \{\
                winner = lane1Horse;\
                finished = true;\
            \}\
            else if (lane2Horse != null && raceWonBy(lane2Horse)) \{\
                winner = lane2Horse;\
                finished = true;\
            \}\
            else if (lane3Horse != null && raceWonBy(lane3Horse)) \{\
                winner = lane3Horse;\
                finished = true;\
            \}\
\
            try \{\
                TimeUnit.MILLISECONDS.sleep(100);\
            \} catch (Exception e) \{\
                System.out.println("Sleep interrupted");\
            \}\
        \}\
\
        if (winner != null) \{\
            System.out.println("\\nAnd the winner is\'85 " + winner.getName() + "!");\
        \}\
        else \{\
            System.out.println("No winner!");\
        \}\
    \}\
\
    private void moveHorse(Horse theHorse)\
    \{\
        if (!theHorse.hasFallen())\
        \{\
            if (Math.random() < theHorse.getConfidence())\
            \{\
                theHorse.moveForward();\
            \}\
\
            if (Math.random() < (0.1 * theHorse.getConfidence() * theHorse.getConfidence()))\
            \{\
                theHorse.fall();\
            \}\
        \}\
    \}\
\
    private boolean raceWonBy(Horse theHorse)\
    \{\
        return theHorse.getDistanceTravelled() == raceLength;\
    \}\
\
    private void printRace()\
    \{\
        System.out.print('\\u000C');  // Clear terminal\
\
        multiplePrint('=', raceLength + 3);\
        System.out.println();\
\
        if (lane1Horse != null) \{\
            printLane(lane1Horse);\
            System.out.println();\
        \}\
\
        if (lane2Horse != null) \{\
            printLane(lane2Horse);\
            System.out.println();\
        \}\
\
        if (lane3Horse != null) \{\
            printLane(lane3Horse);\
            System.out.println();\
        \}\
\
        multiplePrint('=', raceLength + 3);\
        System.out.println();\
    \}\
\
    private void printLane(Horse theHorse)\
    \{\
        int spacesBefore = theHorse.getDistanceTravelled();\
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();\
\
        System.out.print('|');\
        multiplePrint(' ', spacesBefore);\
\
        if (theHorse.hasFallen())\
        \{\
            System.out.print('\uc0\u10060 ');\
        \}\
        else\
        \{\
            System.out.print(theHorse.getSymbol());\
        \}\
\
        multiplePrint(' ', spacesAfter);\
        System.out.print('|');\
    \}\
\
    private void multiplePrint(char aChar, int times)\
    \{\
        for (int i = 0; i < times; i++)\
        \{\
            System.out.print(aChar);\
        \}\
    \}\
\}\
\
/**\
 * Test class to demonstrate Horse and Race functionality.\
 */\
public class HorseTest\
\{\
    public static void main(String[] args)\
    \{\
        // Create three horses\
        Horse ferrari = new Horse("\uc0\u55356 \u57294 ", "Ferrari", 0.9);\
        Horse porsche = new Horse("\uc0\u55357 \u56983 ", "Porsche", 0.85);\
        Horse audi = new Horse("\uc0\u55357 \u56985 ", "Audi", 0.8);\
\
        // Print initial info\
        System.out.println("Initial Status:");\
        System.out.println(ferrari.getName() + " symbol: " + ferrari.getSymbol() + ", confidence: " + ferrari.getConfidence());\
        System.out.println(porsche.getName() + " symbol: " + porsche.getSymbol() + ", confidence: " + porsche.getConfidence());\
        System.out.println(audi.getName() + " symbol: " + audi.getSymbol() + ", confidence: " + audi.getConfidence());\
        System.out.println();\
\
        // Full race\
        System.out.println("\\nStarting the full race:");\
        Race race = new Race(15);\
        race.addHorse(ferrari, 1);\
        race.addHorse(porsche, 2);\
        race.addHorse(audi, 3);\
\
        race.startRace();\
    \}\
\}\
}