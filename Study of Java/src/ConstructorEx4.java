class D{
    public D(){
        System.out.println("생성자A");
    }
    public D(int x){
        System.out.println("매개변수생성자A" + x);
    }
}

class E extends D{
    public E(){
        System.out.println("생성자B");
    }
    public E(int x){
        super(x);
        System.out.println("매개변수생성자B" + x);
    }
}

public class ConstructorEx4 {
    public static void main(String[] args){
        E b = new E(5);
    }
}
/* 실행결과
매개변수생성자A5
매개변수생성자B5
*/