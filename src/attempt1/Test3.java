package attempt1;

import java.io.*;

public class Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strings = reader.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);

        String[] s1 = reader.readLine().concat(" -1").split(" ");
        String[] s2 = reader.readLine().concat(" -1").split(" ");

        int check = 0;
        int pass = 0;
        for (int i = 0; i < n+1; i++){
            int card = Integer.parseInt(s1[i]);
            int current = Integer.parseInt(s2[i]);

            if(pass>current){
                //System.out.println(pass + " " + current + " " + check);
                if(check != 0){
                    writer.write("NO");
                    reader.close();
                    writer.close();
                    return;
                }
            }
            check += current;
            check -= card;
            pass = current;
        }

        writer.write("YES");

        reader.close();
        writer.close();
    }
}
