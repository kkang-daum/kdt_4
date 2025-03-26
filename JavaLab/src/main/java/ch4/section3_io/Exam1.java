package ch4.section3_io;

import java.io.*;

public class Exam1 {
    public static void main(String[] args) {
        try {
            //file 을 byte 로 읽는 역할..
            FileInputStream fis = new FileInputStream("c:\\Temp\\data.txt");
            //byte 를 문자열로 변형시켜 주는 stream 을 연결해서..
            //문자열 인코딩 타입 문제로.. 한글이 깨질 수도 있다..
            //UTF-8, EUC-KR
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bin = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String line ="";
            //한줄씩 잃어서 line 에 저장.. 더이상 읽을수 없으면 null
            while((line = bin.readLine()) != null){
                System.out.println("read : "+line);
                sb.append(line);
            }

            //File - 실제 데이터 in/out 역할은 아니고.. 파일 혹은 디렉토리를 지칭하기 위한
            File dir = new File("c:\\work");
            dir.mkdir();//디렉토리 만들고..

            File file = new File(dir, "result.txt");
            file.createNewFile();

            if(dir.isDirectory()){
                System.out.println(dir.getName()+" 은 디렉토리입니다.");
            }
            if(file.isFile()){
                System.out.println(file.getName()+"은 파일입니다.");
            }

            if(file.exists()){
                System.out.println("111111111111111");
                PrintWriter out = new PrintWriter(file);
                out.println(sb.toString());
                out.flush();
                out.close();

                System.out.println(file.getAbsolutePath());
                System.out.println(file.length());
            }

            File cDir = new File("c:\\");
            String[] fileNames = cDir.list();
            for(String name: fileNames){
                System.out.println(name);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
