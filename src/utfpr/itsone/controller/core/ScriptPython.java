package utfpr.itsone.controller.core;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScriptPython {
    ArrayList<Integer> game_id;

    public ArrayList runScript(int value) throws IOException, InterruptedException {
        String s = System.getProperty("user.dir");
        game_id = new ArrayList<>();
        String pythonScriptPath = s + "\\SistemaRecGames2.py";
        String[] cmd = new String[3];
        cmd[0] = "python";
        cmd[1] = pythonScriptPath;
        cmd[2] = String.valueOf(value);

        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(cmd);

        p.waitFor();

        File file = new File("./result_file");
        Scanner in = null;
        try {
            in = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        int i = 0;
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner src = new Scanner(currentLine);
            src.useDelimiter("[;]+");
            if(++i>2)
                game_id.add(Integer.valueOf(src.next()));
        }
        /*BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        int i = 0;
        while((line = bfr.readLine()) != null) {
            Scanner src = new Scanner(line);
            src.useDelimiter(",| ");
            if(++i>3)
                game_id.add(Integer.valueOf(src.next()));
            System.out.println(line);
        }*/
        return game_id;
    }
}
