package ra.functionalinterface;
@FunctionalInterface
public interface IFace {
    void move();
    default void make(){
        System.out.println("helo");
    };
    static void display(String str){
        System.out.println(str);
    }
}
