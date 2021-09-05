import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class KmeansCalculator {
    ArrayList<String>[] selectedInputDataList = new ArrayList[2450];
    ArrayList<ArrayList<String>> category1Batsman = new ArrayList();
    ArrayList<ArrayList<String>> category2Batsman = new ArrayList();
    ArrayList<ArrayList<String>> category3Batsman = new ArrayList();
    ArrayList<ArrayList<String>> category4Batsman = new ArrayList();
    Random rand = new Random();
    double eps=0.01;
    UpdateCentroidValue updateCentroid = new UpdateCentroidValue();
    ArrayList<String>[] randomList = new ArrayList[5],tempCentroidValue = new ArrayList[4],randomList2 = new ArrayList[5];
    int c1=0,c2=0,c3=0,c4=0;
    double[] tSum = new double[4];
    double[] tSum2 = new double[4];
    double[] tSum3 = new double[4];
    double[] tSum4 = new double[4];

    public KmeansCalculator(ArrayList<String>[] selectedInputDataList){
        this.selectedInputDataList = selectedInputDataList;
    }

    public void getCategoryOfPlayers() throws IOException
    {
        int  checker=0;
        for(int i=0;i<4;i++)
        {
            tSum[i]=0;
            tSum2[i]=0;
            tSum3[i]=0;
            tSum4[i]=0;
        }

        createRandomList();
        checker=kMeansCalculation(checker);
        csvFileMaking();
        printOutput(checker);
    }


    public int kMeansCalculation(int checker){
        while(true){
            category1Batsman.clear();
            category2Batsman.clear();
            category3Batsman.clear();
            category4Batsman.clear();

            for(int j=1; j<selectedInputDataList.length;j++){
                double minDistance = getMin(
                        euclideanDistanceCalculator(randomList[1],selectedInputDataList[j]),
                        euclideanDistanceCalculator(randomList[2],selectedInputDataList[j]),
                        euclideanDistanceCalculator(randomList[3],selectedInputDataList[j]),
                        euclideanDistanceCalculator(randomList[4],selectedInputDataList[j])
                );

                if(minDistance==euclideanDistanceCalculator(randomList[1],selectedInputDataList[j]))
                {
                    tSum = updateCentroid.addSum(selectedInputDataList[j],tSum);
                    c1++;
                    category1Batsman.add(selectedInputDataList[j]);
                }

                else if(minDistance==euclideanDistanceCalculator(randomList[2],selectedInputDataList[j]))
                {
                    tSum2 = updateCentroid.addSum(selectedInputDataList[j],tSum2);
                    c2++;
                    category2Batsman.add(selectedInputDataList[j]);
                }

                else if(minDistance==euclideanDistanceCalculator(randomList[3],selectedInputDataList[j]))
                {
                    tSum3 = updateCentroid.addSum(selectedInputDataList[j],tSum3);
                    c3++;
                    category3Batsman.add(selectedInputDataList[j]);
                }

                else if(minDistance==euclideanDistanceCalculator(randomList[4],selectedInputDataList[j]))
                {
                    tSum4 = updateCentroid.addSum(selectedInputDataList[j],tSum4);
                    c4++;
                    category4Batsman.add(selectedInputDataList[j]);
                }
            }

            for(int m=0;m<randomList.length-1;m++){
                tempCentroidValue[m]=randomList[m+1];
            }

            randomList[1]=updateCentroid.averageCalculator(tSum,c1,randomList[1]);
            randomList[2]=updateCentroid.averageCalculator(tSum2,c2, randomList[2]);
            randomList[3]=updateCentroid.averageCalculator(tSum3,c3, randomList[3]);
            randomList[4]=updateCentroid.averageCalculator(tSum4,c4, randomList[4]);

            if(euclideanDistanceCalculator(randomList[1],tempCentroidValue[0])<eps && euclideanDistanceCalculator(randomList[2],tempCentroidValue[1])<eps
                    && euclideanDistanceCalculator(randomList[3],tempCentroidValue[2])<eps && euclideanDistanceCalculator(randomList[4],tempCentroidValue[3])<eps){
                break;
            }

            checker++;
        }
        return checker;
    }


    public void arrayListToCSV(ArrayList<ArrayList<String>> list, int num) throws IOException {
        File file = new File("src/category"+num+".csv");
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

    public void csvFileMaking() throws IOException {
        arrayListToCSV(category1Batsman,1);
        arrayListToCSV(category2Batsman,2);
        arrayListToCSV(category3Batsman,3);
        arrayListToCSV(category4Batsman,4);

    }

    public void printOutput(int checker){
        System.out.println("NumberOfLoopRotation = "+checker);
        System.out.println("Number of Data Point in Category1 = "+ category1Batsman.size());
        System.out.println("Number of Data Point in Category2 = "+ category2Batsman.size());
        System.out.println("Number of Data Point in Category3 = "+ category3Batsman.size());
        System.out.println("Number of Data Point in Category4 = "+ category4Batsman.size()+"\n");

        for(ArrayList<String> s:randomList2 ){
            System.out.println("RandomPlayerInfo: "+s);
        }

        System.out.println("\n");

        for(ArrayList<String> s:randomList ){
            System.out.println("UpdatedCentroidPlayerInfo: "+s);
        }

        System.out.println("\n");

        for(ArrayList<String> s: category1Batsman){
            System.out.println("Category1 Player: "+s);
        }

        for(ArrayList<String> s: category2Batsman){
            System.out.println("Category2 Player: "+s);
        }

        for(ArrayList<String> s: category3Batsman){
            System.out.println("Category3 Player: "+s);
        }

        for(ArrayList<String> s: category4Batsman){
            System.out.println("Category4 Player: "+s);
        }
    }



    public void createRandomList(){
        int dataLength = selectedInputDataList.length;
        randomList[0]=selectedInputDataList[0];
        randomList2[0]=selectedInputDataList[0];
        for (int i=1;i<randomList.length;i++){
            int randomIndex= rand.nextInt(dataLength);
            if(randomIndex==0)
                randomIndex=1;
            randomList[i]= selectedInputDataList[randomIndex];
            randomList2[i]= selectedInputDataList[randomIndex];

        }
    }

    public double getMin(double d1, double d2, double d3,double d4)
    {
        double numbers[]={d1,d2,d3,d4};

        double minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    public double euclideanDistanceCalculator(ArrayList<String> randomData, ArrayList<String> selectedData)
    {
        double distance=0;
        distance= Math.sqrt(  squareOfDifference(Double.parseDouble(randomData.get(1)),Double.parseDouble(selectedData.get(1))) +
                squareOfDifference(Double.parseDouble(randomData.get(2)),Double.parseDouble(selectedData.get(2)))
                + squareOfDifference(Double.parseDouble(randomData.get(3)),Double.parseDouble(selectedData.get(3))) +
                        squareOfDifference(Double.parseDouble(randomData.get(4)),Double.parseDouble(selectedData.get(4))));
        return  distance;
    }

    public double squareOfDifference( double x, double y)
    {
        double distance = 0;
        distance=(x-y)*(x-y);
        return  distance;
    }


    /*public void printOutput(){
        for(ArrayList<String> s:selectedInputDataList ){
            System.out.println(s);
        }
    }*/

}
