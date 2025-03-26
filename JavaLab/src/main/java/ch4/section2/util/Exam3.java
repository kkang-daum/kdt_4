package ch4.section2.util;

import java.util.*;

public class Exam3 {
    public static void main(String[] args) {

        //클래스의 타입을 표현하면서 <클래스명> 으로 지정하는 기법...제네릭(generic)
        //ArrayList 에 <String> 타입의 데이터를 담을거야.. 선언..
        ArrayList<String> list = new ArrayList();
        list.add("뉴욕");
        list.add("서울");
        list.add("런던");
        list.add("서울");

        list.add(1, "LA");

        for(String str : list){
            System.out.println(str);
        }

        //첫번째 위치..
        System.out.println(list.indexOf("서울"));//2
        System.out.println(list.lastIndexOf("서울"));//4
        System.out.println(list.contains("LA"));

        //set...............list 와 다르게.. 중복데이터 허용하지 않는다..
        HashSet<String> set = new HashSet<>();
        set.add("서울");
        set.add("뉴욕");
        set.add("서울");
        for(String str : set){
            System.out.println(str);
        }
        //서울
        //뉴욕

        //<String, String> : 키 - string, 값 - string
        HashMap<String, String> map = new HashMap<>();
        map.put("one", "서울");
        map.put("two", "뉴욕");

        System.out.println(list.get(0));
        System.out.println(map.get("one"));

        //모든 키 획득..
        System.out.println(map.keySet());
        //모든 데이터
        System.out.println(map.values());

        System.out.println(map.containsKey("one"));
        System.out.println(map.containsValue("서울"));

        //Entry : 키-값.
        for(Map.Entry<String, String> entry: map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //List, Set, Map
        //순차적으로 모두 핸들링하고 싶을때..
        //for loop...............
        //iterator 로 핸들링 해도 된다..
        //모든 collection 에서 지원하는 기능..

        //모든 collection type 의 데이터를 동일 api 로...
        //데이터를 순차적으로 이용하면서 삭제 기능까지 제공한다..
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){//있니?
            String str = iterator.next();//하냐 줘...
            System.out.println(str);
        }

    }
}
