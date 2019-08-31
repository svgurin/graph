public class Main {

    public static void main(String[] args) {
        GraphLibInteger graphLib = new GraphLibInteger(false);
        for (int i = 0; i < 10; i++) {
            graphLib.addEdge(i);
        }
        for (int i = 1; i < 10; i++) {
            graphLib.addVertex(i,i+1);
        }
        for (int i = 0; i < 10; i = i + 2) {
            graphLib.addVertex(i,i+1);
        }
        System.out.println(graphLib.getPath(2,6));
    }
}
