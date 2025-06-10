int data1 = 10;
int? data2 = 10;

//초기화 시점을 미루는...
late int a1;

String? str = "hello";

main(){
  // data1 = data2;//error..
  data1 = data2 as int;//nullable -> non-null : 명시적 캐스팅..
  data2 = data1;//non-null -> nullable : 암시적 캐스팅...

  // print(a1 + 10);//error... LateInitializationError: Field 'a1' has not been initialized.
  a1 = 10;
  print(a1 + 10);

  // str.isEmpty;//error.. nullable 변수를 null safety operator 를 사용하지 않고 이용..
  bool? result = str?.isEmpty;

  int? data3;
  data3 ??= 10;
  print(data3);//10
  data3 ??= null;//대입되는 값이 null 이면 대입이 안된다..
  print(data3);//10

  String? data4 = "hello";
  String? result2 = data4 ?? "world";
  print(result2);//hello
  data4 = null;
  result2 = data4 ?? "world";
  print(result2);//world
}