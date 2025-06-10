void some(int data1, String data2, bool data3){ }

//data1 은 외부에서 필수 입력된다..
//data2, data3 는 옵셔널로 선언되어 있다.. 호출 시점에 데이터 입력이 안될 수도 있다..
//null 인데.. nullable 로 선언이 안되었다..
//옵셔널로 선언되는 매개변수는 nullable 이던가.. default 값을 명시하던가..
void some1(int data1, {String? data2, bool data3 = false}){ }

//필수 매개변수인데.. { } 안에 선언...
void some2({required int data1, String? data2}) { }

main(){
  //매개변수 갯수 및 순서를 맞추어서 호출해야 한다..
  //이름값 명시할 수 없다..
  // some(data1: 10, data2: "hello", data3: true);

  some1(10);//ok....
  // some1(10, "hello", true);//error..named parameter 로 선언된 매개변수이다..
  //값을 지정하려면 꼭 이름 명시해야 한다..
  some1(10, data2: "hello");//ok...
  some1(10, data3: true);//ok...
  some1(10, data2: "hello", data3: true);
  some1(10, data3: true, data2: "hello");//순서 맞추지 않아도 되고..
  some1(data3: true, 10, data2: "hello");//ok.... 필수의 순서 문제만 발생하지 않는다면..

  //필수를 지정하기는 한다.. 순서 신경쓰기 싫다..
  some2(data1: 10);
  some2(data2: "hello", data1: 10);
}