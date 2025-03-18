package ch2.section3;

public class Exam3 {
    public static void main(String[] args) {
        //선언과 동시에 데이터 지정이 가능한 경우..
        int[][] scores = {
                {10, 20},
                {30, 40},
                {30, 40}
        };
        for(int i = 0; i<3; i++){
            for(int j = 0; j<2; j++){
                System.out.print(scores[i][j]+ " ");
            }
            System.out.println();
        }

        //다양한 선언...
        int array1[][];
        int[][] array2;
        int []array3[];

        //사이즈 지정...
//        array1 = new int[][];//error...
        array1 = new int[3][3];//ok...
        array1 = new int[3][];//ok....
//        array1 = new int[][3];//error..

        //array1 = new int[3][];
        //앞 부분의 사이즈만 지정했다면.. 그 안에 들어갈 각각의 배열 사이즈를 다시 지정
        array1[0] = new int[4];
        array1[1] = new int[2];
        array1[2] = new int[5];


        int[][] arr = new int[3][];
        arr[0] = new int[]{10, 20};
        arr[1] = new int[]{10, 20, 30};
    }
}
