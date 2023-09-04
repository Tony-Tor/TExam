package attempt1;

import java.io.*;
import java.util.*;

public class Test5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = reader.readLine().split(" ");

        int cityCount = Integer.parseInt(s[0]);
        int roadCount = Integer.parseInt(s[1]);

        for(int i = 0;i < cityCount; i++){
            new City();
        }

        int maxCost = 0;
        for(int i = 0;i < roadCount; i++){
            String[] data = reader.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            int cost = Integer.parseInt(data[2]);
            if(cost>maxCost)maxCost = cost;
            connectCity(a, b, cost);
        }

        int startState = City.pass(City.cities, 0);

        int start = 0;
        int end = maxCost;
        int middle = end/2;
        int countState;
        while(end-start>1){
            countState = City.pass(City.cities, middle);
            if(countState>startState){
                end = middle;
                middle = (start + end)/2;
            } else {
                start = middle;
                middle = (start + end)/2;
            }
        }

        writer.write("" + middle);

        reader.close();
        writer.close();
    }

    public static void connectCity(int a, int b, int cost){
        City city1 = City.cities.get(a-1);
        City city2 = City.cities.get(b-1);

        if(city1!=city2){
            city1.neighbors.add(city2);
            city1.roads.add(cost);
            city2.neighbors.add(city1);
            city2.roads.add(cost);
        } else {
            city1.neighbors.add(city2);
            city1.roads.add(cost);
        }
    }

    public static class City {
        public static List<City> cities = new ArrayList<>();
        private List<City> neighbors = new ArrayList<>();;
        private List<Integer> roads = new ArrayList<>();;

        protected City() {
            cities.add(this);
        }

        public static int pass(List<City> cities, int maxCost){
            List<City> passed = new ArrayList<>();
            int i = 0;
            for(City city: cities){
                if(!passed.contains(city)){
                    passGraph(city, passed, maxCost);
                    i++;
                }
            }
            return i;
        }

        public static void passGraph(City city, List<City> passed, int maxCost){
            Deque<City> stack = new LinkedList<>();
            stack.push(city);
            while(stack.size()!=0){
                city = stack.peek();
                if(!passed.contains(city)){
                    passed.add(city);
                }
                boolean hasNeighbors = false;
                for(int i = 0; i < city.neighbors.size(); i++){
                    City n = city.neighbors.get(i);
                    if(city.roads.get(i) <= maxCost)continue;
                    if(!passed.contains(n)){
                        stack.push(n);
                        hasNeighbors = true;
                        break;
                    }
                }
                if(!hasNeighbors)stack.pop();
            }
        }
    }
}
