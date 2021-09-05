import java.util.ArrayList;
import java.util.List;

public class UpdateCentroidValue {
    public double[] addSum(ArrayList<String> playerData, double[] tSum)
    {
        tSum[0] += Double.parseDouble(playerData.get(1));
        tSum[1] += Double.parseDouble(playerData.get(2));
        tSum[2] += Double.parseDouble(playerData.get(3));
        tSum[3] += Double.parseDouble(playerData.get(4));

        return tSum;
    }



    public ArrayList<String> averageCalculator(double[] tSum, int counter, ArrayList<String> randomListValue)
    {
        double[] avg=new double[4];
        avg[0]=tSum[0]/counter;
        avg[1]=tSum[1]/counter;
        avg[2]=tSum[2]/counter;
        avg[3]=tSum[3]/counter;

        ArrayList<String> c =new ArrayList<>(List.of(randomListValue.get(0),String.valueOf(avg[0]),String.valueOf(avg[1]),
                String.valueOf(avg[2]),String.valueOf(avg[3])));
        return  c;
    }
}
