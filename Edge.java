public class Edge<T> {
    private final T destination;
    private int weight;
    private String name;

    public Edge(T destination, int weight, String name){
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    public T getDestination(){
        return this.destination;
    }

    public int getWeight(){
        return this.weight;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return "Edge( " + 
            "destination: " + this.destination +
            ")";
    }
}
