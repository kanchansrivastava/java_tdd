package shape;

public class Rectangle {
    int length;
    int breadth;

    public Rectangle (int length, int breadth){
        this.length = length;
        this.breadth = breadth;

    }

    public Rectangle(int length){
        this.length = length;
        this.breadth = length;
    }

    public int area() {
        return length * breadth;
    }

    public int perimeter() {
        return 2 * (length + breadth);
    }

}


