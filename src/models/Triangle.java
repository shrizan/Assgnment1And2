package models;

public class Triangle {
    private final Vertex[] vertices;

    public Vertex[] getVertices() {
        return vertices;
    }

    public Triangle(Vertex[] vertices) {
        if(vertices.length!=3){
            throw new IllegalStateException("Triangle must have 3 vertices");
        }
        this.vertices = vertices;
    }
}
