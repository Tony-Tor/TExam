package attempt1;

import java.io.*;
import java.util.*;

public class Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s1 = reader.readLine().split(" ");
        String[] s2 = reader.readLine().split(" ");

        int sumJoe = Integer.parseInt(s1[0]);
        int billTypes = Integer.parseInt(s1[1]);

        List<Integer> billsList = new ArrayList<>();

        for (int i = 0; i < billTypes; i++){
            int bill = Integer.parseInt(s2[i]);
            billsList.add(bill);
        }

        billsList = billsList.stream().sorted().toList();


        for(int j = billTypes-1; j >=0; j--){
            List<Integer> result = new ArrayList<>();
            int stolen = 0;
            for(int i = j; i >= 0; i--){
                for(int k = 0; k < 2; k++){
                    int bill = billsList.get(i);
                    stolen += bill;
                    if(stolen>sumJoe){
                        stolen -= bill;
                        break;
                    }
                    result.add(bill);
                    if(stolen == sumJoe){
                        result = result.stream().sorted().toList();

                        String res = "";
                        for (int r:result){
                            res += r + " ";
                        }

                        writer.write(result.size() + "\n" + res.trim());

                        reader.close();
                        writer.close();
                        return;
                    }


                }
            }
        }


        writer.write(String.valueOf(-1));

        reader.close();
        writer.close();
    }
}
