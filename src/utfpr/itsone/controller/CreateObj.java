package utfpr.itsone.controller;

import utfpr.itsone.data.GameData;
import utfpr.itsone.model.core.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateObj {
    public CreateObj() {
        create();
    }

    public void create(){
        List<Game> games = Arrays.asList(
                new Game("GOD OF WAR", "Desc"),
                new Game("DARK SOULS", "Project Dark (Working Title) is a brand new dark fantasy RPG designed to completely embrace the concepts of <br>tension in dungeon exploration, fear in encountering enemies, the joy of new discoveries, <br>and a high sense of achievement in progressing. The game is set in a dark fantasy <br>world filled with decadent atmosphere, with a heavy emphasis on<br> player freedom in discovering various tactics and strategies to use in weapon based combat. "),
                new Game("SHADOW OF THE COLOSSUS", " Tales speak of an ancient realm where Colossi roam the majestic landscape. Bound to the land, <br>these creatures hold a key to a mystical power of revival – a power you <br>must obtain to bring a loved one back to life."),
                new Game("DRAGON BALL FIGHTERZ", "After the success of the Xenoverse series, its time to introduce a new classic 2D DRAGON BALL <br>fighting game for this generations consoles. DRAGON BALL FighterZ is born from what makes the <br>DRAGON BALL series so loved and famous: endless spectacular fights with its allpowerful fighters."),
                new Game("NI NO KUNI II: REVENANT KINGDOM", "Re-enter the animated world of Ni no Kuni in the sequel to the role-playing game <br>developed by LEVEL-5. Explore the world and experience the story in an all-new RPG adventure. <br>LEVEL-5 reunites with Yoshiyuki Momose on character design and <br>Music created by Joe Hisaishi in the production of the next Ni no Kuni tale."),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc"),
                new Game("TRUCO", "Desc")
                );
        GameData.getData().addGames(games);
    }
}
