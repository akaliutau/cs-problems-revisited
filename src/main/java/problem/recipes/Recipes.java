package problem.recipes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Recipes {


    static class Record {
        String name;
        int age;
        int salary;

        public Record(String name, int age, int salary){
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString(){
            return "[" + name + ":" + age + ":"+ salary + "]";
        }

    }

    void sortingByMultipleFields() {
        Record r1 = new Record("Alice", 29, 2300);
        Record r2 = new Record("Bobe", 29, 1200);
        Record r3 = new Record("Eve", 33, 9820);
        Comparator<Record> byAge = (o,p) -> o.age - p.age; // youngest first
        Comparator<Record> bySalary = (o,p) -> o.salary - p.salary; // smaller first
        List<Record> lst = new ArrayList<>();
        lst.add(r1);
        lst.add(r2);
        lst.add(r3);
        lst.sort(byAge.thenComparing(bySalary));
        System.out.println(lst);
    }

    public static void main(String[] args) {
        Recipes rec = new Recipes();
        rec.sortingByMultipleFields();
    }
}
