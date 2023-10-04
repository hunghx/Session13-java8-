import ra.functionalinterface.*;
import ra.functionalinterface.Math;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<Subject> marks = new ArrayList<>();
    public static void main(String[] args) {
        LocalDateTime l1 =LocalDateTime.now();
       // Interface :  Default method và Static method
        MyClass myClass = new MyClass();
        myClass.make();
        IFace.display("hungf");

        // FunctionalInterface : la 1 interface , chỉ có 1 pt trừu tượng
        // khởi tạo tạo đối tượng functional interface bằng lamda
        MyFunction myFunction = (s)->{
            return 0;
        };
        // tính toán 2 số nguyên dựa vào phép toán truyền vào
        Math math = new Math();
        cal(5,6, math::sum,"+");
        cal(5,6, Math::minus,"-");
        Calculator cal3 = (a,b)->a*b;
        cal(5,6,cal3,"*");
        Calculator cal4 = (a,b)->a/b;
        cal(5,6,cal4,"/");

        // Stream - dòng
        // 4 loại functional interface được dựng sẵn
        // Consumer  - nhận vào 1 tham số và không trả về gì hết
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        Consumer<Integer> consumer = (a)->{System.out.println(a);};
        list1.forEach(consumer);
        // Function - nhận vào 1 tham số và trả vè 1 đối tượng khác
        list1.stream().map((a)->a*a).forEach(System.out::println);
        // Supplier - không nhận vào gì nhưng trả về 1 đối tượng
        Random random = new Random();
        List<Integer> list2 = Stream.generate(()->random.nextInt(100)).limit(10)
                .collect(Collectors.toList());
        System.out.println(list2);
        // Predicate - nhận vào 1 tham số và trả về boolean
        List<Integer> rs = list2.stream().filter((number->number>50)).collect(Collectors.toList());
        System.out.println(rs);
        // lọc các phần tử trùng lặp trong danh sách và tính lũy thừa bậc 3 của chúng
        List<Integer> list3 = Arrays.asList(1,3,5,7,9,4,5,6,7);
        List<Integer> rs1= list3.stream().distinct().map(e->e*e*e).sorted().collect(Collectors.toList());
        System.out.println(rs1);
        Year year1 = Year.parse(new Scanner(System.in).nextLine());
        long age = ChronoUnit.YEARS.between(year1,LocalDate.now());
        System.out.println("Age:" + age);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM");
        String dm=new Scanner(System.in).nextLine();
        MonthDay mm = MonthDay.parse(dm,dt);
        System.out.println(DateTimeFormatter.ofPattern("dd - MM").format(mm));



        // Datatime
        // Local - cucj bộ
        LocalDate dateLocal = LocalDate.now();
        System.out.println(dateLocal);
        LocalTime timeLocal = LocalTime.now();
        System.out.println(timeLocal);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // Zoned - múi giờ
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        // lấy ra múi gờ của nhật
        ZoneId zoneIdJapan = ZoneId.of("Asia/Tokyo");
        ZonedDateTime zonedDateTimeTokyo = ZonedDateTime.now(zoneIdJapan);
        System.out.println(zonedDateTimeTokyo);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(" yyyy/MM/dd - HH : mm : ss ");
        System.out.println(dateTimeFormatter.format(zonedDateTimeTokyo)+ zonedDateTimeTokyo.getZone());

        LocalDate bornInPhuc = LocalDate.of(1981,7,24);
        Period period = Period.between(bornInPhuc,dateLocal);
        System.out.println(period);
        LocalDateTime localDateTime1 = localDateTime.minus(10,ChronoUnit.DAYS);
        Duration duration = Duration.between(localDateTime1,localDateTime);
        System.out.println(duration.get(ChronoUnit.SECONDS));
        LocalDateTime l2 = LocalDateTime.now();
        long count= ChronoUnit.MILLIS.between(l1,l2);
        System.out.println("nânoo  "+count);

        Year year = Year.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(bornInPhuc);
        System.out.println(dayOfWeek);


        // Optional

//        Optional<String> optional = Optional.of(null);
        // bai toan  tính điểm trung bình của sinh viên
        Student s1 = new Student("Hùng");
        Student s2 = new Student("Nam");
        marks.add(new Subject(s1,9));
        marks.add(new Subject(s1,8));
        marks.add(new Subject(s1,7));
        // tính điểm trung bình
        Optional<List<Subject>> mark = Optional.ofNullable(getListSubject(s1));
        System.out.println(mark);
        List<Subject> list = mark.orElseThrow(()->new RuntimeException("không thể chuyển đổi gia trị null"));
        double avg = list.stream().map(Subject::getPoint).reduce((double) 0, Double::sum)/list.size();
        System.out.println(avg);
       Optional<Subject> optional = list.stream().filter((sub)->sub.getPoint()==8.6).findAny();
        optional.ifPresent(subject -> System.out.println(subject.getPoint()));

    String [] str = {"hung","nam"};

    }
    public  static void cal(double a, double b, Calculator myFun,String cal){
        System.out.println(" kết quả của phép toán  "+cal+" là :" + myFun.calculator(a,b));
    }
    public static List<Subject> getListSubject(Student s){
        List<Subject> search = marks.stream().filter(sub->sub.getStudent().equals(s)).collect(Collectors.toList());
        if (search.isEmpty()) return null;
        return search;
    }

}