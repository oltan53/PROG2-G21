import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class ListGraph<T> implements Graph<T>{
    private final Map<T, Set<Edge<T>>> nodes = new HashMap<>();
    
    //nodes är en HashMap, av alla noder (T). Alla T har ett Set av edges (Bågar,kopplingar). T är en instans av en stad(nod)

    public void add (T something){
        nodes.putIfAbsent(something, new HashSet<>());
    }

    public void remove (T node){
        if (nodes.containsKey(node)){
        ArrayList <T> destinationsArrayList = new ArrayList<>();
        ArrayList <Edge<T>> edgesToRemove = new ArrayList<>();
            for(Edge<T> edge : nodes.get(node)){
                edgesToRemove.add(edge);
                for (T destination : nodes.keySet()){
                    if (edge.getDestination().equals(destination)){
                        destinationsArrayList.add(destination);
                    }
                }
            }
        nodes.remove(node);
        for (T destination : destinationsArrayList){
            for (Edge<T> edgeToRemove : edgesToRemove){
            if (nodes.get(destination).contains(edgeToRemove)){
                nodes.get(destination).remove(edgeToRemove);
            }
            }
        }
    }  
        else {
            throw new NoSuchElementException("Det finns ingen objekt meddet namnet!");
        }   //måste ta bort edges
    }

    public void connect(T node1, T node2, String name, int weight){
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2)){
            throw new NoSuchElementException("något av objekten du skrivit in finns ej!");
        }
        else if (weight < 0){
            throw new IllegalArgumentException("Vikten får ej vara negativ!");
        }
        else if (getEdgeBetween(node1, node2) != null){
            throw new IllegalStateException("det fåt bara finnas en koppling mellan två noder!");
        }
        add(node1);
        add(node2);

        Set<Edge<T>> aEdges = nodes.get(node1);
        Set<Edge<T>> bEdges = nodes.get(node2);

        aEdges.add(new Edge<T>(node2, weight, name));
        bEdges.add(new Edge<T>(node1, weight, name));
        
    }

    public void disconnect(T node1, T node2){
        if(!nodes.containsKey(node1) || !nodes.containsKey(node2)){
            throw new NoSuchElementException("Något eller båda av de två objekten du angivit finns ej!");
        }else if(getEdgeBetween(node1, node2) == null){
            throw new IllegalStateException("Det finns ingen koppling mellan dessa två noder!");
        } 
        Edge<T> edgeToDisconnect = getEdgeBetween(node1, node2);
        Edge<T> edgeToDisconnect2 = getEdgeBetween(node2, node1);
        nodes.get(node1).remove(edgeToDisconnect);
        nodes.get(node2).remove(edgeToDisconnect2);
    }

    public Set<T> getNodes(){
        return nodes.keySet();
    }

    public void setConnectionWeight(T node1, T node2, int weight) {
        throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
    }

    public boolean pathExists(T from, T to) {
       if(!nodes.containsKey(to) || !nodes.containsKey(from)){
        return false;
       }
        
        Set<T> haveBeenTo = new HashSet<>();
        depthFirstSearch(from, to, haveBeenTo);
        return haveBeenTo.contains(to);
    }
    
    private void depthFirstSearch(T from, T to , Set<T> haveBeenTo){
        haveBeenTo.add(from);
        if (from.equals(to)){
            return;
        } 
        
        for (Edge<T> edges : nodes.get(from)){
            if (!haveBeenTo.contains(edges.getDestination())){
                depthFirstSearch(edges.getDestination() , to , haveBeenTo);
            }
        }
    }
    

        // lägg till noden vi varit i, i listan av besökta noder----
        // kolla om noden vi är i nu är den noden vi vill till och om det är det så avbryter vi----

        //loopa igenom alla Edges i den noden vi nu befinner oss i----
        //kolla om de kopplingarna vi loopar igenom innehåller en nod vi varit i----
        //om noden inte finns i våran samling av besökta noder skall vi genomföra allt en gång till (rekursivt)
        //detta med den noden som kopplingen vi inte unersökt går till

    public Edge<T> getEdgeBetween(T node1, T node2){
        
        if(!nodes.containsKey(node2) || !nodes.containsKey(node1)){
            throw new NoSuchElementException("Något av de objekten du skickat in finns inte!");
        }
        Edge<T> returnValue = null;
        for (Edge<T> element : nodes.get(node1)){
            if (element.getDestination().equals(node2)){
                returnValue = element;
            }
        }
        if (returnValue == null){
            return null;
        }
        else{
            return returnValue;
        }
    }

    public Collection<Edge<T>> getEdgesFrom(T node) {
        if (!nodes.containsKey(node)){
            throw new NoSuchElementException("inget sådant objekt finns!");
        }
        return nodes.get(node);
    }

    @Override
    public List<Edge<T>> getPath(T from, T to) {
        
    }

    public String toString(){
        
    }

}