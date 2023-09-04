package attempt1;

import java.io.*;
import java.util.*;

/* test data
        7 13
        2 3 1
        3 3
        1 2 4
        2 1 1
        3 4
        2 3 4
        1 3 4
        3 4
        1 7 3
        1 1 3
        3 7
        3 1
        2 7 4*/

public class Test6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strings = reader.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        for(int i = 0; i < n; i++){
            Spirit spirit = new Spirit();
            spirit.id = i + 1;
        }

        for(int i = 0; i < m; i++){
            String[] s = reader.readLine().split(" ");
            int question = Integer.parseInt(s[0]);
            String res = "";
            switch (question){
                case 1:
                    question1(
                            Integer.parseInt(s[1]),
                            Integer.parseInt(s[2])
                    );
                    break;
                case 2:
                    res = question2(
                            Integer.parseInt(s[1]),
                            Integer.parseInt(s[2])
                    ) + "\n";
                    break;
                case 3:
                    res = question3(
                            Integer.parseInt(s[1])
                    ) + "\n";
                    break;
            }
            writer.write(res);
        }

        int i = 1;
        for(Band band:Band.bands){
            System.out.print("Band #"+ i++ +" { ");
            for(Spirit spirit:band.spirits.stream().sorted(Comparator.comparingInt(o -> o.id)).toList()){
                System.out.print(spirit.id + " ");
            }
            System.out.println("}");
        }

        reader.close();
        writer.close();
    }

    public static void question1(int s1, int s2){

        Spirit spirit1 = Spirit.spirits.get(s1-1);
        Spirit spirit2 = Spirit.spirits.get(s2-1);

        if(spirit1.band != spirit2.band){
            Band b = new Band();
            Band.bands.remove(spirit1.band);
            Band.bands.remove(spirit2.band);
            for(Spirit spirit:spirit1.band.spirits){
                b.addSpirit(spirit);
            }
            for(Spirit spirit:spirit2.band.spirits){
                b.addSpirit(spirit);
            }

        }
    }

    public static String question2(int a, int b){
        Spirit s1 = Spirit.spirits.get(a-1);
        Spirit s2 = Spirit.spirits.get(b-1);
        if(s1.band==s2.band){
            return "YES";
        } else {
            return "NO";
        }
    }

    public static String question3(int a){
        Spirit s1 = Spirit.spirits.get(a-1);
        return s1.bands + "";
    }

    private static class Band{
        public static Set<Band> bands = new HashSet<>();
        List<Spirit> spirits = new ArrayList<>();

        public Band() {
            bands.add(this);
        }

        public void addSpirit(Spirit spirit){
            spirit.addBand(this);
            spirits.add(spirit);
        }
    }

    private static class Spirit{
        public static List<Spirit> spirits = new ArrayList<>();
        int bands = 0;
        int id;
        Band band = null;

        public Spirit() {
            spirits.add(this);
            Band b = new Band();
            b.addSpirit(this);
        }

        public void addBand(Band band){
            bands++;
            this.band = band;
        }
    }
}
