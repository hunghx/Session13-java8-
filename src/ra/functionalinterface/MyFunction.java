package ra.functionalinterface;

@FunctionalInterface // để phát hiện lỗi nếu nó ko đúng điều kiẹn của 1 functional interface
public interface MyFunction{
   int display(String str); // đây là 1 hàm có dạng gì : ko return , ko có tham số
   default int get(){
       return 0;
   };
}
