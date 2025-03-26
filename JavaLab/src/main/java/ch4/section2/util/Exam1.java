package ch4.section2.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exam1 {
    public static void main(String[] args) {
        //시스템의 현재 시간..
        Date d = new Date();
        System.out.println(d);//Wed Mar 26 10:06:24 KST 2025

        //특정 시간날짜를 지정하지 않으면.. 현재 시간..
        Calendar c = Calendar.getInstance();

        System.out.println(c);
        System.out.println(c.get(Calendar.YEAR));//2025
        //month 의 0 이 1월
        System.out.println(c.get(Calendar.MONTH)+1);//3
        System.out.println(c.get(Calendar.DAY_OF_MONTH));//26
        System.out.println(c.get(Calendar.DAY_OF_WEEK));//4
        int week = c.get(Calendar.DAY_OF_WEEK);
        switch (week){
            case Calendar.MONDAY: System.out.println("월"); break;
            case Calendar.WEDNESDAY: System.out.println("수"); break;
        }

        System.out.println(c.get(Calendar.HOUR));//10
        System.out.println(c.get(Calendar.MINUTE));//20

        //시간 날짜 데이터. 초기화..
        c.clear();
        c.set(2020, 3, 19);

        //특정 포멧으로.. 시간 날짜가 표현되고자 한다면...............
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 hh시 mm분 ss초");
        System.out.println(f1.format(d));

        try {
            SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
            Date d2 = f2.parse("2023-03-19");
            if(d.after(d2)){
                System.out.println("지난 날짜를 지정할 수 없습니다.");
            }else {
                System.out.println("예약하신 날짜는 "+f2.format(d2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
