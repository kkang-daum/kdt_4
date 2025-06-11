//case 1 - singleton..................

class MyClass {
  int data = 0;

  // factory MyClass(){}//error... 객체 준비해서 리턴해야 한다..
  //error.. factory 생성자에서는 자신 타입의 객체를 리턴해야..
  // factory MyClass() {
  //   return null;
  // }

  static MyClass? _instance;
  //내부에서만 사용할 생성자를 하나 정의..
  MyClass._internal();
  factory MyClass(){
    if(_instance == null){
      _instance = MyClass._internal();
    }
    return _instance!;
  }
}

//case 2.... cache 알고리즘...
//네트웍에서 획득한 데이터를 가지는 이미지 클래스 객체...
//동일 url 의 이미지를 가지는 객체가 이미 존재할 수 있다..
//동일 url 로 객체를 생성 시도하면.. 캐싱 알고리즘으로.. 기존 객체를 그대로 이용하게 하겠다..
class Image {
  late String url;
  //객체 캐싱...
  static Map<String, Image> _cache = {};

  Image._instance(this.url);

  factory Image(String url){
    if(_cache[url] == null){
      var obj = Image._instance(url);
      _cache[url] = obj;
    }
    return _cache[url]!;
  }
}

//다형성.... 상위 타입으로.. 하위 객체가 이용...
//실제 이용하는 곳은 상위 타입만 알고.. 하위 타입은 인지하지 못하게..
abstract class Shape {
  //추상 클래스는 직접 객체 생성 불가하다.. 즉 생성자 호출할 수 없다..
  //단 factory 생성자는 호출이 가능하다..
  factory Shape(String type){
    switch(type.toLowerCase()){
      case 'circle':
        return Circle();
      case 'rect':
        return Rect();
      default:
        throw Exception('unknown shape type...');
    }
  }
  void draw();
}
class Circle implements Shape {
  @override
  void draw() {
    print("Circle draw");
  }
}
class Rect implements Shape {
  @override
  void draw() {
    print("Rect draw");
  }
}

main(){
  //case 1 - singleton..................
  //singleton 으로 동작하는 클래스의 객체 획득을 별도의 api 로 사용하게 하고 싶지 않다..
  //이용자 입장에서는 객체를 원하는 것임으로.. 그냥.. 클래스의 객체를 생성해서 사용하게 하고 싶다.
  var obj1 = MyClass();
  var obj2 = MyClass();
  obj1.data = 10;
  obj2.data = 20;
  print("${obj1.data}, ${obj2.data}");//20, 20

  //case 2.... cache 알고리즘...
  var image1 = Image('a.jpg');
  var image2 = Image('a.jpg');

  print('${image1 == image2}');//true


  //다형성...
  var circle = Shape('circle');
  var rect = Shape('rect');
  circle.draw();
  rect.draw();
}