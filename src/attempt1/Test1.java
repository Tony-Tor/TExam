package attempt1;

import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strings = reader.readLine().split(" ");

        long n = Integer.parseInt(strings[0]);
        long s = Integer.parseInt(strings[1]);

        String[] prices = reader.readLine().split(" ");

        int max = 0;
        for(int i = 0; i < n; i++){
            int p = Integer.valueOf(prices[i]);
            if(p==s){
                max = p;
                break;
            }
            if(p>max&&p<=s){
                max = p;
            }
        }

        writer.write(String.valueOf(max));

        reader.close();
        writer.close();
    }
}
