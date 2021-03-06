import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputTakingClass {
    ArrayList<String>[] inputDataList = new ArrayList[2450];
    ArrayList<String>[] selectedInputDataList = new ArrayList[2450];
    ArrayList<String> playerList = new ArrayList();
    String[] arrOfStr;
    Scanner sc = new Scanner(System.in);
    String string, a =" ";

    public ArrayList<String>[] getInput() throws IOException, NumberFormatException
    {
        for (int i = 0; i < 2450; i++) {
            inputDataList[i] = new ArrayList<String>();
            selectedInputDataList[i] = new ArrayList<String>();
        }

        File f = new File("data/odi.csv");
        Scanner input = new Scanner(f);

        while (input.hasNextLine())
        {
            string = input.nextLine();
            playerList.add(string);
        }

        selectedInputDataList[0] = new ArrayList<>(List.of("Name","Average", "StrikeRate","AveragePerInnings","PercentageOfScoring50"));

        for (int j = 1; j < playerList.size(); j++){
            arrOfStr = playerList.get(j).split(",", 14);

            if(arrOfStr[4].equals("-"))
                arrOfStr[4]= String.valueOf(0);

            if(arrOfStr[6].equals("-"))
                arrOfStr[6]= String.valueOf(0);

            if(arrOfStr[8].equals("-")){
                arrOfStr[8]= String.valueOf(0);
            }

            if(arrOfStr[10].equals("-"))
                arrOfStr[10]= String.valueOf(0);

            if(arrOfStr[12].equals("-"))
                arrOfStr[12]= String.valueOf(0);

            selectedInputDataList[j].add(arrOfStr[1]);
            selectedInputDataList[j].add(arrOfStr[8]);
            selectedInputDataList[j].add(arrOfStr[10]);

            double runs = Integer.parseInt(arrOfStr[6]), innings = Integer.parseInt(arrOfStr[4]), numberOf50 = Integer.parseInt(arrOfStr[12]);
            double avgPerInnings = runs / innings;
            double percentageOfScoring50;
            if(numberOf50==0){
                percentageOfScoring50 = 0;
            }
            else{
                percentageOfScoring50 = (numberOf50/innings)*100 ;
            }
            String avgMatchResult = String.format("%.3f", avgPerInnings);
            selectedInputDataList[j].add(avgMatchResult);
            String avg50Result = String.format("%.5f", percentageOfScoring50);
            selectedInputDataList[j].add(avg50Result);
        }

        makingSelectedValueCSVFile(selectedInputDataList);

        return selectedInputDataList;
    }

    public void printOutput(){
        for(ArrayList<String> s:selectedInputDataList ){
            System.out.println(s);
        }
    }

    public void makingSelectedValueCSVFile(ArrayList<String>[] selectedInputDataList) throws IOException {
        arrayListToCSV(selectedInputDataList);
    }

    public void arrayListToCSV(ArrayList<String>[] list) throws IOException {
        File file = new File("src/selectedValue.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for(ArrayList<String> s: list)
        {
            bw.write(String.valueOf(s));
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


}
