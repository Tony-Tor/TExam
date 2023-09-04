package attempt1;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> map = new HashMap<>();
        map.put("s",0);
        map.put("h",0);
        map.put("e",0);
        map.put("r",0);
        map.put("i",0);
        map.put("f",0);

        String[] strings = reader.readLine().split("");

        for(String s:strings){
            int i = map.getOrDefault(s,-1);
            if(i!=-1){
                map.put(s, i+1);
            }
        }

        Set<String> keys = map.keySet();

        int min = Integer.MAX_VALUE;
        for(String s:keys){
            int c;
            if(s.equals("f")){
                 c = map.get(s)/2;
            } else {
                c = map.get(s);
            }
            if(c<min){
                min = c;
            }
        }

        writer.write(String.valueOf(min));

        reader.close();
        writer.close();
    }
}
