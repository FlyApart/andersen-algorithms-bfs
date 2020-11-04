package com.andersenlab;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private List<Vertex> vertexList;
    private Queue<Vertex> theQueue;;

    public Graph() {
        vertexList = new ArrayList<>();
        theQueue = new LinkedList<>(){};
    }

    public void addVertex(char l) {
        vertexList.add(new Vertex(l));
    }

    public void addEdge(int start, int end) {
       if(checkValues(start) || checkValues(end)) return;
        vertexList.get(start).addNeighboringPeak(vertexList.get(end));
        vertexList.get(end).addNeighboringPeak(vertexList.get(start));
    }

    public void showVisitedVertex(Vertex vertex) {
        System.out.print(vertex);
    }


    public void breadthFirstSearch(int startVertex) {
        if (checkValues(startVertex)) return;

        theQueue.add(vertexList.get(startVertex));
        while (!theQueue.isEmpty()){
            Vertex currentVertex = theQueue.poll();
            currentVertex.setWasVisited(true);
            showVisitedVertex(currentVertex);

            for (Vertex vertex : currentVertex.getNeighboringVertex()) {
                if(!vertex.isWasVisited()){
                    theQueue.add(vertex);
                }
            }
        }
    }

    private boolean checkValues(int a){
        return a > vertexList.size() - 1 || a < 0;
    }

    private static class Vertex {

        public char name;
        public boolean wasVisited;
        public List<Vertex> neighboringVertex;

        public Vertex(char l) {
            name = l;
            wasVisited = false;
            neighboringVertex = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Vertex{"+ name + "}\n";
        }

        public boolean isWasVisited() {
            return wasVisited;
        }

        public void setWasVisited(boolean wasVisited) {
            this.wasVisited = wasVisited;
        }

        public void addNeighboringPeak(Vertex vertex){
            neighboringVertex.add(vertex);
        }

        public List<Vertex> getNeighboringVertex() {
            return neighboringVertex;
        }
    }
}
