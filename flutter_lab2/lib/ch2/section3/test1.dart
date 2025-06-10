main() {
  //List.....................
  //선언과 동시에 값 할당... 제네릭으로 타입 한정을 하지 않으면.. dynamic
  List list1 = [10, 'hello', true];
  list1[0] = 20;
  list1[1] = false;
  print("${list1}");//[20, false, true]

  //타입 지정 사용..
  List<int> list2 = [10, 20, 30];
  // list2[0] = 'hello';//error...
  //배열 처럼 사용하지만.. 사이즈가 고정되지는 않는다..
  list2.add(40);
  list2.addAll([50, 60]);//append...
  list2.insert(1, 70);
  list2.insertAll(1, [80, 90]);
  print(list2);//[10, 80, 90, 70, 20, 30, 40, 50, 60]

  list2.remove(20);
  list2.insert(1, 20);
  list2.insert(5, 20);
  print(list2);//[10, 20, 80, 90, 70, 20, 30, 40, 50, 60]
  //제거하고자 하는 데이터가 여러개 있다면.. 가장 처음꺼 하나만 제거..
  list2.remove(20);
  print(list2);//[10, 80, 90, 70, 20, 30, 40, 50, 60]
  list2.removeAt(0);
  print(list2);//[80, 90, 70, 20, 30, 40, 50, 60]

  print('length : ${list2.length}, ${list2.isEmpty}, ${list2.isNotEmpty}');//length : 8, false, true
  print('${list2.indexOf(90)}');//여러개가 있다면.. 처음꺼 하나의 위치..//1
  print('${list2.contains(20)}');//true

  list2.sort();
  print(list2);//[20, 30, 40, 50, 60, 70, 80, 90]

  var mapList = list2.map((x) => x * 10);
  print(mapList);//(200, 300, 400, 500, 600, 700, 800, 900)
  var whereList = list2.where((x) => x > 50);
  print(whereList);//(60, 70, 80, 90)
}