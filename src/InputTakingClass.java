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

        selectedInputDataList[0] = new ArrayList<>(List.of("Name","Average", "SR","AvgPerMatch","AvgOf50"));

        for (int j = 1; j < playerList.size(); j++){
            arrOfStr = playerList.get(j).split(",", 14);

            if(arrOfStr[3].equals("-"))
                arrOfStr[3]= String.valueOf(0);

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

            double runs = Integer.parseInt(arrOfStr[6]), matches = Integer.parseInt(arrOfStr[3]), numberOf50 = Integer.parseInt(arrOfStr[12]);
            double avgPerMatch = runs / matches;
            double avgOf50PerMatch;
            if(numberOf50==0){
                avgOf50PerMatch = 0;
            }
            else{
                avgOf50PerMatch = (numberOf50/matches)*100 ;
            }
            String avgMatchResult = String.format("%.3f", avgPerMatch);
            selectedInputDataList[j].add(avgMatchResult);
            String avg50Result = String.format("%.5f", avgOf50PerMatch);
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
