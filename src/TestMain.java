import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMain
{
    ArrayList<String>[] selectedInputDataList = new ArrayList[2450];
    public static void main(String[] args) throws IOException {
        TestMain t = new TestMain();
        t.setValue();
        t.getOutPut();
    }

    public void setValue() throws IOException {
        InputTakingClass inputTakingClass = new InputTakingClass();
        selectedInputDataList = inputTakingClass.getInput();
    }

    public void getOutPut() throws IOException {
        KmeansCalculator k = new KmeansCalculator(selectedInputDataList);
        //k.printOutput();
        k.getCategoryOfPlayers();
    }

}