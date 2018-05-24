import java.io.File;

public class Main {




    public static void main(String[] argv) throws Exception {




        Game game = new Game();

        if (argv.length==1){
        game.gameWargv(new File("gameGrid.txt"),new File(argv[0]));
            }
            else {

            game.gameWoArgv(new File("gameGrid.txt"));
        }



    }
}
