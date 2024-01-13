package programmers.BfsDfs.LevelThree;

import java.util.*;

public class Travel {

    public static void main(String[] args) {


        String[] solution = solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
        System.out.println("solution = " + Arrays.toString(solution));
    }

    static boolean[] isVisited;
    static List<String> output;

    static List<Ticket> tickets;

    /**
     * 시작은 ICN 부터
     */
        public static String[] solution(String[][] tk) {
            tickets = new ArrayList<>();
            isVisited = new boolean[tk.length];
            for (String[] strings : tk) {
                Ticket ticket = new Ticket(strings[0],strings[1]);
                tickets.add(ticket);
            }
            output = new ArrayList<>();

            dfs(0,"ICN","ICN",tickets.size());
            Collections.sort(output);
            System.out.println("output = " + output);

            return output.get(0).split(" ");
        }

    private static void dfs(int depth, String now, String path, int r) {
            if (depth == r){
                output.add(path);
            }
        for (int i = 0 ;  i < r ; i++){
            if (!isVisited[i] && now.equals(tickets.get(i).start)){
                isVisited[i]= true;
                Ticket nowTicket = tickets.get(i);
                dfs(depth+1,nowTicket.end,path +" "+ nowTicket.end , r);
                isVisited[i] = false;
            }
        }
    }

    public static class Ticket{
        String start;
        String end;

        public Ticket(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
}
